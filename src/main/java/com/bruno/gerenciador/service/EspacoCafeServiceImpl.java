package com.bruno.gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.gerenciador.model.EspacoCafe;
import com.bruno.gerenciador.repository.EspacoCafeRepository;

@Service
public class EspacoCafeServiceImpl implements EspacoCafeService {

	@Autowired
	private EspacoCafeRepository espacoCafeRepository;
	
	@Override
	public List<EspacoCafe> getAllEspacoCafes() {
		return espacoCafeRepository.findAll();
	}

	@Override
	public void saveEspacoCafe(EspacoCafe espacoCafe) {
		this.espacoCafeRepository.save(espacoCafe);
	}

	@Override
	public EspacoCafe getEspacoCafeById(long id) {
		Optional<EspacoCafe> optional = espacoCafeRepository.findById(id);
		EspacoCafe espacoCafe = null;
		
		if (optional.isPresent()) {
			espacoCafe = optional.get();
		}
		else {
			throw new RuntimeException("EspacoCafe não encotnrada para o id: " + id);
		}
		return espacoCafe;
	}

	@Override
	public void deleteEspacoCafeById(long id) {
		this.espacoCafeRepository.deleteById(id);	
	}

}
