package com.projeto.desafiobackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.projeto.desafiobackend.dto.EnderecoDTO;

import reactor.core.publisher.Mono;

@Service
public class ViaCepService {

    private final WebClient webClient;

    public ViaCepService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://viacep.com.br/ws").build();
    }

    public Mono<EnderecoDTO> buscarEnderecoPorCep(String cep) {
    	return webClient
                .get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), 
                          response -> Mono.error(new IllegalArgumentException("CEP inválido")))
                .onStatus(status -> status.is5xxServerError(),
                          response -> Mono.error(new RuntimeException("Erro no serviço ViaCEP")))
                .bodyToMono(EnderecoDTO.class);
    }
}

