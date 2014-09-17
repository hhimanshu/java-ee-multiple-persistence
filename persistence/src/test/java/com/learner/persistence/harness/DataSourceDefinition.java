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

	@Nonnull
	Map<String, String> getPersistenceProperties();
}
