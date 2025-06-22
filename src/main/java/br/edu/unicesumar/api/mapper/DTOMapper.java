package br.edu.unicesumar.api.mapper;

import br.edu.unicesumar.api.dto.*;
import br.edu.unicesumar.api.entity.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    public EventoDTO toEventoDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setNome(evento.getNome());
        dto.setDescricao(evento.getDescricao());
        dto.setData(evento.getData());
        dto.setLimiteParticipantes(evento.getLimiteParticipantes());
        dto.setDepartamentoId(evento.getDepartamento().getId());
        dto.setDepartamentoNome(evento.getDepartamento().getNome());
        dto.setNumeroInscritos(evento.getNumeroInscritos());
        dto.setLotado(evento.isLotado());
        dto.setVagasDisponiveis(evento.hasVagasDisponiveis());
        
        if (evento.getPalestrantes() != null) {
            Set<PalestranteSimpleDTO> palestrantes = evento.getPalestrantes().stream()
                .map(p -> new PalestranteSimpleDTO(p.getId(), p.getNome(), p.getInstituicao()))
                .collect(Collectors.toSet());
            dto.setPalestrantes(palestrantes);
        }
        
        return dto;
    }

    public DepartamentoDTO toDepartamentoDTO(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNome(departamento.getNome());
        dto.setSigla(departamento.getSigla());
        dto.setResponsavel(departamento.getResponsavel());
        return dto;
    }

    public Departamento toDepartamentoEntity(DepartamentoDTO dto) {
        Departamento departamento = new Departamento();
        departamento.setId(dto.getId());
        departamento.setNome(dto.getNome());
        departamento.setSigla(dto.getSigla());
        departamento.setResponsavel(dto.getResponsavel());
        return departamento;
    }

    public PalestranteDTO toPalestranteDTO(Palestrante palestrante) {
        PalestranteDTO dto = new PalestranteDTO();
        dto.setId(palestrante.getId());
        dto.setNome(palestrante.getNome());
        dto.setMiniCurriculo(palestrante.getMiniCurriculo());
        dto.setInstituicao(palestrante.getInstituicao());
        return dto;
    }

    public Palestrante toPalestranteEntity(PalestranteDTO dto) {
        Palestrante palestrante = new Palestrante();
        palestrante.setId(dto.getId());
        palestrante.setNome(dto.getNome());
        palestrante.setMiniCurriculo(dto.getMiniCurriculo());
        palestrante.setInstituicao(dto.getInstituicao());
        return palestrante;
    }

    public AlunoDTO toAlunoDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setMatricula(aluno.getMatricula());
        dto.setCurso(aluno.getCurso());
        return dto;
    }

    public Aluno toAlunoEntity(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setCurso(dto.getCurso());
        return aluno;
    }

    public InscricaoDTO toInscricaoDTO(Inscricao inscricao) {
        InscricaoDTO dto = new InscricaoDTO();
        dto.setId(inscricao.getId());
        dto.setDataInscricao(inscricao.getDataInscricao());
        dto.setStatus(inscricao.getStatus());
        dto.setEvento(toEventoDTO(inscricao.getEvento()));
        dto.setAluno(toAlunoDTO(inscricao.getAluno()));
        return dto;
    }

    public DepartamentoReportDTO toDepartamentoReportDTO(Departamento departamento, 
                                                        Long totalEventos, Long totalInscritos) {
        return new DepartamentoReportDTO(
            departamento.getId(),
            departamento.getNome(),
            departamento.getSigla(),
            departamento.getResponsavel(),
            totalEventos,
            totalInscritos
        );
    }
}