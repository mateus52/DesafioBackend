package com.projeto.desafiobackend.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@NotBlank(message = "O campo 'cep' é obrigatório e não pode estar vazio.")
		private String cep;
		
		@NotBlank(message = "O campo 'logradouro' é obrigatório e não pode estar vazio.")
		private String logradouro;
		
		@NotBlank(message = "O campo 'bairro' é obrigatório e não pode estar vazio.")
		private String bairro;
		
		@NotBlank(message = "O campo 'cidade' é obrigatório e não pode estar vazio.")
		private String cidade;
		
		@NotBlank(message = "O campo 'uf' é obrigatório e não pode estar vazio.")
		private String uf;
		
		private String complemento;
		
}
