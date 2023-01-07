package com.co.andresoft.apirest.model.service;

import java.util.List;

import com.co.andresoft.apirest.dto.PublicacionDTO;

public interface IPublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public List<PublicacionDTO> obtenerPublicaciones();
	
	public PublicacionDTO obtenerPublicacionById(Long id);
	
	public PublicacionDTO actualizarPublicacionById(PublicacionDTO publicacionDTO,Long id);
	
	public void eliminarPublicacionById(Long id);

}
