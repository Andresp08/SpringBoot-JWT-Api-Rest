package com.co.andresoft.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.andresoft.apirest.dto.ComentarioDTO;

import com.co.andresoft.apirest.model.service.IComentarioService;

@RestController
@RequestMapping("/api/")
public class ComentarioController {

	@Autowired
	private IComentarioService comentarioService;
	
	@GetMapping("/publicaciones/{publicacionId}/comentarios")
	public List<ComentarioDTO> getComentariosPorPublicacionId(
			@PathVariable(value = "publicacionId") Long publicacionId){
		
		return  comentarioService.obtenerComentariosByPublicacionId(publicacionId);
		
	}
	
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(
			@PathVariable(value = "publicacionId") Long publicacionId,
			@RequestBody ComentarioDTO comentarioDTO){
		
		return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO), 
					HttpStatus.CREATED);
	}
	
}
