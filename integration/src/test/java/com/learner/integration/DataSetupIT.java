package com.learner.integration;

import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;

import com.learner.database.DataSetupTest;
import com.learner.persistence.harness.JpaRule;

import liquibase.exception.LiquibaseException;

public class DataSetupIT {
	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");

	@Test
	public void setupData() throws SQLException, LiquibaseException {
		DataSetupTest.runLiquibaseUpdate(jpaRule);
	}
}
