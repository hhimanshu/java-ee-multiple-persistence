package com.learner.persistence;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.Person;
import com.learner.persistence.entities.QPerson;

public class PersonQueries {

	private static final QPerson person = QPerson.person;
	private final CrudService crudService;

	@Inject
	public PersonQueries(@Nonnull final CrudService crudService) {
		this.crudService = crudService;
	}

	@Nonnull
	public List<Person> getPersons() {
		return crudService.query(person).list(person);
	}
}
