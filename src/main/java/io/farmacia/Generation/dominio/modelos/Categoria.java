package io.farmacia.Generation.dominio.modelos;

import io.farmacia.Generation.dominio.dtos.DadosAtualizacaoCategoria;
import io.farmacia.Generation.dominio.dtos.DadosCadastroCategoria;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="Categoria")
@Table(name= "tb_categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean esta_ativo;

    public Categoria(DadosCadastroCategoria dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.esta_ativo = true;
    }

    public void excluir() {
        this.esta_ativo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoCategoria dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.esta_ativo() != null) {
            this.esta_ativo = dados.esta_ativo();
        }
    }
}
