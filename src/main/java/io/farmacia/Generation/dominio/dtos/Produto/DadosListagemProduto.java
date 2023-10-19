package io.farmacia.Generation.dominio.dtos.Produto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.farmacia.Generation.dominio.dtos.Categoria.DadosListagemCategoria;
import io.farmacia.Generation.dominio.modelos.Produto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DadosListagemProduto(
        Long id,
        String nome,
        BigDecimal preco,
        BigInteger qtdEstoque,
        @JsonIgnoreProperties("produtos")
        DadosListagemCategoria categoria,
        Boolean estaAtivo) {
    public DadosListagemProduto(Produto dados) {
        this(
                dados.getId(),
                dados.getNome(),
                dados.getPreco(),
                dados.getQtdEstoque(),
                new DadosListagemCategoria(dados.getCategoria()),
                dados.getEstaAtivo()
        );
    }
}
