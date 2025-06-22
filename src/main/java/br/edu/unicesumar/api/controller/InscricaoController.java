package br.edu.unicesumar.api.controller;

import br.edu.unicesumar.api.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registrations")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelInscricao(@PathVariable Long id) {
        inscricaoService.cancelInscricao(id);
        return ResponseEntity.noContent().build();
    }
}