package com.learner.services;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.learner.business.manager.PersonManager;
import com.learner.business.presentation.PersonPresentation;


@Path("persons")
public class PersonResource {

	private PersonManager personManager;

	@SuppressWarnings("UnusedDeclaration")
	public PersonResource() {
	}

	@Inject
	public PersonResource(@Nonnull final PersonManager personManager) {
		this.personManager = personManager;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPerson(@Nonnull final PersonPresentation personPresentation) {
		return Response.ok(personManager.addPerson(personPresentation)).status(Response.Status.CREATED).build();
	}
}
