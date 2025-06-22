package br.com.ngbilling.gestaobancaria.arquitetura;

import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import br.com.ngbilling.gestaobancaria.util.JSONUtil;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public abstract class GenericResource {
	protected Response construirResponse(ResponseDTO<?> response, Response.Status statusSucesso,
			Response.Status statusErro) {
		Response.Status status = response.isInSuccess() ? statusSucesso : statusErro;
		String json = response.isInSuccess() ? JSONUtil.responseToJson(response) : JSONUtil.erroToJson(response);
		return Response.status(status).entity(json).type(MediaType.APPLICATION_JSON).build();
	}
}
