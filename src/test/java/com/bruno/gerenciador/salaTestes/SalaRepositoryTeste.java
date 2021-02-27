package com.bruno.gerenciador.salaTestes;

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

import com.bruno.gerenciador.model.Sala;
import com.bruno.gerenciador.repository.SalaRepository;
import com.bruno.gerenciador.service.SalaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SalaRepositoryTeste {

	@Autowired
	private SalaService service;

	@MockBean
	private SalaRepository repository;

	@Test
	public void pegarTodasSalasTeste() {
		when(repository.findAll()).thenReturn(Stream.of(new Sala(1, "Rio de Janeiro", 50), new Sala(2, "São Paulo", 30))
				.collect(Collectors.toList()));
		assertThat(service.getAllSalas().size() == 2);
	}

	@Test
	public void pegarSalaPorIdTeste() {
		Optional<Sala> sala = null;
		long id = 5;
		when(repository.findById(id)).thenReturn(sala = Optional.of(new Sala(1, "Rio de Janeiro", 50)));

		assertThat(service.getSalaById(id) != null);
	}

	@Test
	public void deletaSalaTeste() {
		Sala sala = new Sala(1, "Rio de Janeiro", 50);
		service.deleteSalaById(sala.getId());
		verify(repository, times(1)).deleteById(sala.getId());
	}
}
