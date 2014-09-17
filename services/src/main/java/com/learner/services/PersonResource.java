package com.learner.services;

import javax.annotation.Nonnull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.learner.business.presentation.PersonPresentation;


@Path("persons")
public class PersonResource {

	@POST
	public Response add(@Nonnull final PersonPresentation personPresentation) {
		return Response.ok("hello").build();
	}
}
