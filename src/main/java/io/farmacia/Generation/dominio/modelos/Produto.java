package io.farmacia.Generation.dominio.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.farmacia.Generation.dominio.dtos.Produto.DadosAtualizacaoProduto;
import io.farmacia.Generation.dominio.dtos.Produto.DadosCadastroProduto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity(name="Produto")
@Table(name= "tb_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private BigInteger qtdEstoque;
    @ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;
    private Boolean estaAtivo;


    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.qtdEstoque = dados.qtdEstoque();
        this.categoria = new Categoria(dados.idCategoria());
        this.estaAtivo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        this.nome = dados.nome();
        this.preco = dados.preco();
        this.qtdEstoque = dados.qtdEstoque();
        this.categoria = new Categoria(dados.idCategoria());
        this.estaAtivo = dados.estaAtivo();
    }

    public void excluir() {
        this.estaAtivo = false;
    }
}
