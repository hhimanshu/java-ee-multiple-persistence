package com.learner.persistence.harness;

import com.learner.persistence.configuration.CrudService;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JpaRule extends ExternalResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaRule.class);

    private final DataSourceDefinition dataSourceDefinition;
    private EntityManager entityManager;
    private EntityManagerFactory emFactory;
    private final String persistenceUnitName;

    public static class H2 {
        /**
         * Starts and connects to an in-memory embedded instance of derby
         */
        public static final DataSourceDefinition memory = new H2Memory();
        /**
         * Starts and connects to an embedded instance of derby that persists to the file system.
         */
        public static final DataSourceDefinition directory = new H2Directory();
    }

    /**
     * Creates an in-memory test environment.
     *
     * @param persistenceUnitName the name of the persistence unit to load
     */
    public JpaRule(@Nonnull final String persistenceUnitName) {
        dataSourceDefinition = H2.memory;
        this.persistenceUnitName = persistenceUnitName;
    }

    /**
     * Creates a 'committing' test environment. Use this to setup a integration test environment.
     *
     * @param dataSourceDefinition the protocol to use for the jdbc url
     * @param persistenceUnitName  the name of the persistence unit to load
     */
    public JpaRule(@Nonnull final DataSourceDefinition dataSourceDefinition, @Nonnull final String persistenceUnitName) {
        this.dataSourceDefinition = dataSourceDefinition;
        this.persistenceUnitName = persistenceUnitName;
    }

    /**
     * Closes the transaction, if there is an open transaction, then opens a new transaction. Use this to ensure that
     * entities a really flushed down to the sql layer. This can catch serialization/validation issues...
     */
    public void changeTransaction() {
        commitOrRollbackTransaction();
        entityManager.getTransaction().begin();
    }

    /**
     * Clear the persistence context. Useful for integration test when an other process (the app server) also accesses
     * the same entities.
     */
    public void clearPersistenceContext() {
        entityManager.clear();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Nonnull
    public <S extends CrudService> S createCrudService(@Nonnull final Class<S> crudServiceClass) {
        try {
            return crudServiceClass.getDeclaredConstructor(EntityManager.class).newInstance(entityManager);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(crudServiceClass + " doesn't have a public constructor with a single EntityManager parameter (" + e.getMessage() + ")");
        }
    }

    @Override
    protected void before() throws Throwable {
        dataSourceDefinition.before();
        LOGGER.info("Building JPA EntityManager for unit tests");

        final Map<String, String> persistenceProperties = new HashMap<>();
        persistenceProperties.put("hibernate.id.new_generator_mappings", "true");
        persistenceProperties.putAll(dataSourceDefinition.getPersistenceProperties());

        emFactory = Persistence.createEntityManagerFactory(persistenceUnitName, persistenceProperties);
        entityManager = emFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @Override
    protected void after() {
        LOGGER.info("Closing the transaction.");
        if (entityManager != null) {
            try {
                commitOrRollbackTransaction();
            } catch (PersistenceException e) {
                LOGGER.error(e.getMessage(), e);
            }
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        if (emFactory != null) {
            emFactory.close();
        }
        dataSourceDefinition.after();
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
