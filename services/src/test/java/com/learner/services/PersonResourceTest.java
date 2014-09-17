package com.learner.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.learner.business.manager.PersonManager;
import com.learner.business.presentation.PersonPresentation;

public class PersonResourceTest {

	@Mock
	private PersonManager personManager;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddPerson() {
		final PersonResource personResource = new PersonResource(personManager);
		final PersonPresentation personPresentation = new PersonPresentation("Stephanie", "Jones");

		when(personManager.addPerson(personPresentation)).thenReturn(personPresentation);
		final Response response = personResource.addPerson(personPresentation);

		assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
	}
}