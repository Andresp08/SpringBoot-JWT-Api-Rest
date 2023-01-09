package com.co.andresoft.apirest.model.service;

import java.util.List;

import com.co.andresoft.apirest.dto.ComentarioDTO;

public interface IComentarioService {

	public ComentarioDTO crearComentario(Long publicacionId, ComentarioDTO comentarioDTO);
	
	public List<ComentarioDTO> obtenerComentariosByPublicacionId(Long publicacionId);
	
	public ComentarioDTO obtenerComentarioById(Long publicacionId, Long comentarioId);
	
	public ComentarioDTO actualizarComentarioById(Long publicacionId, Long comentarioId, 
			ComentarioDTO nuevoComentario);
	
	public void eliminarComentarioById(Long publicacionId, Long comentarioId);
}
