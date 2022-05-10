package com.projects.praticandoAPI;

import static org.junit.Assert.*;

import org.junit.Test;

import com.projects.praticandoAPI.modelo.Jogador;

public class JogadorTest {

	@Test(expected = RuntimeException.class)
	public void numeroGolsDeveSerMaiorQueZero() {
		Jogador jogador = new Jogador("Leonardo",0);
		
		jogador.setGols(-5);
	}

	@Test()
	public void numeroDeGolsDeveSerAtualizado() {
		Jogador jogador = new Jogador("Leonardo",0);
		
		jogador.setGols(8);
		
		assertEquals(jogador.getGols(), 8);
	}

}
