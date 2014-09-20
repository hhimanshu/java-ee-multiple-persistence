package com.learner.persistence.configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CrudService {
	private EntityManager entityManager;

	@SuppressWarnings("UnusedDeclaration")
	public CrudService() {
	}

	@Inject
	public CrudService(@PersistenceEntityManager @Nonnull final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("UnusedDeclaration")
	@Nonnull
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Nonnull
	public <T> T create(@Nonnull final T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		return entity;
	}

	@Nullable
	public <T> T find(final long id, final Class<T> classType) {
		return entityManager.find(classType, id);
	}

	public <T> void delete(@Nonnull final T entity) {
		entityManager.remove(entity);
	}
}
