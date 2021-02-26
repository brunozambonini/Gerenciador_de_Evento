package com.bruno.gerenciador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.model.Sala;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	// JpaRepository possui todos os métodos para um CRUD básico pronto

	@Query("FROM Pessoa Where sala1 = ?1")
	List<Pessoa> getPessoasNaSala1(Sala sala);
	
	@Query("FROM Pessoa Where sala2 = ?1")
	List<Pessoa> getPessoasNaSala2(Sala sala);
	
	@Query("FROM Pessoa Where sala1 is null and sala2 is null")
	List<Pessoa> getPessoasSemSalas();
}
