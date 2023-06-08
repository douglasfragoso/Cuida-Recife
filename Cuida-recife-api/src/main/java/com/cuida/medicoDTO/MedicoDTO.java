package com.cuida.medicoDTO;

import com.cuida.constants.Especialidade;
import com.cuida.recife.api.model.Medico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTO {
   
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;

    public MedicoDTO(Medico medico){

        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();

    }
}
