package com.example.MeuSegundoSpring.controllers;

import com.example.MeuSegundoSpring.entities.Produto;
import com.example.MeuSegundoSpring.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Salvar", description = "Método que salva os produtos", tags = "Produtos")
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @Operation(summary = "Listar", description = "Método que lista os produtos", tags = "Produtos")
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Buscar", description = "Método que busca o produto por id", tags = "Produtos")
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Deletar", description = "Método que deleta os produtos", tags = "Produtos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (produtoService.buscarProdutoPorId(id) != null) {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar", description = "Método que busa os produtos por nome", tags = "Produtos")
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Produto>> listarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.listarProdutosPorNome(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(produtos);
    }
}