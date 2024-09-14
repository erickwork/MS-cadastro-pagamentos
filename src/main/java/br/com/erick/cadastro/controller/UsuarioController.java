package br.com.erick.cadastro.controller;

import br.com.erick.cadastro.domain.Usuario;
import br.com.erick.cadastro.dto.UsuarioCadastro;
import br.com.erick.cadastro.repository.UsuarioRepository;
import br.com.erick.cadastro.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> buscarUsuarios(){
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalhar(@PathVariable @NotNull Long id) {
        Usuario usuario = service.obterPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> novoUsuario(@RequestBody @Valid UsuarioCadastro dados, UriComponentsBuilder uriBuilder){
        Usuario usuario = service.novoUsuario(dados);
        URI enderecoNovoUsuario =uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(enderecoNovoUsuario).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarUsuario(@PathVariable Long id){
        Usuario usuario = service.inativarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> reativarUsuario(@PathVariable Long id){
        Usuario usuario = service.reativarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
