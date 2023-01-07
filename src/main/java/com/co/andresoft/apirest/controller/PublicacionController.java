package com.co.andresoft.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.model.service.IPublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionService publicacionService;

	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<PublicacionDTO> listarPublicaciones() {
		return publicacionService.obtenerPublicaciones();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> ObtenerpublicacionById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(publicacionService.obtenerPublicacionById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacionById(@RequestBody PublicacionDTO publicacionDTO,
			@PathVariable(name = "id") Long id) {
		PublicacionDTO publicacionRespuesta = publicacionService.actualizarPublicacionById(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id){
		publicacionService.eliminarPublicacionById(id);
		return new ResponseEntity<>("Publicación eliminada con exito!!", HttpStatus.OK);
	}

}
