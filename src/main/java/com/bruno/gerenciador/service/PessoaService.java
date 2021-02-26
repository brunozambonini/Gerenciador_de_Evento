package com.bruno.gerenciador.service;

import java.util.List;

import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.model.Sala;

public interface PessoaService {
	List<Pessoa> getAllPessoas();
	void savePessoa(Pessoa pessoa);
	Pessoa getPessoaById(long id);
	void deletePessoaById(long id);
	List<Pessoa> getPessoasNaSala1(Sala sala);
	List<Pessoa> getPessoasNaSala2(Sala sala);
	List<Pessoa> getPessoasSemSalas();
	Pessoa savePessoaTeste(Pessoa pessoa); //Criado para criar o teste unitário
}
