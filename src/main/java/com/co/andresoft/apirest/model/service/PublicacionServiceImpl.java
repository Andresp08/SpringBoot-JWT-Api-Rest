package com.co.andresoft.apirest.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.model.dao.IPublicacionDao;
import com.co.andresoft.apirest.model.entity.Publicacion;

@Service
public class PublicacionServiceImpl implements IPublicacionService{

	@Autowired
	private IPublicacionDao publicacionDao;
	
	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		//convertir de DTO a Entity
		Publicacion publicacion = new Publicacion();
		
		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());
		
		Publicacion nuevaPublicacion = publicacionDao.save(publicacion);
		
		//Convertir de Entity a DTO
		PublicacionDTO publicacionRespuesta = new PublicacionDTO();
		publicacionRespuesta.setId(nuevaPublicacion.getId());
		publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
		publicacionRespuesta.setDescripcion(nuevaPublicacion.getDescripcion());
		publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());
		
		
		return publicacionRespuesta;
	}

}

