package br.edu.unicesumar.api.service;

import br.edu.unicesumar.api.dto.PalestranteDTO;
import br.edu.unicesumar.api.entity.Palestrante;
import br.edu.unicesumar.api.exception.InvalidOperationException;
import br.edu.unicesumar.api.exception.ResourceNotFoundException;
import br.edu.unicesumar.api.mapper.DTOMapper;
import br.edu.unicesumar.api.repository.PalestranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PalestranteService {

    @Autowired
    private PalestranteRepository palestranteRepository;

    @Autowired
    private DTOMapper mapper;

    @Transactional(readOnly = true)
    public List<PalestranteDTO> findAll() {
        return palestranteRepository.findAll().stream()
                .map(mapper::toPalestranteDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PalestranteDTO findById(Long id) {
        Palestrante palestrante = palestranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestrante não encontrado com ID: " + id));
        return mapper.toPalestranteDTO(palestrante);
    }

    public PalestranteDTO create(PalestranteDTO dto) {
        Palestrante palestrante = mapper.toPalestranteEntity(dto);
        palestrante = palestranteRepository.save(palestrante);
        return mapper.toPalestranteDTO(palestrante);
    }

    public PalestranteDTO update(Long id, PalestranteDTO dto) {
        Palestrante palestrante = palestranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Palestrante não encontrado com ID: " + id));
        
        palestrante.setNome(dto.getNome());
        palestrante.setMiniCurriculo(dto.getMiniCurriculo());
        palestrante.setInstituicao(dto.getInstituicao());
        
        palestrante = palestranteRepository.save(palestrante);
        return mapper.toPalestranteDTO(palestrante);
    }

    public void delete(Long id) {
        if (!palestranteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Palestrante não encontrado com ID: " + id);
        }
        
        Long eventosCount = palestranteRepository.countEventosByPalestranteId(id);
        if (eventosCount > 0) {
            throw new InvalidOperationException("Não é possível excluir um palestrante que está vinculado a eventos");
        }
        
        palestranteRepository.deleteById(id);
    }
}