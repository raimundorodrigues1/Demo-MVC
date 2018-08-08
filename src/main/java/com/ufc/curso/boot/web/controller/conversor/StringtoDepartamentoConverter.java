package com.ufc.curso.boot.web.controller.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ufc.curso.boot.domain.Departamento;
import com.ufc.curso.boot.service.DepartamentoService;

@Component
public class StringtoDepartamentoConverter implements Converter<String, Departamento> {
	@Autowired
	private DepartamentoService service;

	@Override
	public Departamento convert(String text) {

		if (text.isEmpty()) {
			return null;
		}

		Long id= Long.valueOf(text);
		
		return service.buscarPorId(id);
	}

}
