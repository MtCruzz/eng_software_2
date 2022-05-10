package com.projects.praticandoAPI.modelo;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jogador {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private int gols;
	
	public Jogador() {}
	
	public Jogador(String nome, int gols) {
		super();
		
		this.nome = nome;
		this.gols = gols;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gols, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		return gols == other.gols && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getGols() {
		return gols;
	}

	public void setGols(int gols) {
		validaGols(gols);
		
		this.gols += gols;
	}
	
	private void validaGols(int gols) {
		if(gols < 0)
			throw new RuntimeException("Gols deve ser maior ou igual a zero.");
	}
	
	
}
