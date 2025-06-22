package br.edu.unicesumar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartamentoDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Sigla é obrigatória")
    @Size(max = 10, message = "Sigla deve ter no máximo 10 caracteres")
    private String sigla;
    
    @NotBlank(message = "Responsável é obrigatório")
    @Size(max = 100, message = "Responsável deve ter no máximo 100 caracteres")
    private String responsavel;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
}