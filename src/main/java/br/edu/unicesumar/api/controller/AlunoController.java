package br.edu.unicesumar.api.controller;

import br.edu.unicesumar.api.dto.AlunoDTO;
import br.edu.unicesumar.api.dto.InscricaoDTO;
import br.edu.unicesumar.api.service.AlunoService;
import br.edu.unicesumar.api.service.InscricaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAllAlunos() {
        List<AlunoDTO> alunos = alunoService.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id) {
        AlunoDTO aluno = alunoService.findById(id);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> createAluno(@Valid @RequestBody AlunoDTO dto) {
        AlunoDTO aluno = alunoService.create(dto);
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, 
                                               @Valid @RequestBody AlunoDTO dto) {
        AlunoDTO aluno = alunoService.update(id, dto);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idAluno}/registrations")
    public ResponseEntity<List<InscricaoDTO>> getInscricoesByAluno(@PathVariable Long idAluno) {
        List<InscricaoDTO> inscricoes = inscricaoService.findInscricoesByAluno(idAluno);
        return ResponseEntity.ok(inscricoes);
    }
}