// src/main/java/com/example/projeto/controller/ProdutoController.java
package com.example.projeto.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projeto.service.ProdutoService;
import com.example.projeto.model.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id,
                                                   @RequestBody Produto dados) {
        return produtoService.buscarPorId(id)
            .map(existing -> {
                existing.setNome(dados.getNome());
                existing.setQuantidade(dados.getQuantidade());
                existing.setValor(dados.getValor());
                Produto updated = produtoService.salvarProduto(existing);
                return ResponseEntity.ok(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
