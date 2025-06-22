package br.com.ngbilling.gestaobancaria.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.ngbilling.gestaobancaria.dto.response.ResponseDTO;
import br.com.ngbilling.gestaobancaria.interfaces.EntityDTO;

public class JSONUtil {
	public static EntityDTO jsonToEntityDTO(String json, EntityDTO classeDTO) throws Exception {
		try {
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			EntityDTO dto = gson.fromJson(json, classeDTO.getClass());
			return dto;
		} catch (Exception e) {
			throw new Exception("Estrutura do JSON inválida");
		}
	}

	public static String responseToJson(ResponseDTO<?> response) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		String json = gson.toJson(response.getResponse());
		return json;
	}

	public static String erroToJson(ResponseDTO<?> response) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		String json = gson.toJson(response.getErros());
		return json;
	}
}
