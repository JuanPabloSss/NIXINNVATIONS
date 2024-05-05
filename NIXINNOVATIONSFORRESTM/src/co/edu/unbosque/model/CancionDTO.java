package co.edu.unbosque.model;

import java.util.*;

public class CancionDTO{
	
	private UUID id;
	private String nombre;
	private String artista; 
	private String genero;
	private String archivo;
	private boolean isActive;
	
	public CancionDTO() {
		
	}
	
	public CancionDTO(UUID id, String nombre, String artista, String genero, String archivo, boolean isActive) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.archivo = archivo;
		this.isActive = isActive;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	

}
