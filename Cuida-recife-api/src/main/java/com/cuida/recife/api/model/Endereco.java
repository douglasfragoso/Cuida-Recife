package com.cuida.recife.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Endereco {
    
    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numero;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;
    
    @Column(nullable = false)
    private String uf;

    @JsonFormat(pattern = "\\(d{2}).\\d{3}.-\\{3}")
    @Column(nullable = false)
    private String cpf;
}
