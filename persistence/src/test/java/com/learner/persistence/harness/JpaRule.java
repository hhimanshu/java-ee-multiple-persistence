package com.learner.persistence.harness;

import org.junit.rules.ExternalResource;

public class JpaRule extends ExternalResource {
	@Override
	protected void before() throws Throwable {
		System.out.println("JpaRule Before");
	}

	@Override
	protected void after() {
		System.out.println("JpaRule After");
	}
}
