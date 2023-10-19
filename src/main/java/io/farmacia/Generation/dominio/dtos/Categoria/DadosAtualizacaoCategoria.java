package io.farmacia.Generation.dominio.dtos.Categoria;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(
        @NotNull
        Long id,
        @NotNull
        String nome,
        @NotNull
        String descricao,
        @NotNull
        Boolean esta_ativo
) {
}
