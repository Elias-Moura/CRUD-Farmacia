package dominio.modelos;

import dominio.dtos.DadosAtualizacaoCategoria;
import dominio.dtos.DadosCadastroCategoria;
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
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.esta_ativo = dados.esta_ativo();
    }
}
