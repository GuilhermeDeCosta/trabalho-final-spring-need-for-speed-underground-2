package br.edu.unicesumar.api.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "palestrantes")
public class Palestrante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String miniCurriculo;
    
    @Column(nullable = false, length = 100)
    private String instituicao;
    
    @ManyToMany(mappedBy = "palestrantes", fetch = FetchType.LAZY)
    private Set<Evento> eventos;
    
    public Palestrante() {}
    
    public Palestrante(String nome, String miniCurriculo, String instituicao) {
        this.nome = nome;
        this.miniCurriculo = miniCurriculo;
        this.instituicao = instituicao;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMiniCurriculo() {
        return miniCurriculo;
    }
    
    public void setMiniCurriculo(String miniCurriculo) {
        this.miniCurriculo = miniCurriculo;
    }
    
    public String getInstituicao() {
        return instituicao;
    }
    
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    
    public Set<Evento> getEventos() {
        return eventos;
    }
    
    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }
}