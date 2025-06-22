package br.edu.unicesumar.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlunoDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;
    
    @NotBlank(message = "Matrícula é obrigatória")
    @Size(max = 20, message = "Matrícula deve ter no máximo 20 caracteres")
    private String matricula;
    
    @NotBlank(message = "Curso é obrigatório")
    @Size(max = 100, message = "Curso deve ter no máximo 100 caracteres")
    private String curso;
    
    public AlunoDTO() {}
    
    public AlunoDTO(Long id, String nome, String matricula, String curso) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}