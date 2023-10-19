package io.farmacia.Generation.dominio.dtos.Produto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DadosAtualizacaoProduto(

        @NotNull
        Long id,
        @NotNull
        String nome,
        @NotNull
        BigDecimal preco,
        @NotNull
        BigInteger qtdEstoque,
        @NotNull
        Long idCategoria,
        @NotNull
        Boolean estaAtivo
) {
}
