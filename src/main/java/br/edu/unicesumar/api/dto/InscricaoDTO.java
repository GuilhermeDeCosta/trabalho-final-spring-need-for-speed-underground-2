package br.edu.unicesumar.api.dto;

import br.edu.unicesumar.api.entity.Inscricao.StatusInscricao;
import java.time.LocalDateTime;

public class InscricaoDTO {
    private Long id;
    private LocalDateTime dataInscricao;
    private StatusInscricao status;
    private EventoDTO evento;
    private AlunoDTO aluno;
    
    public InscricaoDTO() {}
    
    public InscricaoDTO(Long id, LocalDateTime dataInscricao, StatusInscricao status, 
                       EventoDTO evento, AlunoDTO aluno) {
        this.id = id;
        this.dataInscricao = dataInscricao;
        this.status = status;
        this.evento = evento;
        this.aluno = aluno;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDateTime dataInscricao) { this.dataInscricao = dataInscricao; }
    
    public StatusInscricao getStatus() { return status; }
    public void setStatus(StatusInscricao status) { this.status = status; }
    
    public EventoDTO getEvento() { return evento; }
    public void setEvento(EventoDTO evento) { this.evento = evento; }
    
    public AlunoDTO getAluno() { return aluno; }
    public void setAluno(AlunoDTO aluno) { this.aluno = aluno; }
}