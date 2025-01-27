package com.projeto.desafiobackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

	@NotBlank(message = "O endereco de email é obrigatório.")
	@Email(message = "O email fornecido não é válido.")
	private String enderecoEmail;
}
