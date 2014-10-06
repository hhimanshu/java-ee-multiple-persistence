package com.learner.persistence.harness;


import javax.annotation.Nonnull;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class H2Client implements DataSourceDefinition {

	private final String database;

	public H2Client() {
		this("integrationDB");
	}

	public H2Client(@Nonnull final String database) {
		this.database = database;
	}

	@Override
	public void before() {

	}

	@Override
	public void after() {

	}

	@Nonnull
	@Override
	public Map<String, String> getPersistenceProperties() {
		final Map<String, String> map = new HashMap<>();
		map.put(HIBERNATE_DIALECT, "org.hibernate.dialect.H2Dialect");
		map.put(JAVAX_PERSISTENCE_JDBC_URL, "jdbc:h2:tcp://localhost/~/" + new File("target", database).getAbsolutePath());
		map.put(JAVAX_PERSISTENCE_JDBC_DRIVER, "org.h2.Driver");
		map.put(JAVAX_PERSISTENCE_JDBC_USER, "sa");
		map.put(JAVAX_PERSISTENCE_JDBC_PASSWORD, "sa");
		return map;
	}

}
