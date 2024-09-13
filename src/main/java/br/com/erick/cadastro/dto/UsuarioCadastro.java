package br.com.erick.cadastro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCadastro {

    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "Email não pode estar vazio")
    @Email(message = "Email deve ser válido")
    private String email;

    @NotBlank(message = "Celular não pode estar vazio")
    @Pattern(regexp = "\\d{11}$", message = "Celular deve ser um número válido")
    private String celular;

    @NotNull(message = "Saldo não pode ser nulo")
    private BigDecimal saldo;

}
