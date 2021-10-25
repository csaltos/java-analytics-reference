package com.example.ejemplodedb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductoData {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nombre;
	
	private String description;
	
	public ProductoData() {
	}

	public ProductoData(String nombre, String description) {
		this.nombre = nombre;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductoData [id=" + id + ", nombre=" + nombre + ", description=" + description + "]";
	}

}








