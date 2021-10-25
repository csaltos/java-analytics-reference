package com.example.miapiweb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContadorData {

	@Id
	@GeneratedValue
	private Integer visita;

	private String nombre;

	public ContadorData() {
	}
	
	public ContadorData(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getVisita() {
		return visita;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "ContadorData [visita=" + visita + ", nombre=" + nombre + "]";
	}

}
