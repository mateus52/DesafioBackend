package com.projeto.desafiobackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.desafiobackend.dto.ClienteDTO;
import com.projeto.desafiobackend.dto.EnderecoDTO;
import com.projeto.desafiobackend.dto.NovoClienteDTO;
import com.projeto.desafiobackend.model.Clientes;
import com.projeto.desafiobackend.model.Email;
import com.projeto.desafiobackend.model.Endereco;
import com.projeto.desafiobackend.model.Telefones;
import com.projeto.desafiobackend.repository.ClientesRepository;
import com.projeto.desafiobackend.util.TelefoneUtil;

import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClientesRepository clienteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<ClienteDTO> listarTodosClientes() {
        List<Clientes> clientes = clienteRepository.findAll();
        // Converter a lista de entidades para DTOs
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class)) // Fazendo a conversão
                .collect(Collectors.toList());
    }
	
	public ClienteDTO buscarClientePorId(Long id) {
	    // Verifica se o cliente foi encontrado no banco de dados
	    Clientes cliente = clienteRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));

	    // Mapeia a entidade 'cliente' para 'ClienteDTO' utilizando o ModelMapper
	    return modelMapper.map(cliente, ClienteDTO.class);
	}
	
	public Clientes salvarCliente(@Valid NovoClienteDTO clienteDTO) {
        // Verifica se o CPF ou nome de usuário já existe
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        if (clienteRepository.existsByNomeUsuario(clienteDTO.getNomeUsuario())) {
            throw new RuntimeException("Nome de usuário já cadastrado.");
        }
        
        if(clienteDTO.getEmails().isEmpty()) {
        	throw new IllegalArgumentException("Pelo menos um email deve ser cadastrado.");
        }
        
        telefoneService.validarTelefones(clienteDTO.getTelefones());
 

        // Converte DTO para entidade
        Clientes cliente = new Clientes();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefones(
            clienteDTO.getTelefones().stream()
                .map(dto -> new Telefones(null,dto.getTipo(),dto.getNumero()))
                .collect(Collectors.toList())
        );
        cliente.setEmails(
            clienteDTO.getEmails().stream()
                .map(dto -> new Email(null,dto.getEnderecoEmail()))
                .collect(Collectors.toList())
        );
        cliente.setEndereco(modelMapper.map(clienteDTO.getEndereco(), Endereco.class));
        cliente.setNomeUsuario(clienteDTO.getNomeUsuario());
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha())); // Criptografa a senha
        cliente.setRoles(clienteDTO.getRoles());

        // Salva o cliente no banco de dados
        return clienteRepository.save(cliente);
    }
	
	@Transactional
    public void atualizarEndereco(@Valid Long clienteId, EnderecoDTO enderecoDTO) {
        // Buscar o cliente pelo ID
        Clientes cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + clienteId));

        // Atualizar o endereço do cliente
        cliente.setEndereco(new Endereco(
            enderecoDTO.getCep(),
            enderecoDTO.getLogradouro(),
            enderecoDTO.getBairro(),
            enderecoDTO.getCidade(),
            enderecoDTO.getUf(),
            enderecoDTO.getComplemento()
        ));

        // Salvar as alterações no banco de dados
        clienteRepository.save(cliente);
    }
	
	public EnderecoDTO buscarEnderecoPorCep(String cep) {
        return viaCepService.buscarEnderecoPorCep(cep).block();
    }

}