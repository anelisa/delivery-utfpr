package com.delivery.app.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static void merge(Object original, Map<String, Object> campos) {
		//todos os dados vem do json e vão para o Map q esta na variavel campos
		//fazer mapeamento entre objetos, vou receber algo em formato de objeto e mapear ele no formato de outro objeto
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		
		//vai converter o objeto original
		//vai realizar um map desses campos e vai converter para o novo objeto "origem"
		//origem é os campos que estão vindo
		Object origem = objectMapper.convertValue(campos, original.getClass());
		
		//o foreach é feito em campos, por que eu preciso fazer isso no objeto que está vindo 
		// e esse objeto não irá conter todos os campos
		campos.forEach((propriedade, valor) -> {
			//acesso as propriedades do objeto, que vem em formato de string
			Field campo = ReflectionUtils.findField(original.getClass(), propriedade);
			campo.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(campo, origem);
			ReflectionUtils.setField(campo, original, novoValor);
		});
		
	}
}
