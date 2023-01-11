package com.co.andresoft.apirest.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.andresoft.apirest.model.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email); //T ó F
	
	public Optional<Usuario> findByUsernameOrEmail(String username, String email);
	
	public Optional<Usuario> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
	
}
