package com.projeto.desafiobackend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "clientes")
public class Clientes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Telefones> telefones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_id")
    private List<Email> emails;

    @Embedded
    private Endereco endereco;
    
    @Column(name = "nome_usuario", unique = true, nullable = false)
    private String nomeUsuario;

    @Column(name = "senha", nullable = false)
    private String senha;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cliente_roles", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (cpf != null) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }
    }
}
