package br.com.ngbilling.gestaobancaria.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO<T extends Object> {
	private T response;
	private boolean inSuccess = false;
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
