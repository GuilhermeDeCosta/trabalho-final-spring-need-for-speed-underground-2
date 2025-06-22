package br.edu.unicesumar.api.controller;

import br.edu.unicesumar.api.dto.EventoDTO;
import br.edu.unicesumar.api.dto.EventoRequestDTO;
import br.edu.unicesumar.api.dto.InscricaoDTO;
import br.edu.unicesumar.api.dto.InscricaoRequestDTO;
import br.edu.unicesumar.api.service.EventoService;
import br.edu.unicesumar.api.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> getAllEventos() {
        List<EventoDTO> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        EventoDTO evento = eventoService.findById(id);
        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> createEvento(@Valid @RequestBody EventoRequestDTO dto) {
        EventoDTO evento = eventoService.create(dto);
        return new ResponseEntity<>(evento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@PathVariable Long id, 
                                                  @Valid @RequestBody EventoRequestDTO dto) {
        EventoDTO evento = eventoService.update(id, dto);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idEvento}/registrations")
    public ResponseEntity<InscricaoDTO> createInscricao(@PathVariable Long idEvento,
                                                       @Valid @RequestBody InscricaoRequestDTO dto) {
        InscricaoDTO inscricao = inscricaoService.createInscricao(idEvento, dto);
        return new ResponseEntity<>(inscricao, HttpStatus.CREATED);
    }
}