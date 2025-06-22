package br.edu.unicesumar.api.repository;

import br.edu.unicesumar.api.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
    @Query("SELECT COUNT(e) FROM Evento e WHERE e.departamento.id = :departamentoId")
    Long countEventosByDepartamentoId(@Param("departamentoId") Long departamentoId);
    
    @Query("SELECT COUNT(i) FROM Inscricao i WHERE i.evento.departamento.id = :departamentoId AND i.status = 'ATIVO'")
    Long countInscricoesByDepartamentoId(@Param("departamentoId") Long departamentoId);
}