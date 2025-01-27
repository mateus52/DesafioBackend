package com.projeto.desafiobackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

	@NotBlank(message = "O cep é obrigatório.")
	@Column(nullable = false, length = 8)
    private String cep;

	@NotBlank(message = "O logradouro é obrigatório.")
    @Column(nullable = false)
    private String logradouro;

	@NotBlank(message = "O bairro é obrigatório.")
    @Column(nullable = false)
    private String bairro;

	@NotBlank(message = "O cidade é obrigatório.")
    @Column(nullable = false)
    private String cidade;

	@NotBlank(message = "O uf é obrigatório.")
    @Column(nullable = false, length = 2)
    private String uf;

    private String complemento;
    
    @PrePersist
    public void prePersist() {
        // Remove a máscara do CPF antes de persistir no banco
        if (cep != null) {
            this.cep = cep.replaceAll("[^0-9]", ""); // Remove tudo que não for número
        }
    }

}
