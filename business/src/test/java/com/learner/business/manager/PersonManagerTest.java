package com.learner.business.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.learner.business.presentation.PersonPresentation;
import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.Person;

public class PersonManagerTest {

	@Mock
	private CrudService crudService;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddPerson() {
		final PersonManager personManager = new PersonManager(crudService);
		final Person mockedPerson = new Person("Christa", "Albert");
		Mockito.when(crudService.create(any(Person.class))).thenReturn(mockedPerson);
		final PersonPresentation personPresentation = personManager.addPerson(new PersonPresentation(mockedPerson.getFirstName(), mockedPerson.getLastName()));
		assertNotNull(personPresentation);
		assertEquals(mockedPerson.getFirstName(), personPresentation.getFirstName());
		assertEquals(mockedPerson.getLastName(), personPresentation.getLastName());
	}
}