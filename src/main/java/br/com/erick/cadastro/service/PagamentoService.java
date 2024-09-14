package br.com.erick.cadastro.service;

import br.com.erick.cadastro.domain.Pagamento;
import br.com.erick.cadastro.domain.Status;
import br.com.erick.cadastro.domain.Usuario;
import br.com.erick.cadastro.dto.PagamentoCadastro;
import br.com.erick.cadastro.dto.UsuarioCadastro;
import br.com.erick.cadastro.repository.PagamentoRepository;
import br.com.erick.cadastro.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Pagamento> obterTodos(){
        return repository.findAll();
    }

    public Pagamento obterPorId(Long id){
        return repository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Pagamento novoPagamento(PagamentoCadastro dto){
        Usuario usuario = usuarioRepository.findById(dto.getUsuario()).orElseThrow(EntityNotFoundException::new);
        Pagamento pagamento = new Pagamento(dto, usuario);
        pagamento.setStatus(Status.PENDENTE);
        repository.save(pagamento);
        rabbitTemplate.convertAndSend("pagamento", pagamento);
        return pagamento;
    }


}
