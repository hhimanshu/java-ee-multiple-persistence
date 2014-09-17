package com.learner.persistence;

import org.junit.Rule;
import org.junit.Test;

import com.learner.persistence.harness.JpaRule;

public class PersonTest {

	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");

	@Test
	public void testAddPerson() {
	}

	@Test
	public void testDeletePerson() {

	}
}
