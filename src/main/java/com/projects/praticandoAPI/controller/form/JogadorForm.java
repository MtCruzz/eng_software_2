package com.projects.praticandoAPI.controller.form;

import com.projects.praticandoAPI.modelo.Jogador;

public class JogadorForm {
	public JogadorForm() {
		super();
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Jogador converter() {
		return new Jogador(nome,0);
	}
}
