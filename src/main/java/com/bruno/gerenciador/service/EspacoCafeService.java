package com.bruno.gerenciador.service;

import java.util.List;

import com.bruno.gerenciador.model.EspacoCafe;

public interface EspacoCafeService {
	List<EspacoCafe> getAllEspacoCafes();
	void saveEspacoCafe(EspacoCafe espacoCafe);
	EspacoCafe getEspacoCafeById(long id);
	void deleteEspacoCafeById(long id);
}
