package dominio.dtos;

import dominio.modelos.Categoria;

public record DadosListagemCategoria(
        Long id,
        String nome,
        String descricao
) {
    public DadosListagemCategoria(Categoria dados) {

        this(dados.getId(), dados.getNome(), dados.getDescricao());
    }
}
