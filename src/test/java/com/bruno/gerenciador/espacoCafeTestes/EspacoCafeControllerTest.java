package com.bruno.gerenciador.espacoCafeTestes;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bruno.gerenciador.controller.EspacoCafeController;
import com.bruno.gerenciador.service.EspacoCafeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EspacoCafeController.class)
public class EspacoCafeControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EspacoCafeService espacoCafeService;

	@Test
	void verificarSeRetornaOkAoChamarListaEspacoCafesTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/listaEspacoCafes")).andExpect(status().isOk());
	}

	@Test
	void verificarSeRetornaOkAoChamarNovaEspacoCafeTeste() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/novoEspacoCafeForm")).andExpect(status().isOk());
	}
	
}
