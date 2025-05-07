// src/main/java/com/example/projeto/repository/ProdutoRepository.java
package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // você pode declarar queries customizadas aqui, se precisar
}
