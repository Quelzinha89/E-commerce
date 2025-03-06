package com.ada;

import java.sql.Connection;
import java.util.List;

public class Produto {
    String nome;
    Integer id;
    String categoria;
    Integer valor_venda;
    Integer valor_produto;
    Integer valor_desconto;

    public Produto(Integer id,String nome, String categoria, Integer valor_venda, Integer valor_produto, Integer valor_desconto){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor_venda = valor_venda;
        this.valor_produto = valor_produto;
        this.valor_desconto = valor_desconto;
    }
    public Produto(){
    }
    public Integer cadastrar(String nome, String categoria, Integer valor_venda, Integer valor_produto, Integer valor_desconto) {
        this.id = BDConnection.criar_produto(nome,categoria,valor_venda,valor_produto,valor_desconto);
        this.nome = nome;
        this.categoria = categoria;
        this.valor_venda = valor_venda;
        this.valor_produto = valor_produto;
        this.valor_desconto = valor_desconto;
        return this.id;
    };
    public  static List<Produto> listar() {
        return BDConnection.retornar_produtos();
    };
    public Integer atualizar(Integer id,String nome, String categoria, Integer valor_venda, Integer valor_produto, Integer valor_desconto) {
        this.id = BDConnection.atualizar_produto(id,nome,categoria,valor_venda,valor_produto,valor_desconto);
        this.nome = nome;
        this.categoria = categoria;
        this.valor_venda = valor_venda;
        this.valor_produto = valor_produto;
        this.valor_desconto = valor_desconto;
        return this.id;
    };

    public Integer getId() {
        return id;
    };
}
