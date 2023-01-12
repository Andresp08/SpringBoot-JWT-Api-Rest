package com.co.andresoft.apirest.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.andresoft.apirest.dto.LoginDTO;
import com.co.andresoft.apirest.dto.RegistrarUsuarioDTO;
import com.co.andresoft.apirest.model.dao.IRolDao;
import com.co.andresoft.apirest.model.dao.IUsuarioDao;
import com.co.andresoft.apirest.model.entity.Rol;
import com.co.andresoft.apirest.model.entity.Usuario;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRolDao rolDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<>("Ha iniciado sesión con exito", HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<String> registrarUsuario(@RequestBody RegistrarUsuarioDTO registroDTO){
		
		if(usuarioDao.existsByUsername(registroDTO.getUsername()) 
				|| usuarioDao.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Email ó nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		Rol roles = rolDao.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioDao.save(usuario);
		
		return new ResponseEntity<>("Usuario registrado exitosamente!!", HttpStatus.OK);
	}
	
}
