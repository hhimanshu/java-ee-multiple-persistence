package com.learner.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.junit.Rule;
import org.junit.Test;

import com.learner.persistence.harness.JpaRule;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class DataSetupTest {
	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");

	@Test
	public void runLiquibaseUpdate() throws LiquibaseException, SQLException {
		final Liquibase liquibase = new Liquibase("liquibase/changelog.xml",
				new ClassLoaderResourceAccessor(),
				new JdbcConnection(getConnection(jpaRule.getEntityManager())));
		liquibase.dropAll();
		liquibase.update("");
	}

	@Nonnull
	private static Connection getConnection(@Nonnull final EntityManager em) throws SQLException {
		final Session session = em.unwrap(Session.class);
		final SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
		final ConnectionProvider cp = sfi.getConnectionProvider();
		return cp.getConnection();
	}
}
