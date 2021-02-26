package com.bruno.gerenciador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruno.gerenciador.model.EspacoCafe;

@Repository
public interface EspacoCafeRepository extends JpaRepository<EspacoCafe, Long>{

}
