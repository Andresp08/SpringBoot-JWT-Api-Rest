package com.co.andresoft.apirest.model.service;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.dto.PublicacionRespuesta;

public interface IPublicacionService {

	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	//public List<PublicacionDTO> obtenerPublicaciones(int numeroPagina, int cantidadRegistros);
	public PublicacionRespuesta obtenerPublicaciones(int numeroPagina, int cantidadRegistros,
			String ordenarPor, String sortDir);
	
	public PublicacionDTO obtenerPublicacionById(Long id);
	
	public PublicacionDTO actualizarPublicacionById(PublicacionDTO publicacionDTO,Long id);
	
	public void eliminarPublicacionById(Long id);

}
