package com.co.andresoft.apirest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			@PathVariable(value = "publicacionId") Long publicacionId) {

		return comentarioService.obtenerComentariosByPublicacionId(publicacionId);

	}

	@GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> getComentarioPorId(
			@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId){
		
		ComentarioDTO comentarioDTO = comentarioService
				.obtenerComentarioById(publicacionId, comentarioId);
		
		return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/publicaciones/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> guardarComentario(
			@PathVariable(value = "publicacionId") Long publicacionId,
			@Valid
			@RequestBody ComentarioDTO comentarioDTO) {

		return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO),
				HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<ComentarioDTO> actualizarComentario(
			@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId,
			@Valid 
			@RequestBody ComentarioDTO nuevoComentario){
		
		ComentarioDTO comentarioActualizado = comentarioService
				.actualizarComentarioById(publicacionId, comentarioId, nuevoComentario);
		
		return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
	public ResponseEntity<String> eliminarComentario(
			@PathVariable(value = "publicacionId") Long publicacionId,
			@PathVariable(value = "id") Long comentarioId){
		
		comentarioService.eliminarComentarioById(publicacionId, comentarioId);
		return new ResponseEntity<>("Comentario eliminado con exito!!", HttpStatus.OK);
		
	}
	
}
