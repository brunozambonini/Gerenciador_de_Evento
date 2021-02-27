package com.bruno.gerenciador.controller;

import java.util.List;

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
import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.model.Sala;
import com.bruno.gerenciador.service.EspacoCafeService;
import com.bruno.gerenciador.service.PessoaService;
import com.bruno.gerenciador.service.SalaService;

@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private SalaService salaService;

	@Autowired
	private EspacoCafeService espacoCafeService;

	// mostrar lista de pessoas
	@GetMapping("/listaPessoas")
	public String listaPessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaService.getAllPessoas());
		return "Pessoa/lista_pessoa"; // nome do arquivo html
	}

	// criar form
	@GetMapping("/novaPessoaForm")
	public String novaPessoa(Model model) {
		model.addAttribute(new Pessoa());
		model.addAttribute(new Sala());
		model.addAttribute(new EspacoCafe());
		// pegar lista de salas para popular select
		model.addAttribute("listaSalas", salaService.getAllSalas());
		// pegar lista de espaços de café para popular select
		model.addAttribute("listaEspacoCafes", espacoCafeService.getAllEspacoCafes());
		return "Pessoa/nova_pessoa";
	}

	// Salvar pessoa no banco de dados
	@PostMapping("/salvarPessoa")
	public String salvarPessoa(@ModelAttribute("pessoa") @Valid Pessoa pessoa, BindingResult result,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique se os campos foram preenchidos corretamente!");
			return "redirect:/novaPessoaForm";
		}
		pessoaService.savePessoa(pessoa);
		return "redirect:/listaPessoas";
	}

	// Mostrar dados da pessoa para serem editados ou não
	@GetMapping("/formUpdate/{id}")
	public String verPessoa(@PathVariable(value = "id") long id, Model model) {
		// pega pessoa do banco de dados
		Pessoa pessoa = pessoaService.getPessoaById(id);

		// popular o form com os dados da pessoa
		model.addAttribute("pessoa", pessoa);

		Sala salaAtual1 = new Sala();
		Sala salaAtual2 = new Sala();
		// preenche select da sala com as salas atuais
		if (pessoa.getSala1() != null) {
			salaAtual1 = salaService.getSalaById(pessoa.getSala1().getId());
		}
		if (pessoa.getSala2() != null) {
			salaAtual2 = salaService.getSalaById(pessoa.getSala2().getId());
		}

		EspacoCafe espacoCafeAtual1 = new EspacoCafe();
		EspacoCafe espacoCafeAtual2 = new EspacoCafe();
		if (pessoa.getEspacoCafe1() != null) {
			espacoCafeAtual1 = espacoCafeService.getEspacoCafeById(pessoa.getEspacoCafe1().getId());
		}
		if (pessoa.getEspacoCafe1() != null) {
			espacoCafeAtual2 = espacoCafeService.getEspacoCafeById(pessoa.getEspacoCafe2().getId());
		}

		model.addAttribute("espacoCafeAtual1", espacoCafeAtual1);
		model.addAttribute("espacoCafeAtual2", espacoCafeAtual2);
		model.addAttribute("salaAtual1", salaAtual1);
		model.addAttribute("salaAtual2", salaAtual2);
		model.addAttribute("listaSalas", salaService.getAllSalas());
		model.addAttribute("listaEspacoCafes", espacoCafeService.getAllEspacoCafes());

		return "Pessoa/detalhes_pessoa";
	}

	// Salvar pessoa no banco de dados
	@PostMapping("/salvarUpdatePessoa")
	public String atualizarPessoa(@ModelAttribute(value = "sala1") String salaId,
			@ModelAttribute(value = "sala2") String sala2Id, @ModelAttribute(value = "pessoa") Pessoa pessoa,
			RedirectAttributes attributes) {

		Sala sala = salaService.getSalaById(Long.parseLong(salaId));

		// verifica se tem espaço ou se a sala já está cheia
		if (pessoaService.getPessoasNaSala1(sala).size() >= sala.getLotacao()) {
			attributes.addFlashAttribute("mensagem", "A sala " + sala.getNome() + " está lotada");
			return "redirect:/novaPessoaForm";
		}
		// verifica se tem espaço ou se a sala já está cheia
		sala = salaService.getSalaById(Long.parseLong(sala2Id));
		if (pessoaService.getPessoasNaSala2(sala).size() >= sala.getLotacao()) {
			attributes.addFlashAttribute("mensagem", "A sala " + sala.getNome() + " está lotada");
			return "redirect:/novaPessoaForm";
		}

		pessoaService.savePessoa(pessoa);
		return "redirect:/listaPessoas";
	}

	// Deleta pessoa por id
	@GetMapping("/deletePessoa/{id}")
	public String deletePessoa(@PathVariable("id") Long id) {
		this.pessoaService.deletePessoaById(id);
		return "redirect:/listaPessoas";
	}

	@GetMapping("/distribuirForm")
	public String formDistribuirPessoas(Model model) {
		model.addAttribute(new Sala());
		model.addAttribute(new EspacoCafe());
		// pegar lista de salas para popular select
		model.addAttribute("listaSalas", salaService.getAllSalas());
		// pegar lista de espaços de café para popular select
		model.addAttribute("listaEspacoCafes", espacoCafeService.getAllEspacoCafes());

		return "Pessoa/distribuir_pessoas";
	}

	@PostMapping("/distribuir")
	public String distribuirPessoas(@ModelAttribute(value = "sala1") String salaId,
			@ModelAttribute(value = "sala2") String sala2Id, @ModelAttribute(value = "espacoCafe1") String espacoCafeId,
			@ModelAttribute(value = "espacoCafe2") String espacoCafe2Id, RedirectAttributes attributes) {

		if (salaId.equals("") || sala2Id.equals("") || espacoCafeId.equals("") || espacoCafe2Id.equals("")) {
			attributes.addFlashAttribute("mensagem", "Preencha todos os campos");
			return "redirect:/distribuirForm";
		}

		// mensagem de erro caso as salas sejam iguais
		if (salaId.equals(sala2Id)) {
			attributes.addFlashAttribute("mensagem", "As salas não podem ser iguais");
			return "redirect:/distribuirForm";
		}

		// mensagem de erro caso as salas sejam iguais
		if (espacoCafeId.equals(espacoCafe2Id)) {
			attributes.addFlashAttribute("mensagem", "Os espaços de café para intervalo não podem ser iguais");
			return "redirect:/distribuirForm";
		}

		// pega os dados das salas
		Sala sala = salaService.getSalaById(Long.parseLong(salaId));
		Sala sala2 = salaService.getSalaById(Long.parseLong(sala2Id));

		// pega os dados dos espaços de café para o intervalo
		EspacoCafe intervalo = espacoCafeService.getEspacoCafeById(Long.parseLong(espacoCafeId));
		EspacoCafe intervalo2 = espacoCafeService.getEspacoCafeById(Long.parseLong(espacoCafe2Id));

		// pega lista de pessoas sem salas
		List<Pessoa> listaPessoas = pessoaService.getPessoasSemSalas();

		int i = 1;
		int total = listaPessoas.size();

		if (total == 0) {
			attributes.addFlashAttribute("mensagem", "Não há pessoas sem salas ou sem espaços para intervalos");
			return "redirect:/distribuirForm";
		}
		// for para adicionar sala e café para cada pessoa
		for (Pessoa pessoa : listaPessoas) {

			// pega lista de pessoas que ja estão em cada sala
			List<Pessoa> listaPessoas1 = pessoaService.getPessoasNaSala1(sala);
			List<Pessoa> listaPessoas2 = pessoaService.getPessoasNaSala2(sala);

			if (i <= (total / 2)) { // de metade das pessoas sem salas
				if (i <= (total / 4)) { // um quarto, a primeira metade da primeira metade, irá trocar de sala
					pessoa.setSala1(sala);
					pessoa.setSala2(sala2);
					pessoa.setEspacoCafe1(intervalo);
					pessoa.setEspacoCafe2(intervalo2);
				} else { // o resto da primeira metade não trocará de sala
					pessoa.setSala2(sala);
					pessoa.setSala1(sala);
					pessoa.setEspacoCafe2(intervalo);
					pessoa.setEspacoCafe1(intervalo);
				}

			} else { // da outra metada de pessoas sem salas:
				if (i <= (total * 0.75)) { // três quartos, a primeira metade da segunda metade, irá trocar de sala
					pessoa.setSala2(sala);
					pessoa.setSala1(sala2);
					pessoa.setEspacoCafe2(intervalo);
					pessoa.setEspacoCafe1(intervalo2);
				} else { // o resto da metade, não trocará de sala
					pessoa.setSala2(sala2);
					pessoa.setSala1(sala2);
					pessoa.setEspacoCafe2(intervalo2);
					pessoa.setEspacoCafe1(intervalo2);
				}
			}
			// verifica se há espaço para alocar a pessoa na sala ou espaço de café
			if (sala.getLotacao() > listaPessoas1.size() && sala2.getLotacao() > listaPessoas2.size()) {
				pessoaService.savePessoa(pessoa);
				i++;
			} else {
				attributes.addFlashAttribute("mensagem", "Sala(s) lotada(s)");
				return "redirect:/distribuirForm";
			}

		}
		return "redirect:/listaPessoas";
	}

	@GetMapping("/resetarPessoasAlocadas")
	public String resetarPessoasAlocadas(RedirectAttributes attributes) {
		List<Pessoa> listaPessoas = pessoaService.getAllPessoas();

		System.out.println(listaPessoas.size());

		// pega lista de pessoas sem salas
		List<Pessoa> listaPessoasSemSalas = pessoaService.getPessoasSemSalas();

		if (listaPessoas.size() != listaPessoasSemSalas.size()) {
			for (Pessoa pessoa : listaPessoas) {
				pessoa.setSala1(null);
				pessoa.setSala2(null);
				pessoa.setEspacoCafe1(null);
				pessoa.setEspacoCafe2(null);

				pessoaService.savePessoa(pessoa);
			}
			attributes.addFlashAttribute("mensagem", "Todas pessoas foram desalocadas.");
			return "redirect:/listaPessoas";
		} else {
			attributes.addFlashAttribute("mensagem", "Nenhuma pessoa está alocada.");
			return "redirect:/listaPessoas";
		}
	}
}
