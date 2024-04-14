package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController // annotation que declara que este é um controlador REST. Um controlador controla os recursos das entity (objetos que podem representar a tabela do banco de dados)
@RequestMapping(value = "/events") // rota
public class EventController {

	@Autowired
	private EventService eventService;
	
	private final MeterRegistry meterRegistry;
	
	public EventController(MeterRegistry meterRegistry) {
		this.meterRegistry = meterRegistry;
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO eventDto) throws EntityNotFoundException {
		EventDTO event = eventService.update(id, eventDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(eventDto.getId()).toUri();
		
		return ResponseEntity.ok().body(eventDto);
	}
	
	@GetMapping
	public ResponseEntity<List<EventDTO>> findAll(){
		Counter counter = Counter.builder("listar_eventos").tag("listar_eventos", "listarEventos")
				.description("Quantidades de acessos feitos a lista de eventos.").register(meterRegistry);
		counter.increment();
		
		List<EventDTO> eventList = eventService.findAll();
		
		return ResponseEntity.ok().body(eventList);
	}
}
