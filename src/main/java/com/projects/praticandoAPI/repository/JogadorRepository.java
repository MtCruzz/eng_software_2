package com.projects.praticandoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projects.praticandoAPI.modelo.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>{
	
}
