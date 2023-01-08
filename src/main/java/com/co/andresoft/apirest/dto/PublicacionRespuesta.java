package com.co.andresoft.apirest.dto;

import java.util.List;

public class PublicacionRespuesta {

	private List<PublicacionDTO> contenido;
	private int numeroPagina;
	private int cantidadRegistros;
	private Long totalElementos;
	private int totalPaginas;
	private boolean ultima;

	public List<PublicacionDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<PublicacionDTO> contenido) {
		this.contenido = contenido;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public int getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(int cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public Long getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(Long totalElementos) {
		this.totalElementos = totalElementos;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public boolean isUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

}
