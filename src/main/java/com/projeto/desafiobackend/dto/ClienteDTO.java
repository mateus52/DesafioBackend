package com.projeto.desafiobackend.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.desafiobackend.util.CpfUtil;
import com.projeto.desafiobackend.util.TelefoneUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

	private String nome;
	private String cpf;
	private List<TelefonesDTO> telefones;
	private List<EmailDTO> emails;
	private EnderecoDTO endereco;
	private String nomeUsuario;
	
	public String getCpf() {
        return CpfUtil.mascaraCpf(this.cpf);
    }
	
	public List<TelefonesDTO> getTelefones() {
        return telefones.stream()
                .map(telefone -> {
                    telefone.setNumero(TelefoneUtil.aplicarMascaraTelefone(telefone.getNumero(),telefone.getTipo()));
                    return telefone;
                })
                .collect(Collectors.toList());
    }

}
