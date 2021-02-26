package com.bruno.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.gerenciador.model.Sala;
import com.bruno.gerenciador.repository.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService{

	@Autowired
	private SalaRepository salaRepository;
	
	@Override
	public List<Sala> getAllSalas() {
		return salaRepository.findAll();
	}

	@Override
	public void saveSala(Sala sala) {
		this.salaRepository.save(sala);
		
	}

	@Override
	public Sala getSalaById(long id) {
		Optional<Sala> optional = salaRepository.findById(id);
		Sala sala = null;
		
		if (optional.isPresent()) {
			sala = optional.get();
		}
		else {
			throw new RuntimeException("Sala não encotnrada para o id: " + id);
		}
		return sala;
	}

	@Override
	public void deleteSalaById(long id) {
		this.salaRepository.deleteById(id);	
	}

}
