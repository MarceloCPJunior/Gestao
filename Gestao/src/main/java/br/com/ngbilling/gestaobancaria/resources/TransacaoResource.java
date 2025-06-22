package br.com.ngbilling.gestaobancaria.resources;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericResource;
import br.com.ngbilling.gestaobancaria.bo.TransacaoBO;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacao")
//@Tag(name = "Transação", description = "Operações relacionadas a transações bancárias")
public class TransacaoResource extends GenericResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
//	@Operation(
//		summary = "Criar nova transação",
//		description = "Cria uma nova transação bancária com os dados fornecidos"
//	)
//	@ApiResponses(value = {
//		@ApiResponse(
//			responseCode = "201",
//			description = "Transação criada com sucesso",
//			content = @Content(
//				mediaType = MediaType.APPLICATION_JSON,
//				schema = @Schema(implementation = ContaDTO.class)
//			)
//		),
//		@ApiResponse(
//			responseCode = "404",
//			description = "Conta não encontrada"
//		)
//	})
	public Response transacao(String body) {
		TransacaoBO bo = new TransacaoBO();
		ResponseDTO<ContaDTO> response = bo.criarTransacao(body);
		return construirResponse(response, Response.Status.CREATED, Response.Status.NOT_FOUND);
	}
}
