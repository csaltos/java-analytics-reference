package com.example.ejemplodedb;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductosApp implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	//@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola Spring");
		
		productoRepository.save(new ProductoData("fresas", "Fruta"));
		productoRepository.save(new ProductoData("cuaderno", "Util escolar"));
		productoRepository.save(new ProductoData("cuaderno", "Otro cuaderno"));
		productoRepository.save(new ProductoData("pelota", "de plastico azul"));
		
		System.out.println("Todos los productos:");
		productoRepository.findAll().forEach(System.out::println);
		
		System.out.println("Los productos con nombre cuaderno");
		productoRepository.findByNombre("cuaderno").forEach(System.out::println);
		
		
		jdbcTemplate.execute("CREATE TABLE clientes (id SERIAL, nombre VARCHAR(40), apellido VARCHAR(40))");
		
		crearRegistro("Ana", "Suarez");
		crearRegistro("Ana", "Perez");
		crearRegistro("Carlos", "Salamanca");
		crearRegistro("Carlos", "Oteo");
		
		try {
			crearRegistro(null, null);
		} catch (LogicaException e) {
			System.err.println("No se pudo crear el registro " + e.getCodigoInterno());
			e.printStackTrace();
		}
		
		/*List<String> apellidos = jdbcTemplate.query("SELECT id, nombre, apellido FROM clientes WHERE nombre = ?",
				new Object[] { "Ana" },
				(rs, rowNum) -> {
					return rs.getString("apellido") + " " + rowNum;
				});
		System.out.println("Apellidos: " + apellidos);*/
		
		jdbcTemplate.queryForStream(
				"SELECT id, nombre, apellido FROM clientes WHERE nombre = ? OR apellido = ?",
				(rs, rowNum) -> new Cliente(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellido")),
				"Ana",
				"Torrez"
				).forEach(System.out::println);
		
		/*PrintWriter out = null;
		try {
			out = new PrintWriter(new File("ejemplo.txt"));
			out.println("Ejemplo de contenido del archivo");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch(Exception e) {
					System.err.print("No se puedo cerrar el archivo ejemplo.txt");
					e.printStackTrace();
				}
			}
		}*/
		
		try(PrintWriter out = new PrintWriter(new File("ejemplo.txt"))) {
			out.println("Ejemplo de contenido del archivo");
		}
		
		try(Scanner in = new Scanner(new File("ejemplo.txt"))) {
			String contenido = in.nextLine();
			System.out.println("Contenido de ejemplo.txt: " + contenido);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Este mensaje siempre sale");
		}
		
		
	}

	private void crearRegistro(String nombre, String apellido) throws LogicaException {
		if (nombre == null) {
			throw new LogicaException(201, "El nombre es requerido");
		}
		if (apellido == null) {
			throw new RuntimeException("El apellido es requerido");
		}
		try {
			jdbcTemplate.update("INSERT INTO clientes(nombre, apellido) VALUES (?, ?)", nombre, apellido);
		} catch (Exception e) {
			throw new LogicaException(500, "Error al insertar en base de datos tabla clientes", e, true, false);
		}
	}
	
}





