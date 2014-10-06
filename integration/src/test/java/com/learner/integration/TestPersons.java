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
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.learner.persistence.harness.Integration;
import com.learner.persistence.harness.JpaRule;

@Category(Integration.class)
public class TestPersons extends AbstractIntegrationTest {

	@Rule
	public final JpaRule jpaRule = new JpaRule(JpaRule.Derby.client, "unit-testing-pu");


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
