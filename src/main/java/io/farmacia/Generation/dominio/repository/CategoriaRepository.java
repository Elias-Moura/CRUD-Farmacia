package io.farmacia.Generation.dominio.repository;

import io.farmacia.Generation.dominio.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
