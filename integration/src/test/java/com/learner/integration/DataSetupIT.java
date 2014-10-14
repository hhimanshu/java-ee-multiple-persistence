package com.learner.integration;

import com.learner.database.DataSetupTest;
import com.learner.persistence.harness.JpaRule;
import com.learner.persistence.harness.PreIntegration;
import liquibase.exception.LiquibaseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.SQLException;

@Category(PreIntegration.class)
public class DataSetupIT {
    @Rule
    public final JpaRule jpaRule = new JpaRule(JpaRule.H2.directory, "unit-testing-pu");

    @Test
    public void setupData() throws SQLException, LiquibaseException {
        // (todo: harit) better logging
        System.out.println("Setting up IT Database");
        DataSetupTest.runLiquibaseUpdate(jpaRule);
    }
}
