package com.ada;

public class QuantidadeProduto {
    Integer produto_id;
    Integer quantidade;

    Integer valor_venda_produto;

    public QuantidadeProduto (Integer produto_id,Integer quantidade,Integer valor_venda_produto){
        this.quantidade = quantidade;
        this.produto_id = produto_id;
        this.valor_venda_produto =valor_venda_produto;

    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getValor_venda_produto() {
        return valor_venda_produto;
    }

    public Integer getProduto_id() {
        return produto_id;
    }
}
