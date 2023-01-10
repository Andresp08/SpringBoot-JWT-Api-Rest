package com.co.andresoft.apirest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {

	private Long id;

	@NotEmpty(message = "El nombre no debe ir en vacio")
	@NotBlank(message = "El nombre no debe ir en blanco")
	@Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
	private String nombre;

	@Email(message = "Por favor ingresa un email correcto")
	@NotEmpty(message = "El email no debe ir vacio")
	@NotBlank(message = "El email no debe ir en blanco")
	private String email;

	@NotEmpty(message = "El cuerpo del comentario no debe ir vacio")
	@NotBlank(message = "El cuerpo del comentario no debe ir en blanco")
	@Size(min = 6, message = "El cuerpo del comentario debe tener al menos 6 caracteres")
	private String cuerpo;

	public ComentarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

}
