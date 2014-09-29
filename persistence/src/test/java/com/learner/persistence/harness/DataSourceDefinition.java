package com.learner.persistence.harness;

import java.util.Map;

import javax.annotation.Nonnull;

public interface DataSourceDefinition {

	String HIBERNATE_DIALECT = "hibernate.dialect";
	String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

	String JAVAX_PERSISTENCE_JDBC_URL = "javax.persistence.jdbc.url";
	String JAVAX_PERSISTENCE_JDBC_DRIVER = "javax.persistence.jdbc.driver";
	String JAVAX_PERSISTENCE_JDBC_USER = "javax.persistence.jdbc.user";
	String JAVAX_PERSISTENCE_JDBC_PASSWORD = "javax.persistence.jdbc.password";

	/**
	 * Called at the start of the JpaRule before() method. Use this to set up additional environment variable.
	 */
	void before();

	/**
	 * Called at the end of the JpaRule after() method. Use this to perform additional clean-up.
	 */
	void after();

	/**
	 * Properties to pass to the entity manager factory. Use this, to define an hibernate dialect or some of the
	 * <em>javax .persistence.jdbc</em> properties.
	 *
	 * @return Additional properties to use when creating the entity manager factory. The values of these properties
	 * override any values that may have been configured elsewhere
	 */
	@Nonnull
	Map<String, String> getPersistenceProperties();
}

