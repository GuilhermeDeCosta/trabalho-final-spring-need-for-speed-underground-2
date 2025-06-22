package br.edu.unicesumar.api.service;

import br.edu.unicesumar.api.dto.AlunoDTO;
import br.edu.unicesumar.api.entity.Aluno;
import br.edu.unicesumar.api.exception.DuplicateResourceException;
import br.edu.unicesumar.api.exception.ResourceNotFoundException;
import br.edu.unicesumar.api.mapper.DTOMapper;
import br.edu.unicesumar.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DTOMapper mapper;

    @Transactional(readOnly = true)
    public List<AlunoDTO> findAll() {
        return alunoRepository.findAll().stream()
                .map(mapper::toAlunoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
        return mapper.toAlunoDTO(aluno);
    }

    public AlunoDTO create(AlunoDTO dto) {

        if (alunoRepository.existsByMatricula(dto.getMatricula())) {
            throw new DuplicateResourceException("Já existe um aluno com a matrícula: " + dto.getMatricula());
        }
        
        Aluno aluno = mapper.toAlunoEntity(dto);
        aluno = alunoRepository.save(aluno);
        return mapper.toAlunoDTO(aluno);
    }

    public AlunoDTO update(Long id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
        
        if (!aluno.getMatricula().equals(dto.getMatricula()) && 
            alunoRepository.existsByMatricula(dto.getMatricula())) {
            throw new DuplicateResourceException("Já existe um aluno com a matrícula: " + dto.getMatricula());
        }
        
        aluno.setNome(dto.getNome());
        aluno.setMatricula(dto.getMatricula());
        aluno.setCurso(dto.getCurso());
        
        aluno = alunoRepository.save(aluno);
        return mapper.toAlunoDTO(aluno);
    }

    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado com ID: " + id);
        }
        alunoRepository.deleteById(id);
    }
}