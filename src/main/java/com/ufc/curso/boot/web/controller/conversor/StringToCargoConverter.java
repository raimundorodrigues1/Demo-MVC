package com.ufc.curso.boot.web.controller.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ufc.curso.boot.domain.Cargo;
import com.ufc.curso.boot.service.CargoService;


@Component
public class StringToCargoConverter implements Converter<String, Cargo> {
	
	@Autowired
	private CargoService service;


	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {
			return null;
		}

		Long id= Long.valueOf(text);
		
		return service.buscarPorId(id);
	}
}
