package com.cuida.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuida.medicoDTO.MedicoDTO;
import com.cuida.recife.api.model.Medico;
import com.cuida.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")

public class MedicoController {
    
    @PostMapping
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody Medico medico){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoRepository.save(medico));

    }

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> listarMedico(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        Page<Medico> medicos = medicoRepository.findAll(paginacao);
        List<MedicoDTO> medicosDTO = medicos.stream().map(MedicoDTO::new).collect(Collectors.toList());
        Page<MedicoDTO> medicosDTOPage = new PageImpl<>(medicosDTO, paginacao, medicos.getTotalElements());
        return ResponseEntity.status(HttpStatus.OK).body(medicosDTOPage);
    }

    @PutMapping("/{crm}")
    public ResponseEntity<Medico> atualizarMedico(@PathVariable String crm, @RequestBody Medico medicoAtualizado) {
        try {
            Optional<Medico> optionalMedico = medicoRepository.findByCrm(crm);

            if (medicoAtualizado.getCrm() != null || medicoAtualizado.getEspecialidade() != null || medicoAtualizado.getEndereco()!= null) {
                return ResponseEntity.badRequest().build();
            }
            if (optionalMedico.isPresent()) {
                Medico medico = optionalMedico.get();
                medico.setNome(medicoAtualizado.getNome());
                medico.setEmail(medicoAtualizado.getEmail());
                medico.setTelefone(medicoAtualizado.getTelefone());
                Medico updatedMedico = medicoRepository.save(medico);

                return ResponseEntity.status(HttpStatus.OK).body(updatedMedico);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{crm}/delete")
    public ResponseEntity<Void> desativarMedico(@PathVariable String crm) {
        try {
            Optional<Medico> optionalMedico = medicoRepository.findByCrm(crm);
    
            if (optionalMedico.isPresent()) { // Isso significa que o médico foi encontrado no banco de dados.
                Medico medico = optionalMedico.get();
                medico.setActive(false);
    
                medicoRepository.save(medico);
    
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//ResponseEntity.notFound().build()
            }
        } catch (Exception e) {
            e.printStackTrace(); // Exibe o stack trace da exceção no console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Autowired
    private MedicoRepository medicoRepository;
}
