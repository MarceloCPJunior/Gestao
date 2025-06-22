package br.com.ngbilling.gestaobancaria.resources;

import br.com.ngbilling.gestaobancaria.arquitetura.GenericResource;
import br.com.ngbilling.gestaobancaria.bo.ContaBO;
import br.com.ngbilling.gestaobancaria.dto.model.ContaDTO;
import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conta")
@Tag(name = "Conta", description = "Operações relacionadas a contas bancárias")
public class ContaResource extends GenericResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Criar nova conta", description = "Cria uma nova conta bancária com os dados fornecidos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Conta criada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ContaDTO.class))),
			@ApiResponse(responseCode = "409", description = "Conflito - Conta já existe") })
	public Response criarConta(@Parameter(description = "Dados da conta em formato JSON", required = true) String body,
			@Context HttpHeaders headers) {
		String ambiente = headers.getHeaderString("X-ENV");
		ContaBO bo = new ContaBO();
		ResponseDTO<ContaDTO> response = bo.criarConta(body, ambiente);
		return construirResponse(response, Response.Status.CREATED, Response.Status.CONFLICT);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "Consultar conta", description = "Consulta uma conta bancária pelo número da conta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Conta encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Conta não encontrada") })
	public Response consultarConta(
			@Parameter(description = "Número da conta", required = true) @QueryParam("numero_conta") Long numeroConta,
			@Context HttpHeaders headers) {
		String ambiente = headers.getHeaderString("X-ENV");
		ContaBO bo = new ContaBO();
		ResponseDTO<ContaDTO> response = bo.consultarConta(numeroConta, ambiente);
		return construirResponse(response, Response.Status.OK, Response.Status.NOT_FOUND);
	}
}
