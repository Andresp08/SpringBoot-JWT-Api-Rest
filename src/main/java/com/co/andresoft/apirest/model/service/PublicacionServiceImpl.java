package com.co.andresoft.apirest.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.exception.ResourceNotFoundException;
import com.co.andresoft.apirest.model.dao.IPublicacionDao;
import com.co.andresoft.apirest.model.entity.Publicacion;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

	@Autowired
	private IPublicacionDao publicacionDao;

	@Override
	@Transactional
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		// convertir de DTO a Entity

		Publicacion publicacion = mapearEntidad(publicacionDTO);

		Publicacion nuevaPublicacion = publicacionDao.save(publicacion);

		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PublicacionDTO> obtenerPublicaciones() {
		List<Publicacion> publicaciones = publicacionDao.findAll();
		return publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public PublicacionDTO obtenerPublicacionById(Long id) {
		Publicacion publicacion = publicacionDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		return mapearDTO(publicacion);
	}
	
	@Override
	@Transactional
	public PublicacionDTO actualizarPublicacionById(PublicacionDTO publicacionDTO, Long id) {
		Publicacion publicacion = publicacionDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion publicacionActualizada = mapearEntidad(publicacionDTO);
		
		return mapearDTO(publicacionActualizada);
	}
	
	@Override
	@Transactional
	public void eliminarPublicacionById(Long id) {
		Publicacion publicacion = publicacionDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
		
		publicacionDao.delete(publicacion);
	}

	// Convierte Entidad a DTO
	private PublicacionDTO mapearDTO(Publicacion publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());

		return publicacionDTO;
	}

	// Convertir DTO a Entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = new Publicacion();

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		return publicacion;
	}

}
