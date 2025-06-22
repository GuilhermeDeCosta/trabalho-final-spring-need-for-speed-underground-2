package br.edu.unicesumar.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class EventoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime data;
    private Integer limiteParticipantes;
    private Long departamentoId;
    private String departamentoNome;
    private Set<Long> palestrantesIds;
    private Set<PalestranteSimpleDTO> palestrantes;
    private Long numeroInscritos;
    private Boolean lotado;
    private Boolean vagasDisponiveis;
    
    public EventoDTO() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    
    public Integer getLimiteParticipantes() { return limiteParticipantes; }
    public void setLimiteParticipantes(Integer limiteParticipantes) { this.limiteParticipantes = limiteParticipantes; }
    
    public Long getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(Long departamentoId) { this.departamentoId = departamentoId; }
    
    public String getDepartamentoNome() { return departamentoNome; }
    public void setDepartamentoNome(String departamentoNome) { this.departamentoNome = departamentoNome; }
    
    public Set<Long> getPalestrantesIds() { return palestrantesIds; }
    public void setPalestrantesIds(Set<Long> palestrantesIds) { this.palestrantesIds = palestrantesIds; }
    
    public Set<PalestranteSimpleDTO> getPalestrantes() { return palestrantes; }
    public void setPalestrantes(Set<PalestranteSimpleDTO> palestrantes) { this.palestrantes = palestrantes; }
    
    public Long getNumeroInscritos() { return numeroInscritos; }
    public void setNumeroInscritos(Long numeroInscritos) { this.numeroInscritos = numeroInscritos; }
    
    public Boolean getLotado() { return lotado; }
    public void setLotado(Boolean lotado) { this.lotado = lotado; }
    
    public Boolean getVagasDisponiveis() { return vagasDisponiveis; }
    public void setVagasDisponiveis(Boolean vagasDisponiveis) { this.vagasDisponiveis = vagasDisponiveis; }
}