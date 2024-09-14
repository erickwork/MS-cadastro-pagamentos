package br.com.erick.cadastro.controller;

import br.com.erick.cadastro.domain.Pagamento;
import br.com.erick.cadastro.domain.Usuario;
import br.com.erick.cadastro.dto.PagamentoCadastro;
import br.com.erick.cadastro.dto.UsuarioCadastro;
import br.com.erick.cadastro.service.PagamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public List<Pagamento> buscarUsuarios(){
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> detalhar(@PathVariable @NotNull Long id) {
        Pagamento pagamento = service.obterPorId(id);
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Pagamento> novoUsuario(@RequestBody @Valid PagamentoCadastro dados, UriComponentsBuilder uriBuilder){
        Pagamento pagamento = service.novoPagamento(dados);
        URI enderecoNovoPagamento=uriBuilder.path("pagamento/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(enderecoNovoPagamento).body(pagamento);
    }


}
