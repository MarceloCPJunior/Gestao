package br.com.ngbilling.gestaobancaria.resources;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericResource;
import br.com.ngbilling.gestaobancaria.bo.TransacaoBO;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacao")
public class TransacaoResource extends GenericResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacao(String body) {
		TransacaoBO bo = new TransacaoBO();
		ResponseDTO<ContaDTO> response = bo.criarTransacao(body);
		return construirResponse(response, Response.Status.CREATED, Response.Status.NOT_FOUND);
	}
}
