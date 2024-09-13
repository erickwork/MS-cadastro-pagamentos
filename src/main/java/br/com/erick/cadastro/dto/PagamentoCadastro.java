package br.com.erick.cadastro.dto;

import br.com.erick.cadastro.domain.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoCadastro {

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long usuario;

    @NotNull(message = "O tipo de pagamento não pode ser nulo")
    private TipoPagamento tipo;

    @NotNull(message = "O valor não pode ser nulo")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data de vencimento não pode ser nula")
    private LocalDate vencimento;
}
