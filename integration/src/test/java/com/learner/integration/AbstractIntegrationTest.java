package com.learner.integration;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class AbstractIntegrationTest {
	public static void assertStatus(final int expected, @Nonnull final Response response) {
		if (expected == response.getStatus()) {
			return;
		}
		fail("expected=" + expected + ", status=" + response.getStatus() + ", message=" + response.getEntity());
	}

	@Nonnull
	public JsonNode parseResponse(@Nonnull final Response response) {
		try {
			return new ObjectMapper().readTree(response.readEntity(String.class));
		} catch (final IOException e) {
			throw new RuntimeException("Can not parse response: " + e.getMessage());
		}
	}
}