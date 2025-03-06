package com.ada;

import com.ada.BDConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    String documento;
    Integer id;

    public Cliente(Integer id, String documento){
        this.id =id;
        this.documento=documento;
    }
    public Cliente(){
        this.id =0;
        this.documento="00000000000";
    }
    public Integer cadastrar(String documento) {
        this.id = BDConnection.criar_cliente(documento);
        this.documento = documento;
        return this.id;
    };
    public  static List<Cliente> listar() {
        return BDConnection.retornar_clientes();
    };
    public Integer atualizar(String documento,Integer id) {
        this.id = BDConnection.atualizar_cliente(id,documento);
        this.documento = documento;
        return this.id;
    };
}
