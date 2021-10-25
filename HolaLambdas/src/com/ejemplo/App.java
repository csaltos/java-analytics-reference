package com.ejemplo;

class Programa implements Runnable {
	
	@Override
	public void run() {
		System.out.println("Esto ejecuta desde otro hilo de proceso: " +
				Thread.currentThread().getName());
	}
	
}

public class App {

	public static void main(String[] args) {
		System.out.println("Hola Mundo desde " +
				Thread.currentThread().getName());
		
		Programa programa1 = new Programa();
		Thread ejecucion1 = new Thread(programa1);
		ejecucion1.start();

		Programa programa2 = new Programa();
		Thread ejecucion2 = new Thread(programa2);
		ejecucion2.start();
		
		Runnable programaAnonimo = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hola desde clase anónima");
			}
		};
		Thread ejecucion3 = new Thread(programaAnonimo);
		ejecucion3.start();
		
		// Reutilizando codigo vamos a poner Hola Ana, Hola Pepe, Hola Juan
		// (10 minutos)
		// https://github.com/csaltos/newjava
		
		Runnable programa4 = crearPrograma("Carlos");
		Thread ejecucion4 = new Thread(programa4);
		ejecucion4.start();
		
		Runnable programa5 = crearPrograma("Ana");
		Thread ejecucion5 = new Thread(programa5);
		ejecucion5.start();
	
	}

	static Runnable crearPrograma(final String nombre) {
		return () -> System.out.println("Hola " + nombre + " desde una función: " + Thread.currentThread().getName());
	}
	
}












