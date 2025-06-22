package br.edu.unicesumar.api.service;

import br.edu.unicesumar.api.dto.EventoDTO;
import br.edu.unicesumar.api.dto.EventoRequestDTO;
import br.edu.unicesumar.api.entity.Departamento;
import br.edu.unicesumar.api.entity.Evento;
import br.edu.unicesumar.api.entity.Palestrante;
import br.edu.unicesumar.api.exception.InvalidOperationException;
import br.edu.unicesumar.api.exception.ResourceNotFoundException;
import br.edu.unicesumar.api.mapper.DTOMapper;
import br.edu.unicesumar.api.repository.DepartamentoRepository;
import br.edu.unicesumar.api.repository.EventoRepository;
import br.edu.unicesumar.api.repository.PalestranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private PalestranteRepository palestranteRepository;

    @Autowired
    private DTOMapper mapper;

    @Transactional(readOnly = true)
    public List<EventoDTO> findAll() {
        return eventoRepository.findAll().stream()
                .map(mapper::toEventoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventoDTO findById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));
        return mapper.toEventoDTO(evento);
    }

    public EventoDTO create(EventoRequestDTO dto) {
        
        if (dto.getData().isBefore(LocalDateTime.now())) {
            throw new InvalidOperationException("A data do evento deve ser futura");
        }

        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com ID: " + dto.getDepartamentoId()));

        Set<Palestrante> palestrantes = new HashSet<>();
        for (Long palestranteId : dto.getPalestrantesIds()) {
            Palestrante palestrante = palestranteRepository.findById(palestranteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Palestrante não encontrado com ID: " + palestranteId));
            palestrantes.add(palestrante);
        }

        Evento evento = new Evento();
        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setData(dto.getData());
        evento.setLimiteParticipantes(dto.getLimiteParticipantes());
        evento.setDepartamento(departamento);
        evento.setPalestrantes(palestrantes);

        evento = eventoRepository.save(evento);
        return mapper.toEventoDTO(evento);
    }

    public EventoDTO update(Long id, EventoRequestDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));

        if (dto.getData().isBefore(LocalDateTime.now())) {
            throw new InvalidOperationException("A data do evento deve ser futura");
        }

        Departamento departamento = departamentoRepository.findById(dto.getDepartamentoId())
                .orElseThrow(() -> new ResourceNotFoundException("Departamento não encontrado com ID: " + dto.getDepartamentoId()));

        Set<Palestrante> palestrantes = new HashSet<>();
        for (Long palestranteId : dto.getPalestrantesIds()) {
            Palestrante palestrante = palestranteRepository.findById(palestranteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Palestrante não encontrado com ID: " + palestranteId));
            palestrantes.add(palestrante);
        }

        evento.setNome(dto.getNome());
        evento.setDescricao(dto.getDescricao());
        evento.setData(dto.getData());
        evento.setLimiteParticipantes(dto.getLimiteParticipantes());
        evento.setDepartamento(departamento);
        evento.setPalestrantes(palestrantes);

        evento = eventoRepository.save(evento);
        return mapper.toEventoDTO(evento);
    }

    public void delete(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento não encontrado com ID: " + id);
        }

        eventoRepository.deleteById(id);
    }
}