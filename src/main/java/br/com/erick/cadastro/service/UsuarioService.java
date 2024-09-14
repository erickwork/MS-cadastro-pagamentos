package br.com.erick.cadastro.service;


import br.com.erick.cadastro.domain.Usuario;
import br.com.erick.cadastro.dto.UsuarioCadastro;
import br.com.erick.cadastro.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Usuario> obterTodos(){
        return repository.findAll();
    }

    public Usuario obterPorId(Long id){
        return repository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Usuario novoUsuario(UsuarioCadastro dto){
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        usuario.setAtivo(true);
        repository.save(usuario);
        rabbitTemplate.convertAndSend("usuario", usuario);
        return usuario;
    }

    public Usuario inativarUsuario(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        usuario.setAtivo(false);
        repository.save(usuario);
        rabbitTemplate.convertAndSend("usuario", usuario);
        return usuario;
    }

    public Usuario reativarUsuario(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        usuario.setAtivo(true);
        repository.save(usuario);
        rabbitTemplate.convertAndSend("usuario", usuario);
        return usuario;
    }

}
