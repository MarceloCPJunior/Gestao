package br.com.ngbilling.gestaobancaria.resources;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericResource;
import br.com.ngbilling.gestaobancaria.bo.ContaBO;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
public class ContaResource extends GenericResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response criarConta(String body) {
		ContaBO bo = new ContaBO();
		ResponseDTO<ContaDTO> response = bo.criarConta(body);
		return construirResponse(response, Response.Status.CREATED, Response.Status.CONFLICT);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarConta(@QueryParam("numero_conta") Long numeroConta) {
		ContaBO bo = new ContaBO();
		ResponseDTO<ContaDTO> response = bo.consultarConta(numeroConta);
		return construirResponse(response, Response.Status.OK, Response.Status.NOT_FOUND);
	}
}
