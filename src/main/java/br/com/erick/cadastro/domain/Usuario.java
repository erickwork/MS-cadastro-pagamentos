package br.com.erick.cadastro.domain;

import br.com.erick.cadastro.dto.UsuarioCadastro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String celular;
    private BigDecimal saldo;
    private boolean ativo;

    public Usuario(UsuarioCadastro dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.celular = dto.getCelular();
        this.saldo = dto.getSaldo();
        this.ativo = true;
    }
}
