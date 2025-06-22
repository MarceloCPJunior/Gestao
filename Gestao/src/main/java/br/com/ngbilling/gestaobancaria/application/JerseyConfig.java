package br.com.ngbilling.gestaobancaria.application;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


import org.glassfish.jersey.server.ServerProperties;

@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		// Registrar os pacotes onde estão os recursos REST
		packages("br.com.ngbilling.gestaobancaria.resources");

		// Registrar Swagger
		register(OpenApiResource.class);

		// Configurações do Jersey
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		property(ServerProperties.JSON_PROCESSING_FEATURE_DISABLE, false);
		property(ServerProperties.MOXY_JSON_FEATURE_DISABLE, true);
	}
}
