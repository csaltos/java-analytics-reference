package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

public class EjemplosOptional {

	public static void main(String[] args) {
		// Optional (es un Monad)
		
		final Optional<String> sOptional = Optional.ofNullable("Valor");
		final Optional<Object> oOptional = Optional.ofNullable(null);
		
		System.out.println("s: " + sOptional.orElseGet(() -> getDefault()));
		System.out.println("o: " + oOptional.orElse("Valor"));
		
		// oOptional.orElseThrow(() -> new RuntimeException("El valor es requerido"));
		
		if (sOptional.isPresent()) {
			System.out.println("s: " + sOptional.get());
		} else {
			System.out.println("s está vacío");
		}

		if (oOptional.isPresent()) {
			System.out.println("o: " + oOptional.get());
		} else {
			System.out.println("o está vacío");
		}
		
		sOptional.ifPresent(valor -> System.out.println(valor));
		sOptional.ifPresent(System.out::println);
		
		oOptional.ifPresent(System.out::println);
		
		// EJERCICIO: creamos un metodo que devuelve un optional de string que a veces está vacío y a veces está lleno
		// y pintamos el valor en la pantalla cuando está lleno o un valor por defecto cuando está vacío
		// (10 minutos)
		
		/*final Optional<String> optional = getOptional();
		
		if (optional.isPresent()) {
			System.out.println("Valor del optional: " + optional.get());
		} else {
			System.out.println("Valor por defecto: ALTERNATIVO");
		}*/
		
		System.out.println("Valor del optional: " + getOptional().orElse("ALTERNATIVO"));
		
		Optional<String> sFiltrado = sOptional.filter(s -> s.indexOf("x") >= 0);
		
		System.out.println("s orignal: " + sOptional);
		System.out.println("s filtrado: " + sFiltrado);
		
		Optional<Integer> numero = Optional.of("1").map(s -> Integer.valueOf(s));
		numero.ifPresent(System.out::println);
		
		Optional<Integer> otroNumero = Optional.of("2").flatMap(s -> convertirAEntero(s));
		System.out.println("otro numero: " + otroNumero);
			
	}
	
	private static Optional<Integer> convertirAEntero(String s) {
		try {
			return Optional.ofNullable(Integer.valueOf(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
	
	private static String getDefault() {
		throw new RuntimeException("Ejemplo de error de base de datos");
	}
	
	private static Optional<String> getOptional() {
		try {
			if (SecureRandom.getInstanceStrong().nextBoolean()) {
				return Optional.ofNullable("CONTENIDO");
			} else {
				return Optional.empty();
			}
		} catch (NoSuchAlgorithmException e) {
			return Optional.empty();
		}
	}

}






