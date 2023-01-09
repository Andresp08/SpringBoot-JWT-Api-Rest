package com.co.andresoft.apirest.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresoft.apirest.model.entity.Comentario;

public interface IComentarioDao extends JpaRepository<Comentario, Long>{

	public List<Comentario> findByPublicacionId(Long id);
	
}
