package com.bruno.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.model.Sala;
import com.bruno.gerenciador.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService{
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> getAllPessoas() {
		return pessoaRepository.findAll();
	}

	@Override
	public void savePessoa(Pessoa pessoa) {
		this.pessoaRepository.save(pessoa);
	}

	@Override
	public Pessoa getPessoaById(long id) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		Pessoa pessoa = null;
		
		if (optional.isPresent()) {
			pessoa = optional.get();
		}
		else {
			throw new RuntimeException("Pessoa não encotnrada para o id: " + id);
		}
		return pessoa;
	}

	@Override
	public void deletePessoaById(long id) {
		this.pessoaRepository.deleteById(id);
	}

	@Override
	public List<Pessoa> getPessoasNaSala1(Sala sala) {
		return pessoaRepository.getPessoasNaSala1(sala);
	}

	@Override
	public List<Pessoa> getPessoasNaSala2(Sala sala) {
		return pessoaRepository.getPessoasNaSala2(sala);
	}

	@Override
	public Pessoa savePessoaTeste(Pessoa pessoa) {
		this.pessoaRepository.save(pessoa);
		return pessoa;
		
	}

	@Override
	public List<Pessoa> getPessoasSemSalas() {
		return pessoaRepository.getPessoasSemSalas();
	}
}
