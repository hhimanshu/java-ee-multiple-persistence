package com.learner.integration;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.codehaus.jackson.JsonNode;
import org.junit.Test;

public class PersonsIT extends AbstractIntegrationTest {

	@Test
	public void testGetPersons() throws IOException {
		final Client client = ClientBuilder.newClient();
		final WebTarget webTarget = client.target("http://localhost:9090/earth/rest/persons");
		final Response response = webTarget
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).get();
		assertStatus(HttpStatus.SC_OK, response);
		final JsonNode responsePayload = parseResponse(response);
		assertEquals(5, responsePayload.size());
	}
}
