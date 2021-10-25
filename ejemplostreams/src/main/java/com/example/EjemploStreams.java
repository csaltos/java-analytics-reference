package com.example;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


public class EjemploStreams {

	public static void main(String[] args) {
		Stream<Integer> stream = Stream.iterate(0, n -> n + 1);
		System.out.println("stream: " + stream);
		//int contador = 0;
		AtomicInteger contador = new AtomicInteger();
		System.out.print("[");
		stream.limit(10).forEach(item -> {
			int posicion = contador.getAndIncrement();
			if (posicion == 0) {
				System.out.print("" + item);
			} else {
				System.out.print(", " + item);
			}
		});
		System.out.println("]");
		
		Optional<Integer> suma = Stream.of(1, 2, 6, 5, 9)
				.reduce((acc, item) -> acc + item);
		if (suma.isPresent()) {
			System.out.println("La suma es: " + suma.get());
		} else {
			System.out.println("La suma es 0");
		}
		
		Integer sumaEntero = Stream.of(1, 5, 6, 2, 9)
				.reduce(0, (a, b) -> a + b);
		System.out.println("La suma como entero es: " + sumaEntero);
		
		Integer sumaCombinador = Stream.of(1, 3, 8, 9, 5)
			.reduce(0, (a, b) -> a + b, (a, b) -> a + b);
		System.out.println("La suma con combiandor es: " + sumaCombinador);
		
	}

}
