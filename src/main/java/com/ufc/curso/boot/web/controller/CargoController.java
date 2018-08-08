package com.ufc.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ufc.curso.boot.domain.Cargo;
import com.ufc.curso.boot.domain.Departamento;
import com.ufc.curso.boot.service.CargoService;
import com.ufc.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {

		return "cargo/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {

			return "cargo/cadastro";
		}

		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso");
		return "redirect:/cargos/cadastrar";

	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());

		return "cargo/lista";
	}

	@GetMapping("/editar/{id}")
	public String PreEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}

	@PostMapping("/editar")
	public String Editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {

			return "cargo/cadastro";
		}

		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo editado com sucesso");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionario(id)) {

			attr.addFlashAttribute("fail", "Cargo não removido. Possui funcionários vinculados.");

		} else {
			cargoService.excuir(id);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso.");
		}
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {

		return departamentoService.buscarTodos();
	}

}
