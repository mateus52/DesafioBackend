package com.projeto.desafiobackend.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NovoClienteDTO {

	@NotBlank(message = "O nome é obrigatório.")
	@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "O nome pode conter apenas letras, números e espaços.")
	private String nome;

	@NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "O CPF deve estar no formato XXX.XXX.XXX-XX.")
	private String cpf;

	@NotEmpty(message = "A lista de telefones não pode ser vazia.")
    @Valid
	private List<TelefonesDTO> telefones;

	@NotEmpty(message = "A lista de email não pode ser vazia.")
    @Valid
	private List<EmailDTO> emails;

	@NotNull(message = "O endereço é obrigatório.")
	@Valid
	private EnderecoDTO endereco;

	@NotBlank(message = "O nome de usuário é obrigatório.")
	private String nomeUsuario;

	@NotBlank(message = "A senha é obrigatória.")
	private String senha;

	private Set<String> roles;

}
