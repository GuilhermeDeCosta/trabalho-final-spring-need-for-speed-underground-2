package br.edu.unicesumar.api.controller;

import br.edu.unicesumar.api.dto.DepartamentoDTO;
import br.edu.unicesumar.api.dto.DepartamentoReportDTO;
import br.edu.unicesumar.api.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> getAllDepartamentos() {
        List<DepartamentoDTO> departamentos = departamentoService.findAll();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> getDepartamentoById(@PathVariable Long id) {
        DepartamentoDTO departamento = departamentoService.findById(id);
        return ResponseEntity.ok(departamento);
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> createDepartamento(@Valid @RequestBody DepartamentoDTO dto) {
        DepartamentoDTO departamento = departamentoService.create(dto);
        return new ResponseEntity<>(departamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> updateDepartamento(@PathVariable Long id, 
                                                             @Valid @RequestBody DepartamentoDTO dto) {
        DepartamentoDTO departamento = departamentoService.update(id, dto);
        return ResponseEntity.ok(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/report")
    public ResponseEntity<DepartamentoReportDTO> getDepartamentoReport(@PathVariable Long id) {
        DepartamentoReportDTO report = departamentoService.getReport(id);
        return ResponseEntity.ok(report);
    }
}