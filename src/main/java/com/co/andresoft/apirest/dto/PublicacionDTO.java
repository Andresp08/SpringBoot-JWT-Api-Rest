package com.co.andresoft.apirest.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.co.andresoft.apirest.model.entity.Comentario;

public class PublicacionDTO {

	private Long id;
	
	@NotEmpty
	@NotBlank(message = "El titulo de la publicación no debe ir en blanco")
	@Size(min = 2, message = "El titulo de la publicación debe tener al menos 2 caracteres")
	private String titulo;
	
	@NotEmpty
	@NotBlank(message = "La descripción de la publicación no debe ir en blanco")
	@Size(min = 10, message = "La descripción de la publicación debe tener al menos 10 caracteres")
	private String descripcion;
	
	@NotEmpty
	@NotBlank(message = "El contenido de la publicación no debe ir en blanco")
	private String contenido;
	
	private Set<Comentario> comentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public PublicacionDTO() {
		super();
	}

}
