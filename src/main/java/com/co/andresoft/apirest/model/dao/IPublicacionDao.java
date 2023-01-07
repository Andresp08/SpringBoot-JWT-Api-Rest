package com.co.andresoft.apirest.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresoft.apirest.model.entity.Publicacion;

public interface IPublicacionDao extends JpaRepository<Publicacion, Long>{
	
}
