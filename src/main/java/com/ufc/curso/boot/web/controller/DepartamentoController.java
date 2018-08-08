package com.ufc.curso.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.curso.boot.domain.Departamento;
import com.ufc.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {

		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", service.buscarTodos());

		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento inserido com sucesso");
		return "redirect:/departamentos/cadastrar";

	}

	@GetMapping("/editar/{id}")
	public String PreEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", service.buscarPorId(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String Editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "departamento/cadastro";
		}

		service.editar(departamento);
		attr.addFlashAttribute("success", "Departamento editado com sucesso");
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {

		if (service.departamentoTemCargo(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargos vinculados");
		} else {
			model.addAttribute("success", "Departamento excluído com sucesso");
			service.excuir(id);

		}
		return listar(model);
	}
}
