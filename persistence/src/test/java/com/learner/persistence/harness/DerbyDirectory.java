package com.learner.persistence.harness;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

public class DerbyDirectory extends EmbeddedDerby {

	private final String database;

	public DerbyDirectory() {
		this("integrationDB");
	}

	public DerbyDirectory(@Nonnull final String database) {
		this.database = database;
	}

	@Nonnull
	@Override
	public Map<String, String> getPersistenceProperties() {
		final Map<String, String> map = new HashMap<>();
		map.put(HIBERNATE_DIALECT, EmbeddedDerby.DERBY_TEN_TEN_DIALECT);
		map.put(JAVAX_PERSISTENCE_JDBC_URL, EmbeddedDerby.JDBC_URL_PROTOCOL + "directory:" + database);
		map.put(JAVAX_PERSISTENCE_JDBC_DRIVER, "org.apache.derby.jdbc.EmbeddedDriver");
		map.put(JAVAX_PERSISTENCE_JDBC_USER, "");
		map.put(JAVAX_PERSISTENCE_JDBC_PASSWORD, "");
		map.put(HIBERNATE_HBM2DDL_AUTO, "create");
		return map;
	}

}
