package br.edu.unicesumar.api.service;

import br.edu.unicesumar.api.dto.InscricaoDTO;
import br.edu.unicesumar.api.dto.InscricaoRequestDTO;
import br.edu.unicesumar.api.entity.Aluno;
import br.edu.unicesumar.api.entity.Evento;
import br.edu.unicesumar.api.entity.Inscricao;
import br.edu.unicesumar.api.entity.Inscricao.StatusInscricao;
import br.edu.unicesumar.api.exception.InvalidOperationException;
import br.edu.unicesumar.api.exception.ResourceNotFoundException;
import br.edu.unicesumar.api.mapper.DTOMapper;
import br.edu.unicesumar.api.repository.AlunoRepository;
import br.edu.unicesumar.api.repository.EventoRepository;
import br.edu.unicesumar.api.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DTOMapper mapper;

    public InscricaoDTO createInscricao(Long eventoId, InscricaoRequestDTO dto) {

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + eventoId));

        Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + dto.getAlunoId()));

        if (evento.isDataPassada()) {
            throw new InvalidOperationException("Não é possível se inscrever em um evento com data já passada");
        }

        if (evento.isLotado()) {
            throw new InvalidOperationException("Evento está lotado");
        }


        Optional<Inscricao> inscricaoExistente = inscricaoRepository.findByEventoIdAndAlunoId(eventoId, dto.getAlunoId());
        if (inscricaoExistente.isPresent()) {
            if (inscricaoExistente.get().getStatus() == StatusInscricao.ATIVO) {
                throw new InvalidOperationException("Aluno já está inscrito neste evento");
            }

            Inscricao inscricao = inscricaoExistente.get();
            inscricao.setStatus(StatusInscricao.ATIVO);
            inscricao.setDataInscricao(LocalDateTime.now()); 
            inscricao = inscricaoRepository.save(inscricao);
            return mapper.toInscricaoDTO(inscricao);
        }

        List<Inscricao> inscricoesAtivas = inscricaoRepository.findByAlunoIdAndStatus(dto.getAlunoId(), StatusInscricao.ATIVO);
        for (Inscricao inscricaoAtiva : inscricoesAtivas) {
            if (temConflitoDeHorario(evento, inscricaoAtiva.getEvento())) {
                throw new InvalidOperationException("Aluno já tem um evento no mesmo horário");
            }
        }

        Inscricao inscricao = new Inscricao(evento, aluno);
        inscricao = inscricaoRepository.save(inscricao);
        return mapper.toInscricaoDTO(inscricao);
    }

    @Transactional(readOnly = true)
    public List<InscricaoDTO> findInscricoesByAluno(Long alunoId) {

        if (!alunoRepository.existsById(alunoId)) {
            throw new ResourceNotFoundException("Aluno não encontrado com ID: " + alunoId);
        }

        List<Inscricao> inscricoes = inscricaoRepository.findByAlunoIdAndStatus(alunoId, StatusInscricao.ATIVO);
        return inscricoes.stream()
                .map(mapper::toInscricaoDTO)
                .collect(Collectors.toList());
    }

    public void cancelInscricao(Long inscricaoId) {
        Inscricao inscricao = inscricaoRepository.findById(inscricaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada com ID: " + inscricaoId));

        if (inscricao.getStatus() == StatusInscricao.CANCELADO) {
            throw new InvalidOperationException("Inscrição já está cancelada");
        }

        inscricao.setStatus(StatusInscricao.CANCELADO);
        inscricaoRepository.save(inscricao);
    }

    private boolean temConflitoDeHorario(Evento evento1, Evento evento2) {

        LocalDateTime data1 = evento1.getData();
        LocalDateTime data2 = evento2.getData();
        
        return data1.toLocalDate().equals(data2.toLocalDate());
    }
}