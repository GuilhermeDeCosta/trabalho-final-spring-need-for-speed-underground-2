package br.edu.unicesumar.api.dto;

public class PalestranteSimpleDTO {
    private Long id;
    private String nome;
    private String instituicao;
    
    public PalestranteSimpleDTO() {}
    
    public PalestranteSimpleDTO(Long id, String nome, String instituicao) {
        this.id = id;
        this.nome = nome;
        this.instituicao = instituicao;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }
}