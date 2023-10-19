package io.farmacia.Generation.controllers;

import io.farmacia.Generation.dominio.dtos.DadosAtualizacaoCategoria;
import io.farmacia.Generation.dominio.dtos.DadosCadastroCategoria;
import io.farmacia.Generation.dominio.dtos.DadosListagemCategoria;
import io.farmacia.Generation.dominio.modelos.Categoria;
import io.farmacia.Generation.dominio.repository.CategoriaRepository;
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
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemCategoria>> getAll(@PageableDefault(size=10, sort={"id"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCategoria::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemCategoria> getById(@PathVariable Long id) {
        var postagem = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemCategoria(postagem));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<DadosListagemCategoria>> getByTitulo(@PathVariable String nome) {
        var body = repository.findAllByNomeContainingIgnoreCase(nome)
                .stream()
                .map(DadosListagemCategoria::new)
                .toList();
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemCategoria> post(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder) {
        var categoria = new Categoria(dados);
        repository.save(categoria);

        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemCategoria(categoria));
    }

    @DeleteMapping({"/{id}"})
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Categoria categoria = repository.getReferenceById(id);
        categoria.excluir();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DadosListagemCategoria> put(@Valid @RequestBody DadosAtualizacaoCategoria dados) {
        Categoria categoria = repository.getReferenceById(dados.id());
        categoria.atualizarInformacoes(dados);
        repository.save(categoria);
        return ResponseEntity.ok(new DadosListagemCategoria(categoria));
    }

}
