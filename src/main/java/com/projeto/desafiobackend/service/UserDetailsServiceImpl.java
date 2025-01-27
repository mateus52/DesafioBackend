package com.projeto.desafiobackend.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projeto.desafiobackend.model.Clientes;
import com.projeto.desafiobackend.repository.ClientesRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final ClientesRepository clientesRepository;

    public UserDetailsServiceImpl(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
    	Clientes cliente = clientesRepository.findByNomeUsuario(nomeUsuario);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new org.springframework.security.core.userdetails.User(
        		cliente.getNomeUsuario(),
        		cliente.getSenha(),
        		cliente.getRoles().stream().map(role -> 
                    new SimpleGrantedAuthority("ROLE_" + role)
                ).toList()
        );
    }
	
	

}
