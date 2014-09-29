package com.learner.persistence.harness;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class DerbyClient implements DataSourceDefinition {

	private final String database;

	public DerbyClient() {
		this("integrationDB");
	}

	public DerbyClient(@Nonnull final String database) {
		this.database = database;
	}

	@Nonnull
	public static String getPort() {
		return System.getProperty("derby.port", "1527");
	}

	@Nonnull
	@Override
	public Map<String, String> getPersistenceProperties() {
		final Map<String, String> map = new HashMap<>();
		map.put(HIBERNATE_DIALECT, EmbeddedDerby.DERBY_TEN_TEN_DIALECT);
		map.put(JAVAX_PERSISTENCE_JDBC_URL, EmbeddedDerby.JDBC_URL_PROTOCOL + "//localhost:" + getPort() + "/" + database);
		map.put(JAVAX_PERSISTENCE_JDBC_DRIVER, "org.apache.derby.jdbc.ClientDriver");
		map.put(JAVAX_PERSISTENCE_JDBC_USER, System.getProperty("datasource.user", "APP"));
		map.put(JAVAX_PERSISTENCE_JDBC_PASSWORD, System.getProperty("datasource.password", "nonemptypassword"));
		return map;
	}

	@Override
	public void before() {
	}

	@Override
	public void after() {
	}
}
