package com.projeto.desafiobackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.desafiobackend.dto.ClienteDTO;
import com.projeto.desafiobackend.dto.EnderecoDTO;
import com.projeto.desafiobackend.dto.NovoClienteDTO;
import com.projeto.desafiobackend.model.Clientes;
import com.projeto.desafiobackend.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id, Authentication authentication) {
        ClienteDTO cliente = clienteService.buscarClientePorId(id);

        if (!authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                && !cliente.getNomeUsuario().equals(authentication.getName())) {
            return ResponseEntity.status(403).body("Acesso negado: você só pode visualizar suas próprias informações.");
        }

        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes(Authentication authentication) {
        if (!authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body(null);
        }

        return ResponseEntity.ok(clienteService.listarTodosClientes());
    }
    
    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody NovoClienteDTO clienteDTO) {
        Clientes novoCliente = clienteService.salvarCliente(clienteDTO);
        return ResponseEntity.ok("Cliente criado com sucesso: " + novoCliente.getNome());
    }
    
	@PutMapping("/{id}/endereco")
	public ResponseEntity<String> atualizarEndereco(@PathVariable Long id, Authentication authentication, @RequestBody EnderecoDTO enderecoDTO) {
		ClienteDTO cliente = clienteService.buscarClientePorId(id);
		if (!authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
				&& !cliente.getNomeUsuario().equals(authentication.getName())) {
			return ResponseEntity.status(403).body("Acesso negado: você só pode atualizar suas próprias informações.");
		}
		clienteService.atualizarEndereco(id, enderecoDTO);
		return ResponseEntity.ok("Endereço atualizado com sucesso.");

	}
	
	@GetMapping("/buscar/{cep}")
	public ResponseEntity<EnderecoDTO> buscarEnderecoPorCep(@PathVariable String cep) {
		EnderecoDTO enderecoDTO = clienteService.buscarEnderecoPorCep(cep);
		if (enderecoDTO != null) {
			return ResponseEntity.ok(enderecoDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
    
}
