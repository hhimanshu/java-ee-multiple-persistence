package com.learner.persistence.harness;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaRule extends ExternalResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaRule.class);

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private final DataSourceDefinition dataSourceDefinition;
	private final String persistenceUnitName;

	public JpaRule(final String persistenceUnitName) {
		dataSourceDefinition = new H2Memory();
		this.persistenceUnitName = persistenceUnitName;
	}

	public JpaRule(@Nonnull final String persistenceUnitName, @Nonnull final DataSourceDefinition dataSourceDefinition) {
		this.dataSourceDefinition = dataSourceDefinition;
		this.persistenceUnitName = persistenceUnitName;
	}

	public void changeTransaction() {
		commitOrRollbackTransaction();
		entityManager.getTransaction().begin();
	}

	@Override
	protected void before() throws Throwable {
		LOGGER.debug("building database");
		final Map<String, String> persistenceProperties = new HashMap<>();
		persistenceProperties.putAll(dataSourceDefinition.getPersistenceProperties());

		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, persistenceProperties);
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@Override
	protected void after() {
		LOGGER.debug("destroying database");
		if (entityManager != null) {
			try {
				commitOrRollbackTransaction();
			} catch (final PersistenceException e) {
				LOGGER.error(e.getMessage(), e);
			}

			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}

		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	private void commitOrRollbackTransaction() {
		final EntityTransaction transaction = entityManager.getTransaction();
		if (!transaction.isActive()) {
			return;
		}
		if (transaction.getRollbackOnly()) {
			transaction.rollback();
		} else {
			transaction.commit();
		}
	}
}
