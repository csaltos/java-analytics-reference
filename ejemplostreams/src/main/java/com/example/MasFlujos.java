package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MasFlujos {

	public static void main(String[] args) {
		System.out.println("Flujo original:");
		crearFlujo().sorted().forEach(nombre -> System.out.println(nombre));
		System.out.println("Flujo ordenado:");
		crearFlujo().sorted().forEach(System.out::println);
		System.out.println("Nombres con un punto (tradicional):");
		crearFlujo().sorted().forEach(nombre -> System.out.println(nombre + "."));
		System.out.println("Nombres con un punto (funcional):");
		crearFlujo().map(nombre -> nombre + ".").forEach(System.out::println);
		System.out.println("Ordenar en sentido inverso:");
		crearFlujo().sorted((s1, s2) -> s2.compareTo(s1)).forEach(System.out::println);
		System.out.println("Ordenar por tamaño");
		crearFlujo().sorted((s1, s2) -> s1.length() - s2.length()).forEach(System.out::println);
		System.out.println("Flujo de enteros ordenado:");
		crearFlujoDeEnteros().sorted().forEach(System.out::println);
		System.out.println("Conteo de elementos:");
		final long conteo = crearFlujoDeEnteros().count();
		System.out.println(conteo);
		System.out.println("Descartar los 3 primeros elementos:");
		crearFlujo().skip(3).forEach(System.out::println);
		
		System.out.println("Calcular la suma:");
		final int suma = crearFlujoDeEnteros().reduce(0, (n1, n2) -> n1 + n2);
		System.out.println(suma);
		
		System.out.println("Suma como IntSream: ");
		IntStream flujo2 = IntStream.of(1, 2, 3);
		System.out.println(flujo2.sum());
		
		System.out.println("Estadísticas de un IntStream: ");
		IntStream flujo3 = IntStream.of(1, 2, 3);
		System.out.println(flujo3.summaryStatistics());
		
		IntStream enterosComoIntStream = crearFlujoDeEnteros().mapToInt(x -> x);
		System.out.println("La suma como IntStream convertido es: " + enterosComoIntStream.sum());
		
		IntStream enterosDesdeStrings = Stream.of("1", "2", "9").mapToInt(s -> Integer.valueOf(s));
		System.out.println("Suma convirtiendo de cadenas a enteros: " + enterosDesdeStrings.sum());
		
		System.out.println("Enteros concatenados con espacios");
		String enterosEnUnaCadana = crearFlujoDeEnteros().map(n -> String.valueOf(n)).reduce("resultado:", (s1, s2) -> s1 + " " + s2);
		System.out.println(enterosEnUnaCadana);
		
		Optional<Integer> s = crearFlujoDeEnteros().reduce((x, y) -> x + y);
		System.out.println("s: " + s);

		Integer s2 = crearFlujoDeEnteros().reduce(0, (acc, x) -> acc + x);
		System.out.println("s2: " + s2);

		String a = crearFlujoDeEnteros().reduce("", (acc, x) -> acc + (x + 2), (x, y) -> x + y.toString());
		System.out.println("a: " + a);

		// EJERCICIO: Flujo de floats donde filtramos los negativos, les calculamos el cuadrado y los imprimimos en la consola
		
		/*
		List<Float> arrayFloats = new ArrayList<>();
		arrayFloats.add((1.0F);
		arrayFloats.add(-3.5F);
		arrayFloats.add(2.7F);
		arrayFloats.add(3.5F);
		arrayFloats.stream();*/
		
		Stream.of(1.0F, -3.5F, 2.7F, 3.5F, -8.0F)
			.parallel()
			.filter(item -> item < 0)
			.map(negativo -> negativo * negativo)
			.forEachOrdered(System.out::println);
		
		/*Stream.of(1.0F, -3.5F, 2.7F, 3.5F, -8.0F)
			.parallel()
			.filter(item -> item < 0)
			.map(negativo -> negativo * negativo)
			.collect(Collectors.toSet());*/
	}
	
	private static Stream<String> crearFlujo() {
		return Stream.of("Ana", "Fernando", "Manuel", "Jose", "Cristina");
	}
	
	private static Stream<Integer> crearFlujoDeEnteros() {
		return Stream.of(1, 5, 4, 3, -2, 0, 5, 9);
	}
	
}
