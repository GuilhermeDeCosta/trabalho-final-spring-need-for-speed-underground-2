package br.edu.unicesumar.api.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamentos")
public class Departamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 10, unique = true)
    private String sigla;
    
    @Column(nullable = false, length = 100)
    private String responsavel;
    
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evento> eventos;
    
    public Departamento() {}
    
    public Departamento(String nome, String sigla, String responsavel) {
        this.nome = nome;
        this.sigla = sigla;
        this.responsavel = responsavel;
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
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    public String getResponsavel() {
        return responsavel;
    }
    
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
    public List<Evento> getEventos() {
        return eventos;
    }
    
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}