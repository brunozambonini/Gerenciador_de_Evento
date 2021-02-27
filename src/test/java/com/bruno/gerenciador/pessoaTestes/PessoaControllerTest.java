package com.bruno.gerenciador.pessoaTestes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bruno.gerenciador.controller.PessoaController;
import com.bruno.gerenciador.model.EspacoCafe;
import com.bruno.gerenciador.model.Pessoa;
import com.bruno.gerenciador.model.Sala;
import com.bruno.gerenciador.service.EspacoCafeService;
import com.bruno.gerenciador.service.PessoaService;
import com.bruno.gerenciador.service.SalaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PessoaController.class)
class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PessoaService pessoaService;

	@MockBean
	private SalaService salaService;

	@MockBean
	private EspacoCafeService espacoCafeService;

	@Test
	void verificarSeRetornaOkAoChamarListaPessoasTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/listaPessoas")).andExpect(status().isOk());
	}

	@Test
	void verificarSeRetornaOkAoChamarNovaPessoaTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/novaPessoaForm")).andExpect(status().isOk());
	}	
}