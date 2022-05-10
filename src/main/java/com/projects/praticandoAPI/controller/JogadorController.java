package com.projects.praticandoAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.projects.praticandoAPI.controller.dto.JogadorDTO;
import com.projects.praticandoAPI.controller.form.AtualizaGolJogadorForm;
import com.projects.praticandoAPI.controller.form.JogadorForm;
import com.projects.praticandoAPI.modelo.Jogador;
import com.projects.praticandoAPI.repository.JogadorRepository;

@RestController
@CrossOrigin
@RequestMapping("/jogadores")
public class JogadorController {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@GetMapping
	public List<JogadorDTO> lista(){
		List<Jogador> jogadores = jogadorRepository.findAll();
		return JogadorDTO.converter(jogadores);
	}
	
	@PostMapping
	public ResponseEntity<JogadorDTO> cadastrar(@RequestBody JogadorForm form,UriComponentsBuilder uriBuilder){
		Jogador jogador = form.converter();
		jogadorRepository.save(jogador);
		
		URI uri = uriBuilder.path("/jogadores/{id}").buildAndExpand(jogador.getId()).toUri();
		return ResponseEntity.created(uri).body(new JogadorDTO(jogador));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<JogadorDTO> atualizaGols(@PathVariable Long id,@RequestBody AtualizaGolJogadorForm form){
		Optional<Jogador> jogador = jogadorRepository.findById(id);
		
		if(jogador.isEmpty()) 
			return ResponseEntity.notFound().build();
		try {
			jogador.get().setGols(form.getGols());
			
			jogadorRepository.save(jogador.get());
			
			return ResponseEntity.ok(new JogadorDTO(jogador.get()));	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}	
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		Optional<Jogador> jogador = jogadorRepository.findById(id);
		
		if(jogador.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		jogadorRepository.delete(jogador.get());
		
		return ResponseEntity.noContent().build();
	}
}
