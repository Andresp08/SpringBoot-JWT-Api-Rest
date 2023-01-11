package com.co.andresoft.apirest.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresoft.apirest.model.entity.Rol;

public interface IRolDao extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre); 
	
}
