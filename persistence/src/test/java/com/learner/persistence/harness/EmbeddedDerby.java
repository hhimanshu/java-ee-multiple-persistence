package com.learner.persistence.harness;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EmbeddedDerby implements DataSourceDefinition {
	static final String DERBY_TEN_TEN_DIALECT = "org.hibernate.dialect.DerbyTenSevenDialect";
	static final String JDBC_URL_PROTOCOL = "jdbc:derby:";

	private static final String DERBY_SYSTEM_HOME = "derby.system.home";
	private static final Logger LOGGER = LoggerFactory.getLogger(DerbyDirectory.class);

	private String previousDerbySystemHome;

	@Nonnull
	public String assembleBaseUrl(@Nonnull final String... attributes) {
		final StringBuilder sb = new StringBuilder(getPersistenceProperties().get(JAVAX_PERSISTENCE_JDBC_URL));
		for (final String attribute : attributes) {
			sb.append(";").append(attribute);
		}
		return sb.toString();
	}

	@Override
	public void before() {
		previousDerbySystemHome = System.setProperty(DERBY_SYSTEM_HOME, "target/derby");
		LOGGER.info("Starting " + this + " database for unit tests");
		try {
			DriverManager.getConnection(assembleBaseUrl("create=true")).close();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
			Assert.fail("Failing to start " + this + ": " + e.getMessage());
		}
	}

	@Override
	public void after() {
		LOGGER.info("Stopping " + this + " database.");
		try {
			DriverManager.getConnection(assembleBaseUrl("shutdown=true")).close();
		} catch (SQLException e) {
			// XJ015 (with SQLCODE 50000) is the expected SQLSTATE for complete system shutdown.
			// 08006 (with SQLCODE 45000) is the expected SQLSTATE for shutdown of only an individual database.
			if (e.getErrorCode() != 45000 && e.getErrorCode() != 50000) {
				LOGGER.error(e.getMessage(), e);
				Assert.fail("Failing to close " + this + ": " + e.getMessage());
			}
		} finally {
			if (previousDerbySystemHome == null) {
				System.getProperties().remove(DERBY_SYSTEM_HOME);
			} else {
				System.setProperty(DERBY_SYSTEM_HOME, previousDerbySystemHome);
			}
		}
	}
}