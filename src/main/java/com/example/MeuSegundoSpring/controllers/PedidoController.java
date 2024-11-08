package com.example.MeuSegundoSpring.controllers;

import com.example.MeuSegundoSpring.entities.Pedido;
import com.example.MeuSegundoSpring.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Salvar", description = "Método que salva os pedidos", tags = "Pedidos")
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.salvarPedido(pedido));
    }

    @Operation(summary = "Listar", description = "Método que lista os pedidos", tags = "Pedidos")
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @Operation(summary = "Buscar", description = "Método que busca o pedido por id", tags = "Pedidos")
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Deletar", description = "Método que deleta os pedidos", tags = "Pedidos")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        if (pedidoService.buscarPedidoPorId(id) != null) {
            pedidoService.deletarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar", description = "Método que busca os pedidos por id do cliente", tags = "Pedidos")
    @GetMapping("/buscarPorCliente/{idCliente}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long idCliente) {
        List<Pedido> pedidos = pedidoService.listarPeidoPorIdCliente(idCliente);
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Buscar", description = "Método que busca os pedidos por id do produto", tags = "Pedidos")
    @GetMapping("/buscarPorProduto/{idProduto}")
    public ResponseEntity<List<Pedido>> listarPorProduto(@PathVariable Long idProduto) {
        List<Pedido> pedidos = pedidoService.listarPorIdProduto(idProduto);
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pedidos);
    }
}