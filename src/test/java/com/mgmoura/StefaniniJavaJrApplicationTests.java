package com.mgmoura;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.mgmoura.dtos.AutorPostRequestDto;
import com.mgmoura.dtos.AutorPutRequestDto;
import com.mgmoura.dtos.AutorResponseDto;
import com.mgmoura.dtos.ObraPostRequestDto;
import com.mgmoura.entities.Autor;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StefaniniJavaJrApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	private static Autor autor;

	@Test
	@Order(1)
	public void testAutorPost() throws Exception {

		Faker faker = new Faker();
				
		AutorPostRequestDto dto = new AutorPostRequestDto();
		
		dto.setNome(faker.funnyName().name());
		dto.setSexo("F");
		dto.setEmail("post@gmail.com");
		dto.setDataNascimento("2020-10-05");
		dto.setPaisOrigem(faker.country().name());
		dto.setCpf(String.valueOf(faker.number().randomNumber(11, false)));

		MvcResult result = mockMvc.perform(post("/autor") 
				.contentType("application/json")
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		AutorResponseDto response = mapper.readValue(responseBody, AutorResponseDto.class);
		
		autor = response.getAutor();
	}

	@Test
	@Order(2)
	public void testAutorPut() throws Exception{
		
		AutorPutRequestDto dto = new AutorPutRequestDto();
		
		dto.setIdAutor(autor.getIdAutor());
		dto.setNome("Usuario Put");
		dto.setSexo("M");
		dto.setEmail("put@email.com");
		dto.setDataNascimento("2019-10-10");
		dto.setPaisOrigem("usa");
		dto.setCpf("999-000-111-22");
		
		mockMvc.perform(put("/autor") 
				.contentType("application/json")
				.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
	}

	@Test
	@Order(3)
	public void testAutorGetAll() throws Exception{
		
		mockMvc.perform(get("/autor")).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	public void testAutorGetById() throws Exception{
		
		mockMvc.perform(get("/autor" + autor.getIdAutor())).andExpect(status().isOk());
	}



	@Test
	@Order(5)
	public void testObraPost() throws Exception {
		
		ObraPostRequestDto dto = new ObraPostRequestDto();
		
		dto.setIdAutor(autor.getIdAutor());
		dto.setNome("Livro ABC");
		dto.setDescricao("Histórias Infantis");
		dto.setDataPublicacao("2021-10-11");
		dto.setDataExposicao("2022-10-22");
		
		mockMvc.perform(post("/obra") 
				.contentType("application/json") 
				.content(mapper.writeValueAsString(dto))) 
				.andExpect(status().isCreated());
	}
	
	@Test
	@Order(6)
	public void testObraGetAll() throws Exception{
		
		mockMvc.perform(get("/obra/2000-01-01/2025-01-01")).andExpect(status().isOk());
	}
	
	
	@Test
	@Order(7)
	public void testAutorDelete() throws Exception{
		
		testAutorPost(); // novo autor para deletar
		mockMvc.perform(delete("/autor" + autor.getIdAutor())).andExpect(status().isOk());
	}
	

}
