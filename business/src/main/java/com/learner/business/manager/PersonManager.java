package com.learner.business.manager;

import java.util.List;

import javax.annotation.Nonnull;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.learner.business.presentation.PersonPresentation;
import com.learner.persistence.PersonQueries;
import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.Person;

@Stateless
public class PersonManager {
	private CrudService crudService;

	@SuppressWarnings("UnusedDeclaration")
	public PersonManager() {
	}

	@Inject
	public PersonManager(@Nonnull final CrudService crudService) {
		this.crudService = crudService;
	}

	@Nonnull
	public PersonPresentation addPerson(@Nonnull final PersonPresentation personPresentation) {
		return PersonPresentation.toPersonPresentation(crudService.create(personPresentation.toPerson()));
	}

	@Nonnull
	public List<PersonPresentation> getPersons() {
		final List<Person> persons = new PersonQueries(crudService).getPersons();
		return PersonPresentation.toPersonPresentations(persons);
	}
}
