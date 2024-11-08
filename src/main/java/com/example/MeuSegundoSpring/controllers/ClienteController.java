package com.example.MeuSegundoSpring.controllers;


import com.example.MeuSegundoSpring.entities.Cliente;
import com.example.MeuSegundoSpring.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Salvar", description = "Método que salva os clientes", tags = "Clientes")
    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(cliente));
    }

    @Operation(summary = "Listar", description = "Método que lista os clientes", tags = "Clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @Operation(summary = "Busca", description = "Método que buscar o cliente por Id", tags = "Clientes")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Buscar", description = "Método que busca os clientes por nome", tags = "Clientes")
    @GetMapping("/buscarPorNome/{nome}")
    public ResponseEntity<List<Cliente>> listarPorNome(@PathVariable String nome) {
        List<Cliente> clientes = clienteService.listarPorNome(nome);

        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(clientes);
        }
    }

    @Operation(summary = "Deletar", description = "Método que deleta os clientes", tags = "Clientes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clienteService.buscarClientePorId(id) != null) {
            clienteService.deletarCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}