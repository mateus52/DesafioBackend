package com.projeto.desafiobackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.desafiobackend.dto.TelefonesDTO;
import com.projeto.desafiobackend.model.enums.TipoTelefone;

@Service
class TelefoneService {

	public void validarTelefones(List<TelefonesDTO> telefones) {
        if (telefones.isEmpty()) {
            throw new IllegalArgumentException("Pelo menos um telefone deve ser cadastrado.");
        }

        telefones.forEach(telefone -> {
            String numero = telefone.getNumero().replaceAll("[^0-9]", "");
            if (TipoTelefone.valueOf(telefone.getTipo()) == TipoTelefone.CELULAR && numero.length() != 11) {
                throw new IllegalArgumentException("O celular deve ter 11 dígitos.");
            }
            if ((TipoTelefone.valueOf(telefone.getTipo()) == TipoTelefone.RESIDENCIAL ||TipoTelefone.valueOf(telefone.getTipo()) == TipoTelefone.COMERCIAL)
                    && numero.length() != 10) {
                throw new IllegalArgumentException("O telefone residencial ou comercial deve ter 10 dígitos.");
            }
        });
    }
}
