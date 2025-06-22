package br.com.ngbilling.gestaobancaria.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test")
public class TestAplicacao {
	@GET
	public String ping() {
		return "API OK!";
	}
}
