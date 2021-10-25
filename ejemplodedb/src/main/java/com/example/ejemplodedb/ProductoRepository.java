package com.example.ejemplodedb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoData, Integer> {
	
	public List<ProductoData> findByNombre(String nombre);

}
