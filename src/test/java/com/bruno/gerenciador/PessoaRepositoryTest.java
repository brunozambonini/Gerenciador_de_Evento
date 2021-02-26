package com.bruno.gerenciador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.repository.PessoaRepository;
import com.bruno.gerenciador.service.PessoaService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PessoaRepositoryTest {

	@Autowired
	private PessoaService service;
	
	@MockBean
	private PessoaRepository repository;
	
	@Test
	public void pegarTodasPessoasTeste() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Pessoa(504, "Bruno","Zambonini"), new Pessoa (580, "Andressa","Santiago")).collect(Collectors.toList()));
		assertThat(service.getAllPessoas().size() == 2);
	}
	
	@Test
	public void pegarPessoaPorIdTeste() {
		Optional<Pessoa> pessoa = null;
		long id = 5;
		when(repository.findById(id)).thenReturn(pessoa = Optional.of(new Pessoa(504, "Bruno","Zambonini")));
		
		assertThat(service.getPessoaById(id) != null);
	}
	
	@Test
	public void salvarPessoaTeste() {
		Pessoa pessoa = new Pessoa(500, "Bruno", "Zambonini");
		when(repository.save(pessoa)).thenReturn(pessoa);
		assertThat(pessoa == service.savePessoaTeste(pessoa));
	}
	
	@Test
	public void deletaPessoaTeste() {
		Pessoa pessoa = new Pessoa(500, "Bruno", "Zambonini");
		service.deletePessoaById(pessoa.getId());
		verify(repository, times(1)).deleteById(pessoa.getId());
	}
	
	@Test
	public void seSejaInformadoUmaPessoaValidaNaoRetornaErro() {
	    Pessoa pessoa = new Pessoa(1, "Bruno", "Zambonini");
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Set<ConstraintViolation<Pessoa>> violations = factory.getValidator().validate(pessoa);
	    
	    assertThat(violations.size()).isEqualTo(0);
	}
	    
	@Test
	public void seForInformadoNomeOuSobrenomeEmBrancoRetornaErro() {
		Pessoa pessoa = new Pessoa(1, " ", " ");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Set<ConstraintViolation<Pessoa>> violations = factory.getValidator().validate(pessoa);

	    assertThat(violations.size()).isEqualTo(2);
	}
	    
	@Test
	public void seForInformadoNomeOuSobrenomeNulosOuVaziosRetornaErro() {
		Pessoa pessoa = new Pessoa(1, null, null);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Set<ConstraintViolation<Pessoa>> violations = factory.getValidator().validate(pessoa);
	    assertThat(violations.size()).isEqualTo(4); // 4 porque o NotBlacnk, considera null como um erro tbm
	}	
}
