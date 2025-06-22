package br.edu.unicesumar.api.service;

import br.edu.unicesumar.api.dto.DepartamentoDTO;
import br.edu.unicesumar.api.dto.DepartamentoReportDTO;
import br.edu.unicesumar.api.entity.Departamento;
import br.edu.unicesumar.api.exception.ResourceNotFoundException;
import br.edu.unicesumar.api.mapper.DTOMapper;
import br.edu.unicesumar.api.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private DTOMapper mapper;

    @Transactional(readOnly = true)
    public List<DepartamentoDTO> findAll() {
        return departamentoRepository.findAll().stream()
                .map(mapper::toDepartamentoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepartamentoDTO findById(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com ID: " + id));
        return mapper.toDepartamentoDTO(departamento);
    }

    public DepartamentoDTO create(DepartamentoDTO dto) {
        Departamento departamento = mapper.toDepartamentoEntity(dto);
        departamento = departamentoRepository.save(departamento);
        return mapper.toDepartamentoDTO(departamento);
    }

    public DepartamentoDTO update(Long id, DepartamentoDTO dto) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com ID: " + id));
        
        departamento.setNome(dto.getNome());
        departamento.setSigla(dto.getSigla());
        departamento.setResponsavel(dto.getResponsavel());
        
        departamento = departamentoRepository.save(departamento);
        return mapper.toDepartamentoDTO(departamento);
    }

    public void delete(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Departamento não encontrado com ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public DepartamentoReportDTO getReport(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com ID: " + id));
        
        Long totalEventos = departamentoRepository.countEventosByDepartamentoId(id);
        Long totalInscritos = departamentoRepository.countInscricoesByDepartamentoId(id);
        
        return mapper.toDepartamentoReportDTO(departamento, totalEventos, totalInscritos);
    }
}