package com.bruno.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.gerenciador.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{
	
}
