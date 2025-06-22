package br.edu.unicesumar.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "eventos")
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
    
    @Column(nullable = false)
    private LocalDateTime data;
    
    @Column(nullable = false)
    private Integer limiteParticipantes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "evento_palestrante",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "palestrante_id")
    )
    private Set<Palestrante> palestrantes;
    
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inscricao> inscricoes;
    
    public Evento() {}
    
    public Evento(String nome, String descricao, LocalDateTime data, Integer limiteParticipantes, Departamento departamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.limiteParticipantes = limiteParticipantes;
        this.departamento = departamento;
    }
    
    public long getNumeroInscritos() {
        if (inscricoes == null) return 0;
        return inscricoes.stream()
            .filter(inscricao -> inscricao.getStatus() == Inscricao.StatusInscricao.ATIVO)
            .count();
    }
    
    public boolean isLotado() {
        return getNumeroInscritos() >= limiteParticipantes;
    }
    
    public boolean hasVagasDisponiveis() {
        return !isLotado();
    }
    
    public boolean isDataPassada() {
        return data.isBefore(LocalDateTime.now());
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
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDateTime getData() {
        return data;
    }
    
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public Integer getLimiteParticipantes() {
        return limiteParticipantes;
    }
    
    public void setLimiteParticipantes(Integer limiteParticipantes) {
        this.limiteParticipantes = limiteParticipantes;
    }
    
    public Departamento getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public Set<Palestrante> getPalestrantes() {
        return palestrantes;
    }
    
    public void setPalestrantes(Set<Palestrante> palestrantes) {
        this.palestrantes = palestrantes;
    }
    
    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }
    
    public void setInscricoes(List<Inscricao> inscricoes) {
        this.inscricoes = inscricoes;
    }
}