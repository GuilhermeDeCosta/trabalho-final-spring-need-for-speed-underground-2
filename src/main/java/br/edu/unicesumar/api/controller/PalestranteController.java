package br.edu.unicesumar.api.controller;

import br.edu.unicesumar.api.dto.PalestranteDTO;
import br.edu.unicesumar.api.service.PalestranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
public class PalestranteController {

    @Autowired
    private PalestranteService palestranteService;

    @GetMapping
    public ResponseEntity<List<PalestranteDTO>> getAllPalestrantes() {
        List<PalestranteDTO> palestrantes = palestranteService.findAll();
        return ResponseEntity.ok(palestrantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalestranteDTO> getPalestranteById(@PathVariable Long id) {
        PalestranteDTO palestrante = palestranteService.findById(id);
        return ResponseEntity.ok(palestrante);
    }

    @PostMapping
    public ResponseEntity<PalestranteDTO> createPalestrante(@Valid @RequestBody PalestranteDTO dto) {
        PalestranteDTO palestrante = palestranteService.create(dto);
        return new ResponseEntity<>(palestrante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PalestranteDTO> updatePalestrante(@PathVariable Long id, 
                                                           @Valid @RequestBody PalestranteDTO dto) {
        PalestranteDTO palestrante = palestranteService.update(id, dto);
        return ResponseEntity.ok(palestrante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePalestrante(@PathVariable Long id) {
        palestranteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}