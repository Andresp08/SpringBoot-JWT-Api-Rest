package com.co.andresoft.apirest.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.dto.PublicacionRespuesta;
import com.co.andresoft.apirest.model.service.IPublicacionService;
import com.co.andresoft.apirest.util.AppConstantes;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionService publicacionService;

	@GetMapping
	public PublicacionRespuesta listarPublicaciones(
			@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_PAGINA_DEFECTO, 
				required = false) int numeroPagina,
			@RequestParam(value = "pageSize", defaultValue = AppConstantes.NUMERO_REGISTROS_DEFECTO, 
				required = false) int cantidadRegistros,
			@RequestParam(value = "sortBy", defaultValue = AppConstantes.ORDENAR_DEFECTO, 
				required = false) String ordenarPor,
			@RequestParam(value = "sortDir", defaultValue = AppConstantes.ORDENAR_DIRECCION_DEFECTO, 
				required = false) String sortDir) {

		return publicacionService.obtenerPublicaciones(numeroPagina, cantidadRegistros, ordenarPor, sortDir);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PublicacionDTO> ObtenerpublicacionById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(publicacionService.obtenerPublicacionById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PublicacionDTO> guardarPublicacion(
			@Valid
			@RequestBody PublicacionDTO publicacionDTO) {
		return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PublicacionDTO> actualizarPublicacionById(
			@Valid
			@RequestBody PublicacionDTO publicacionDTO,
			@PathVariable(name = "id") Long id) {
		PublicacionDTO publicacionRespuesta = publicacionService.actualizarPublicacionById(publicacionDTO, id);
		return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarPublicacion(@PathVariable(name = "id") Long id) {
		publicacionService.eliminarPublicacionById(id);
		return new ResponseEntity<>("Publicación eliminada con exito!!", HttpStatus.OK);
	}

}
