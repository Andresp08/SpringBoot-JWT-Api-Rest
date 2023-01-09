package com.co.andresoft.apirest.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresoft.apirest.dto.ComentarioDTO;
import com.co.andresoft.apirest.exception.ResourceNotFoundException;
import com.co.andresoft.apirest.model.dao.IComentarioDao;
import com.co.andresoft.apirest.model.dao.IPublicacionDao;
import com.co.andresoft.apirest.model.entity.Comentario;
import com.co.andresoft.apirest.model.entity.Publicacion;

@Service
public class ComentarioServiceImpl implements IComentarioService {

	@Autowired
	private IComentarioDao comentarioDao;

	@Autowired
	private IPublicacionDao publicacionDao;

	@Override
	@Transactional
	public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {

		Comentario comentario = mapearEntidad(comentarioDTO);

		Publicacion publicacion = publicacionDao.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		comentario.setPublicacion(publicacion);

		Comentario nuevoComentario = comentarioDao.save(comentario);

		return mapearDTO(nuevoComentario);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ComentarioDTO> obtenerComentariosByPublicacionId(Long publicacionId) {
		
		List<Comentario> comentarios = comentarioDao.findByPublicacionId(publicacionId);
		
		return comentarios.stream().map(comentario -> mapearDTO(comentario))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public ComentarioDTO obtenerComentarioById(Long publicacionId, Long comentarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	// Convierte Entidad a DTO
	private ComentarioDTO mapearDTO(Comentario comentario) {
		ComentarioDTO comentarioDTO = new ComentarioDTO();

		comentarioDTO.setId(comentario.getId());
		comentarioDTO.setNombre(comentario.getNombre());
		comentarioDTO.setEmail(comentario.getEmail());
		comentarioDTO.setCuerpo(comentario.getCuerpo());

		return comentarioDTO;
	}

	// Convertir DTO a Entidad
	private Comentario mapearEntidad(ComentarioDTO comentarioDTO) {
		Comentario comentario = new Comentario();

		comentario.setId(comentarioDTO.getId());
		comentario.setNombre(comentarioDTO.getNombre());
		comentario.setEmail(comentarioDTO.getEmail());
		comentario.setCuerpo(comentarioDTO.getCuerpo());

		return comentario;
	}

}