package com.projects.praticandoAPI.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.projects.praticandoAPI.modelo.Jogador;

public class JogadorDTO {
	private Long id;
	private String nome;
	private int gols;
	
	public JogadorDTO() {
		super();
	}

	public JogadorDTO(Jogador jogador) {
		super();
		this.id = jogador.getId();
		this.nome = jogador.getNome();
		this.gols = jogador.getGols();
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getGols() {
		return gols;
	}

	public static List<JogadorDTO> converter(List<Jogador> jogadores) {
		return jogadores.stream().map(JogadorDTO::new).collect(Collectors.toList());
	}
}
