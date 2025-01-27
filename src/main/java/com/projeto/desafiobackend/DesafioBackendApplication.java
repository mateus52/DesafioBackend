package com.projeto.desafiobackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projeto.desafiobackend.model.Clientes;
import com.projeto.desafiobackend.model.Email;
import com.projeto.desafiobackend.model.Endereco;
import com.projeto.desafiobackend.model.Telefones;
import com.projeto.desafiobackend.repository.ClientesRepository;

@SpringBootApplication
public class DesafioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner loadData(ClientesRepository clientesRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (clientesRepository.findByNomeUsuario("admin") == null) {
            	List<Telefones> telAdmin = new ArrayList<>();
            	Telefones telefone1 = new Telefones(null, "CELULAR", "61999999999");
            	telAdmin.add(telefone1);
            	
            	List<Email> emailAdmin = new ArrayList<>();
            	Email email1 = new Email(null,"admin@gmail.com");
            	emailAdmin.add(email1);
            	
            	Clientes admin = new Clientes();
                admin.setNome("Admin");
                admin.setCpf("12345678910");
                admin.setTelefones(telAdmin);
                admin.setEmails(emailAdmin);
                admin.setEndereco(new Endereco("73555888","Praça Admin", "Admin", "Brasilia","DF", "Rua Admin"));
                admin.setNomeUsuario("admin");
                admin.setSenha(passwordEncoder.encode("123qwe!@#"));
                admin.setRoles(Set.of("ADMIN"));
                clientesRepository.save(admin);
            }

            
			if (clientesRepository.findByNomeUsuario("cliente") == null) {
				List<Telefones> telCliente = new ArrayList<>();
				Telefones telefone2 = new Telefones(null, "CELULAR", "61988888888");
				telCliente.add(telefone2);

				List<Email> emailCliente = new ArrayList<>();
				Email email2 = new Email(null, "cliente@gmail.com");
				emailCliente.add(email2);

				Clientes cliente = new Clientes();
				cliente.setNome("Cliente");
				cliente.setCpf("12345678911");
				cliente.setTelefones(telCliente);
				cliente.setEmails(emailCliente);
				cliente.setEndereco(new Endereco("73555889", "Praça Cliente", "Cliente", "Brasilia", "DF", "Rua Cliente"));
				cliente.setNomeUsuario("cliente");
				cliente.setSenha(passwordEncoder.encode("123qwe123"));
				cliente.setRoles(Set.of("USER"));
				clientesRepository.save(cliente);
			}
        };
    }

}
