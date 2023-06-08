package com.cuida.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cuida.recife.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, String> {
    
    Optional<Medico> findByCrm(@Param("crm") String cmr);
}
