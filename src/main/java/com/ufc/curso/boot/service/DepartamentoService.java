package com.ufc.curso.boot.service;

import java.util.List;

import com.ufc.curso.boot.domain.Departamento;

public interface DepartamentoService {

	void salvar(Departamento departamento);

	void editar(Departamento departamento);

	void excuir(Long id);

	Departamento buscarPorId(Long id);

	List<Departamento> buscarTodos();

	boolean departamentoTemCargo(Long id);
}
