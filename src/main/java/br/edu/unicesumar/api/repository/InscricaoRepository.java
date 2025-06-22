package br.edu.unicesumar.api.repository;

import br.edu.unicesumar.api.entity.Inscricao;
import br.edu.unicesumar.api.entity.Inscricao.StatusInscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    
    @Query("SELECT i FROM Inscricao i WHERE i.aluno.id = :alunoId AND i.status = :status")
    List<Inscricao> findByAlunoIdAndStatus(@Param("alunoId") Long alunoId, @Param("status") StatusInscricao status);
    
    @Query("SELECT i FROM Inscricao i WHERE i.evento.id = :eventoId AND i.aluno.id = :alunoId")
    Optional<Inscricao> findByEventoIdAndAlunoId(@Param("eventoId") Long eventoId, @Param("alunoId") Long alunoId);
    
    @Query("SELECT COUNT(i) FROM Inscricao i WHERE i.evento.id = :eventoId AND i.status = 'ATIVO'")
    Long countInscricoesAtivasByEventoId(@Param("eventoId") Long eventoId);
}