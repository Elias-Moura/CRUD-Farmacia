package io.farmacia.Generation.dominio.modelos;

import io.farmacia.Generation.dominio.dtos.Categoria.DadosAtualizacaoCategoria;
import io.farmacia.Generation.dominio.dtos.Categoria.DadosCadastroCategoria;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToMany(mappedBy = "categoria", cascade= CascadeType.REMOVE)
    private List<Produto> produtos;
    private Boolean estaAtivo;

    public Categoria(DadosCadastroCategoria dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.estaAtivo = true;
    }

    public Categoria(Long id){
        this.id = id;
    }

    public void excluir() {
        this.estaAtivo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoCategoria dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.esta_ativo() != null) {
            this.estaAtivo = dados.esta_ativo();
        }
    }

}
