package br.edu.unicesumar.api.dto;

public class DepartamentoReportDTO {
    private Long id;
    private String nome;
    private String sigla;
    private String responsavel;
    private Long totalEventos;
    private Long totalInscritos;
    
    public DepartamentoReportDTO() {}
    
    public DepartamentoReportDTO(Long id, String nome, String sigla, String responsavel, 
                                Long totalEventos, Long totalInscritos) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.responsavel = responsavel;
        this.totalEventos = totalEventos;
        this.totalInscritos = totalInscritos;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
    
    public Long getTotalEventos() { return totalEventos; }
    public void setTotalEventos(Long totalEventos) { this.totalEventos = totalEventos; }
    
    public Long getTotalInscritos() { return totalInscritos; }
    public void setTotalInscritos(Long totalInscritos) { this.totalInscritos = totalInscritos; }
}