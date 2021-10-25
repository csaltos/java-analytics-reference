package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjemploFlujos {

	public static void main(String[] args) {
		final List<Integer> lista = Arrays.asList(1, -2, -3, 4, 5, 9, -8);
		System.out.println("Lista: " + lista);
		lista.forEach(item -> System.out.print(" " + item));
		System.out.println();
		
		for (final Integer item: lista) {
			System.out.print(" " + item);
		}
		System.out.println();
		
		for (int i = 0; i < lista.size(); i++) {
			System.out.print(" " + lista.get(i));
		}
		System.out.println();
		
		// Estilo imperativo de programación
		final List<Integer> positivos = new ArrayList<>();
		for (Integer i: lista) {
			if (i >= 0) {
				positivos.add(i);
			}
		}
		System.out.println("Positivos: " + positivos);

		// Estilo de programación funcional
		final List<Integer> negativos = lista.stream().parallel().filter(item -> item < 0).collect(Collectors.toList());
		System.out.println("Negativos: " + negativos);

		positivos.addAll(lista.stream().parallel().filter(item -> item >= 0).collect(Collectors.toList()));
		System.out.println("Positivos (con stream): " + positivos);

		// Solo flujos
		//System.out.println("Flujo de enteros: " + flujoDeEnteros);
		createFlujoDeEnteros().parallel().forEach(item -> System.out.print(" " + item));
		System.out.println();
		
		createFlujoDeEnteros().parallel().forEachOrdered(item -> System.out.print(" " + item));
		
	}
	
	static Stream<Integer> createFlujoDeEnteros() {
		return Stream.of(1, 2, -3, 5, -9, 0);
	}

}










