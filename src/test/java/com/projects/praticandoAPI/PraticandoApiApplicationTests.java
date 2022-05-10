package com.projects.praticandoAPI;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.projects.praticandoAPI.controller.dto.JogadorDTO;
import com.projects.praticandoAPI.controller.form.AtualizaGolJogadorForm;
import com.projects.praticandoAPI.controller.form.JogadorForm;
import com.projects.praticandoAPI.modelo.Jogador;
import com.projects.praticandoAPI.repository.JogadorRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PraticandoApiApplicationTests {		
	@Autowired
	private TestRestTemplate testRestTemplate;
	  
	@Autowired 
	private JogadorRepository jogadorRespository;
	
	@Test
	public void criarNovoJogadorTest() {
		JogadorForm form = new JogadorForm();
		form.setNome("Leonardo");
		
		HttpEntity<JogadorForm> httpEntity = new HttpEntity<>(form);
		
		ResponseEntity<JogadorDTO> response = this.testRestTemplate
				.exchange("/jogadores", HttpMethod.POST, httpEntity,JogadorDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody().getNome(), "Leonardo");
	}

	@Test
	public void listaTest() {
		ResponseEntity<JogadorDTO[]> response = this.testRestTemplate
				.exchange("/jogadores", HttpMethod.GET, null,JogadorDTO[].class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void atualizaGolsTest() {
		Jogador jogador = new Jogador("Leonardo", 0);
		
		Jogador jogadorSalvo = this.jogadorRespository.save(jogador);
		
		AtualizaGolJogadorForm form = new AtualizaGolJogadorForm();
		form.setGols(2);
		
		HttpEntity<AtualizaGolJogadorForm> httpEntity = new HttpEntity<>(form);
		
		ResponseEntity<JogadorDTO> response = this.testRestTemplate
				.exchange("/jogadores/"+ jogadorSalvo.getId(), HttpMethod.PUT, httpEntity,JogadorDTO.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getGols(), 2);
	}
	
	@Test
	public void deletarTest() {
		Jogador jogador = new Jogador("Leonardo", 0);
		
		Jogador jogadorSalvo = this.jogadorRespository.save(jogador);
		
		ResponseEntity<Void> response = this.testRestTemplate
				.exchange("/jogadores/"+ jogadorSalvo.getId(), HttpMethod.DELETE, null,Void.class);
		
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
}

