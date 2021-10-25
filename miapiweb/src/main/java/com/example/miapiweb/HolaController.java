package com.example.miapiweb;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	private AtomicInteger contador = new AtomicInteger();
	
	@Autowired
	private ContadorRepository contadorRepository;
	
	@GetMapping("/saludar")
	public String saludar(
			@RequestParam(defaultValue = "Mundo") String nombre,
			@RequestParam(required = false) String apellido) {
		log.info("Petición a /saludar: {} {}", nombre, apellido);
		if (apellido == null) {
			return "Hola " + nombre + " " + contador.incrementAndGet();
		} else {
			return "Hola " + nombre + " " + apellido + " " + contador.incrementAndGet();
		}
	}
	
	@PutMapping("/visita")
	public ResponseEntity<ContadorData> visita(@RequestBody ContadorData visita) {
		if (visita.getVisita() != null) {
			log.error("El primary key de visita no debe estar indicado");
			return ResponseEntity.badRequest().build();
		}
		ContadorData visitaCreada = contadorRepository.save(visita); // UPSERT
		//return "Hola " + nombre + " " + contador.getVisita();
		try {
			URI location = new URI("/visita/" + visitaCreada.getVisita());
			return ResponseEntity.created(location).body(visitaCreada);
		} catch (URISyntaxException e) {
			log.error("Fallo interno al crear la URI", e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/lista-visitas")
	public List<ContadorData> listarVisitar() {
		return contadorRepository.findAll();
	}

	@GetMapping("/visita")
	public Optional<ContadorData> leerVisita(@RequestParam Integer id) {
		return contadorRepository.findById(id);
	}
	
	@GetMapping("/visita/{id}")
	public ResponseEntity<ContadorData> leerVisitaRestful(@PathVariable String id) {
		try {
			Integer idAsInteger = Integer.valueOf(id);
			Optional<ContadorData> contador = contadorRepository.findById(idAsInteger);
			if (contador.isPresent()) {
				return ResponseEntity.ok().body(contador.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (NumberFormatException e) {
			log.error("El ID {} no es un numero válido", id, e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/visita")
	public ResponseEntity<ContadorData> editarVisita(@RequestBody ContadorData visita) {
		if (visita.getVisita() == null) {
			return ResponseEntity.badRequest().build();
		}
		if (!contadorRepository.existsById(visita.getVisita())) {
			return ResponseEntity.notFound().build();
		}
		ContadorData visitaEditada = contadorRepository.save(visita);
		return ResponseEntity.ok(visitaEditada);
	}
		
	@DeleteMapping("/visita/{id}")
	public ResponseEntity<ContadorData> borrarVisita(@PathVariable Integer id) {
		if (contadorRepository.existsById(id)) {
			contadorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}


















