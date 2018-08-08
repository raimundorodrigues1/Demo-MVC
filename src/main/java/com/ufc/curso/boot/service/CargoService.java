package com.ufc.curso.boot.service;

import java.util.List;

import com.ufc.curso.boot.domain.Cargo;

public interface CargoService {
	
	void salvar (Cargo cargo);
	
	void editar (Cargo cargo);
	
	void excuir (Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();

	boolean cargoTemFuncionario(Long id);

}
