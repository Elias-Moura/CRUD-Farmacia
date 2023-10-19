package io.farmacia.Generation.controllers;

import io.farmacia.Generation.dominio.dtos.Produto.DadosAtualizacaoProduto;
import io.farmacia.Generation.dominio.dtos.Produto.DadosCadastroProduto;
import io.farmacia.Generation.dominio.dtos.Produto.DadosListagemProduto;
import io.farmacia.Generation.dominio.modelos.Produto;
import io.farmacia.Generation.dominio.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> getAll(@PageableDefault(size=10, sort={"id"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemProduto> getById(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemProduto(produto));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<DadosListagemProduto>> getByTitulo(@PathVariable String nome) {
        var body = repository.findAllByNomeContainingIgnoreCase(nome)
                .stream()
                .map(DadosListagemProduto::new)
                .toList();
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemProduto> post(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = new Produto(dados);
        repository.save(produto);

        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemProduto(produto));
    }

    @DeleteMapping({"/{id}"})
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Produto produto = repository.getReferenceById(id);
        produto.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DadosListagemProduto> put(@Valid @RequestBody DadosAtualizacaoProduto dados) {
        Produto produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);
        repository.save(produto);
        return ResponseEntity.ok(new DadosListagemProduto(produto));
    }

}
