package com.bruno.gerenciador.espacoCafeTestes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bruno.gerenciador.model.EspacoCafe;
import com.bruno.gerenciador.repository.EspacoCafeRepository;
import com.bruno.gerenciador.service.EspacoCafeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EspacoCafeRepositoryTest {

	@Autowired
	private EspacoCafeService service;
	
	@MockBean
	private EspacoCafeRepository repository;

	@Test
	public void pegarTodasEspacoCafesTeste() {
		when(repository.findAll()).thenReturn(Stream
				.of(new EspacoCafe(504, "Connect",30), new EspacoCafe(5, "Café Station", 30)).collect(Collectors.toList()));
		assertThat(service.getAllEspacoCafes().size() == 2);
	}
	
	@Test
	public void pegarEspacoCafePorIdTeste() {
		Optional<EspacoCafe> espacoCafe = null;
		long id = 5;
		when(repository.findById(id)).thenReturn(espacoCafe = Optional.of(new EspacoCafe(5, "Café Station", 30)));
		
		assertThat(service.getEspacoCafeById(id) != null);
	}
	
	@Test
	public void deletaEspacoCafeTeste() {
		EspacoCafe espacoCafe = new EspacoCafe(5, "Café Station", 30);
		service.deleteEspacoCafeById(espacoCafe.getId());
		verify(repository, times(1)).deleteById(espacoCafe.getId());
	}
	
}
