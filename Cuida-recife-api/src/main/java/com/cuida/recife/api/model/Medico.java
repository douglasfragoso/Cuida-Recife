package com.cuida.recife.api.model;


import com.cuida.constants.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="medicos_tb")

public class Medico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedico;
    
    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false)
    private String nome;

    @JsonFormat(pattern = "^(.+)@(\\S+)$")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonFormat(pattern = "\\(d{2})-\\d{4,5}.-\\{4}")
    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

}
