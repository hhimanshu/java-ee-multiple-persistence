package com.learner.services.configuration;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;


@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonContextResolver implements ContextResolver<ObjectMapper> {
	private final ObjectMapper objectMapper;

	public JacksonContextResolver() {
		objectMapper = new ObjectMapper()
				.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

		final AnnotationIntrospector annotationIntrospector = new JaxbAnnotationIntrospector();
		objectMapper
				.setDeserializationConfig(objectMapper.getDeserializationConfig().withAnnotationIntrospector(annotationIntrospector))
				.setSerializationConfig(objectMapper.getSerializationConfig().withAnnotationIntrospector(annotationIntrospector));
	}

	@Override
	public ObjectMapper getContext(final Class<?> aClass) {
		return objectMapper;
	}
}
