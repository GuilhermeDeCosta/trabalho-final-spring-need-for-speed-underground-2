package br.edu.unicesumar.api.dto;

import jakarta.validation.constraints.NotNull;

public class InscricaoRequestDTO {
    
    @NotNull(message = "ID do aluno é obrigatório")
    private Long alunoId;
    
    public InscricaoRequestDTO() {}
    
    public InscricaoRequestDTO(Long alunoId) {
        this.alunoId = alunoId;
    }
    
    public Long getAlunoId() { return alunoId; }
    public void setAlunoId(Long alunoId) { this.alunoId = alunoId; }
}