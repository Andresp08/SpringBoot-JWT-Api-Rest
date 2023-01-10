package com.co.andresoft.apirest.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.andresoft.apirest.dto.PublicacionDTO;
import com.co.andresoft.apirest.dto.PublicacionRespuesta;
import com.co.andresoft.apirest.exception.ResourceNotFoundException;
import com.co.andresoft.apirest.model.dao.IPublicacionDao;
import com.co.andresoft.apirest.model.entity.Publicacion;

@Service
public class PublicacionServiceImpl implements IPublicacionService {
	
	@Autowired
	private ModelMapper modelMapper;

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
	public PublicacionRespuesta obtenerPublicaciones(int numeroPagina, int cantidadRegistros, String ordenarPor,
			String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending()
				: Sort.by(ordenarPor).descending();

		Pageable pageable = PageRequest.of(numeroPagina, cantidadRegistros, sort);

		Page<Publicacion> publicaciones = publicacionDao.findAll(pageable);

		List<Publicacion> listaPublicaciones = publicaciones.getContent();

		List<PublicacionDTO> contenido = listaPublicaciones.stream().map(publicacion -> mapearDTO(publicacion))
				.collect(Collectors.toList());

		PublicacionRespuesta respuesta = new PublicacionRespuesta();
		respuesta.setContenido(contenido);
		respuesta.setNumeroPagina(publicaciones.getNumber());
		respuesta.setCantidadRegistros(publicaciones.getSize());
		respuesta.setTotalElementos(publicaciones.getTotalElements());
		respuesta.setTotalPaginas(publicaciones.getTotalPages());
		respuesta.setUltima(publicaciones.isLast());

		return respuesta;
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
		PublicacionDTO publicacionDTO = modelMapper.map(publicacion, PublicacionDTO.class);
		return publicacionDTO;
	}

	// Convertir DTO a Entidad
	private Publicacion mapearEntidad(PublicacionDTO publicacionDTO) {
		Publicacion publicacion = modelMapper.map(publicacionDTO, Publicacion.class);
		return publicacion;
	}

}
