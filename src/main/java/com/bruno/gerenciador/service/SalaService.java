package com.bruno.gerenciador.service;

import java.util.List;

import com.bruno.gerenciador.model.Sala;

public interface SalaService {
	List<Sala> getAllSalas();
	void saveSala(Sala sala);
	Sala getSalaById(long id);
	void deleteSalaById(long id);
}
