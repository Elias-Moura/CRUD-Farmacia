package io.farmacia.Generation.dominio.dtos.Categoria;

import io.farmacia.Generation.dominio.modelos.Categoria;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCategoria(
        @NotNull
        String nome,
        @NotNull
        String descricao
) {

    public DadosCadastroCategoria(Categoria dados) {

        this(dados.getNome(), dados.getDescricao());
    }
}
