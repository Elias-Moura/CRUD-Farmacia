package io.farmacia.Generation.dominio.dtos.Produto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DadosCadastroProduto(
        @NotNull
        String nome,
        @NotNull
        BigDecimal preco,
        @NotNull
        BigInteger qtdEstoque,
        @NotNull
        Long idCategoria)
{

}
