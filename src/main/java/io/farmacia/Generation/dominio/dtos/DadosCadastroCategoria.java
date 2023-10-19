package io.farmacia.Generation.dominio.dtos;

import io.farmacia.Generation.dominio.modelos.Categoria;

public record DadosCadastroCategoria(
        String nome,
        String descricao
) {

    public DadosCadastroCategoria(Categoria dados) {
        this(dados.getNome(), dados.getDescricao());
    }
}
