package com.bruno.gerenciador.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bruno.gerenciador.model.EspacoCafe;
import com.bruno.gerenciador.service.EspacoCafeService;

@Controller
public class EspacoCafeController {

	@Autowired
	private EspacoCafeService espacoCafeService;
	
	//
	@GetMapping("/listaEspacoCafes")
	public String listaEspacoCafes(Model model) {
		model.addAttribute("listaEspacoCafes", espacoCafeService.getAllEspacoCafes());
		return "EspacoCafe/lista_espacoCafe"; // nome do arquivo html
	}
	
	//
	@GetMapping("/novoEspacoCafeForm")
	public String novoEspacoCafe(Model model) {
		EspacoCafe espacoCafe = new EspacoCafe();
		model.addAttribute("espacoCafe", espacoCafe);
		
		return "EspacoCafe/novo_espacoCafe";
	}
	
	// 
	@PostMapping("/salvarEspacoCafe")
	public String salvarEspacoCafe(@ModelAttribute("espacoCafe") @Valid EspacoCafe espacoCafe, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			 attributes.addFlashAttribute("mensagem","Verifique se os campos foram preenchidos corretamente!");
			 return "redirect:/novoEspacoCafeForm";
		 }
		
		espacoCafeService.saveEspacoCafe(espacoCafe);
		return "redirect:/listaEspacoCafes";
	}
	
	//Mostrar dados da pessoa para serem editados ou não
	@GetMapping("/espacoCafe/{id}")
	public String detalhesEspacoCafe(@PathVariable (value = "id") long id, Model model) {
		//pega espacoCafe do banco de dados
		EspacoCafe espacoCafe = espacoCafeService.getEspacoCafeById(id);
		
		//popular o form com os dados da espacoCafe
		model.addAttribute("espacoCafe", espacoCafe);
		
		return "EspacoCafe/detalhes_espacoCafe";
	}
	
	
	 @GetMapping("/deleteEspacoCafe/{id}")
	 public String deleteEspacoCafe(@PathVariable("id") Long id) {
		 this.espacoCafeService.deleteEspacoCafeById(id);
		 return "redirect:/listaEspacoCafes";
	 }
}
