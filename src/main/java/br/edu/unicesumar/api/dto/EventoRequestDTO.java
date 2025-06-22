package br.edu.unicesumar.api.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

public class EventoRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 200, message = "Nome deve ter no máximo 200 caracteres")
    private String nome;
    
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    @NotNull(message = "Data é obrigatória")
    @Future(message = "Data deve ser futura")
    private LocalDateTime data;
    
    @NotNull(message = "Limite de participantes é obrigatório")
    @Min(value = 1, message = "Limite de participantes deve ser no mínimo 1")
    private Integer limiteParticipantes;
    
    @NotNull(message = "Departamento é obrigatório")
    private Long departamentoId;
    
    @NotEmpty(message = "Evento deve ter pelo menos um palestrante")
    private Set<Long> palestrantesIds;
    
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
    
    public Set<Long> getPalestrantesIds() { return palestrantesIds; }
    public void setPalestrantesIds(Set<Long> palestrantesIds) { this.palestrantesIds = palestrantesIds; }
}