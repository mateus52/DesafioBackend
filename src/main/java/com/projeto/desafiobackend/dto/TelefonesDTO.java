package com.projeto.desafiobackend.dto;

import com.projeto.desafiobackend.util.TelefoneUtil;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefonesDTO {

	@NotBlank(message = "O número do telefone é obrigatório.")
	private String numero;
	
	@NotBlank(message = "O tipo de telefone é obrigatório.")
    private String tipo;
	
	public String getNumero() {
        return TelefoneUtil.aplicarMascaraTelefone(this.numero,this.tipo);
    }
	
}


