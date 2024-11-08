package com.example.MeuSegundoSpring.services;

import com.example.MeuSegundoSpring.entities.Pedido;
import com.example.MeuSegundoSpring.persistence.ClienteRepository;
import com.example.MeuSegundoSpring.persistence.PedidoRepository;
import com.example.MeuSegundoSpring.persistence.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getIdCliente() == null || !clienteRepository.existsById(pedido.getIdCliente())) {
            throw new IllegalArgumentException("Cliente inválido.");
        }

        if (pedido.getIdsProdutos() == null || pedido.getIdsProdutos().isEmpty()) {
            throw new IllegalArgumentException("O pedido deve conter pelo menos um produto.");
        }

        for (Long idProduto : pedido.getIdsProdutos()) {
            if (!produtoRepository.existsById(idProduto)) {
                throw new IllegalArgumentException("Produto inválido com ID: " + idProduto);
            }
        }

        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> listarPeidoPorIdCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    public List<Pedido> listarPorIdProduto(Long idProduto) {
        return pedidoRepository.findByIdsProdutosContaining(idProduto);
    }
}