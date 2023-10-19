package dominio.dtos;

public record DadosAtualizacaoCategoria(
        Long id,
        String nome,
        String descricao,
        Boolean esta_ativo
) {
}
