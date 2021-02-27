package com.bruno.gerenciador.salaTestes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bruno.gerenciador.controller.SalaController;
import com.bruno.gerenciador.repository.PessoaRepository;
import com.bruno.gerenciador.service.PessoaService;
import com.bruno.gerenciador.service.SalaService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SalaController.class)
public class SalaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PessoaService pessoaService;

	@MockBean
	private SalaService salaService;

	@MockBean
	private PessoaRepository pessoaRepository;

	@Test
	void verificarSeRetornaOkAoChamarListaSalasTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/listaSalas")).andExpect(status().isOk());
	}

	@Test
	void verificarSeRetornaOkAoChamarNovaSalaTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/novaSalaForm")).andExpect(status().isOk());
	}
	
}
