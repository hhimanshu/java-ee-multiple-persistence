package com.learner.persistence.harness;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class H2Memory implements DataSourceDefinition {

	private final String database;

	public H2Memory() {
		this("unit-testing-pu");
	}

	public H2Memory(@Nonnull final String database) {
		this.database = database;
	}

	@Nonnull
	@Override
	public Map<String, String> getPersistenceProperties() {
		final Map<String, String> map = new HashMap<>();
		map.put(HIBERNATE_DIALECT, "org.hibernate.dialect.H2Dialect");
		map.put(JAVAX_PERSISTENCE_JDBC_URL, "jdbc:h2:mem:" + database);
		map.put(JAVAX_PERSISTENCE_JDBC_DRIVER, "org.h2.Driver");
		map.put(JAVAX_PERSISTENCE_JDBC_USER, "");
		map.put(JAVAX_PERSISTENCE_JDBC_PASSWORD, "");
		map.put(HIBERNATE_HBM2DDL_AUTO, "create-drop");
		return map;
	}
}
