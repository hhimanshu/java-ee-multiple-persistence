package com.learner.persistence.configuration;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class PersistenceEntityManagerProducer {
	@PersistenceContext(unitName = "earth")
	private EntityManager entityManager;

	@Produces
	@PersistenceEntityManager
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
