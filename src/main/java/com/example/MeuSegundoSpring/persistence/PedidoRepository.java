package com.example.MeuSegundoSpring.persistence;

import com.example.MeuSegundoSpring.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByIdCliente(Long idCliente);

    List<Pedido> findByIdsProdutosContaining(Long idProduto);
}