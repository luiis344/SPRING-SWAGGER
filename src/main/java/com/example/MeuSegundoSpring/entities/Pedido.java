package com.example.MeuSegundoSpring.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCliente;

    @ElementCollection
    private List<Long> idsProdutos;

    public Pedido() {}

    public Pedido(Long idCliente, List<Long> idsProdutos) {
        this.idCliente = idCliente;
        this.idsProdutos = idsProdutos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Long> getIdsProdutos() {
        return idsProdutos;
    }

    public void setIdsProdutos(List<Long> idsProdutos) {
        this.idsProdutos = idsProdutos;
    }
}
