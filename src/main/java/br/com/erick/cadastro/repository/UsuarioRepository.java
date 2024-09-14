package br.com.erick.cadastro.repository;

import br.com.erick.cadastro.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
