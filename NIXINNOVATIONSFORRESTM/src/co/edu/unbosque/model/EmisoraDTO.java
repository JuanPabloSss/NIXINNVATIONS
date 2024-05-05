package co.edu.unbosque.model;

import java.util.*;

public class EmisoraDTO {
	
	private UUID id;
	private String nombre;
	private String genero;
	private String modoTransmision;
	
	public EmisoraDTO () {
		
	}

	public EmisoraDTO(UUID id, String nombre, String genero, String modoTransmision) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.modoTransmision = modoTransmision;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getModoTransmision() {
		return modoTransmision;
	}

	public void setModoTransmision(String modoTransmision) {
		this.modoTransmision = modoTransmision;
	}
	
	

}
	