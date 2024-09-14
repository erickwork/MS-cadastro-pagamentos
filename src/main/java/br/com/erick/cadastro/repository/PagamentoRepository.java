package br.com.erick.cadastro.repository;

import br.com.erick.cadastro.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
