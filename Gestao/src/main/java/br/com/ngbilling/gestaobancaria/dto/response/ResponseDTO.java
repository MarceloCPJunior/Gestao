package br.com.ngbilling.gestaobancaria.dto.response;

import java.util.ArrayList;
import java.util.List;

//import io.swagger.v3.oas.annotations.media.Schema;

//@Schema(description = "DTO genérico para respostas da API")
public class ResponseDTO<T extends Object> {
	
//	@Schema(description = "Dados da resposta")
	private T response;
	
//	@Schema(description = "Indica se a operação foi bem-sucedida", example = "true")
	private boolean inSuccess = false;
	
//	@Schema(description = "Lista de erros, se houver")
	private List<String> erros;

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public boolean isInSuccess() {
		return inSuccess;
	}

	public void setInSuccess(boolean inSuccess) {
		this.inSuccess = inSuccess;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public void addErro(String erro) {
		if (erros == null) {
			erros = new ArrayList<>();
		}
		erros.add(erro);
	}
}
