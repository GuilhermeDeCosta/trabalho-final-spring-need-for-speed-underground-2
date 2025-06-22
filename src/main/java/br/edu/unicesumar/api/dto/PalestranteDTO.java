package br.edu.unicesumar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PalestranteDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Mini currículo é obrigatório")
    private String miniCurriculo;
    
    @NotBlank(message = "Instituição é obrigatória")
    @Size(max = 100, message = "Instituição deve ter no máximo 100 caracteres")
    private String instituicao;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getMiniCurriculo() { return miniCurriculo; }
    public void setMiniCurriculo(String miniCurriculo) { this.miniCurriculo = miniCurriculo; }
    
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
}