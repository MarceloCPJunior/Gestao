package br.com.ngbilling.gestaobancaria.application;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		property("jersey.config.server.wadl.disableWadl", true);
		packages("br.com.ngbilling.gestaobancaria.resources");
		register(OpenApiResource.class);
	}
}
