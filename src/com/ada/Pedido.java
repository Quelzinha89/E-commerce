package com.ada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Pedido {
    Integer id;
    Date data_da_criacao;
    String status;
    List<QuantidadeProduto> listaprodutos = new ArrayList<QuantidadeProduto>();

    boolean exist;

    public Pedido(){
        StatusENUM aberto = StatusENUM.ABERTO;
        this.data_da_criacao = new Date();
        System.out.println("Data Agora: "+data_da_criacao);
        this.status = aberto.name();
    };
    public void addProduto(Integer produto_id,Integer quantidade,Integer valor_venda_produto){
        this.exist = false;
        if(this.status.equals(StatusENUM.ABERTO.name())){
            listaprodutos.forEach((produto_da_lista)->{
                if(produto_id.equals(produto_da_lista.getProduto_id())){
                    produto_da_lista.setQuantidade(produto_da_lista.getQuantidade()+quantidade);
                    this.exist = true;
                }
            });
            if (!this.exist){
                QuantidadeProduto item = new QuantidadeProduto(produto_id,quantidade,valor_venda_produto);
                listaprodutos.add(item);
            }
            System.out.println(quantidade + " produto(s) com id " + produto_id + " adicionado(s) ao pedido.");
        } else {
            System.out.println("Não é possível adicionar produtos a um pedido que não está aberto.");
        }
    };
    public void removeProduto(Integer produto_id,Integer quantidade){
        if (this.status.equals(StatusENUM.ABERTO.name())) {
            listaprodutos.forEach((produto_da_lista)->{
                if(produto_id.equals(produto_da_lista.getProduto_id())){
                    produto_da_lista.setQuantidade(produto_da_lista.getQuantidade()-quantidade);
                }
            });
            listaprodutos.removeIf(produto_da_lista -> produto_da_lista.getQuantidade()<1);
            System.out.println(quantidade + " produto(s) com id " + produto_id + "(s) removido(s) do pedido.");
        } else {
            System.out.println("Não é possível remover produtos de um pedido que não está aberto.");
        }

    };
    public Integer fecharPedido(){
        Integer id = 0;
        if (this.status.equals(StatusENUM.ABERTO.name())) {
            StatusENUM fechado = StatusENUM.AGUARDANDO_PAGAMENTO;
            this.status= fechado.name();
            id = BDConnection.criar_pedido(this.data_da_criacao,this.status,this.listaprodutos);
        }else {
            System.out.println("Não é possível remover produtos de um pedido que não está aberto.");
        }
        return id;
    };
    public void resgatar_pedido(Integer id){

        Pedido pedidoResgatado = BDConnection.resgatar_pedido(id);
        this.id = pedidoResgatado.getId();
        this.status = pedidoResgatado.getStatus();
        this.data_da_criacao = pedidoResgatado.getData_da_criacao();
        this.listaprodutos = pedidoResgatado.getListaprodutos();

    }
    public void pagarPedido(){
        if(this.status.equals(StatusENUM.AGUARDANDO_PAGAMENTO.name())){
            StatusENUM pago = StatusENUM.PAGO;
            this.status= pago.name();
            BDConnection.alterar_status_pedido(this.id,this.status);
        }
    };
    public void finalizarPedido(){
        if(this.status.equals(StatusENUM.PAGO.name())){
            StatusENUM pago = StatusENUM.FINALIZADO;
            this.status= pago.name();
            BDConnection.alterar_status_pedido(this.id,this.status);
        }
    };

    public List<QuantidadeProduto> getListaprodutos() {
        return listaprodutos;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Date getData_da_criacao() {
        return data_da_criacao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setData_da_criacao(Date data_da_criacao) {
        this.data_da_criacao = data_da_criacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
