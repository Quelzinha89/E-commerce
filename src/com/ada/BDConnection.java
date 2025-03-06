package com.ada;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;

public class BDConnection {
    static String dbname = "pdv";
    static String user = "postgres";
    static String pass = "paideia";


    public static Connection connect_to_bd(String dbname, String user, String pass){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if (connection!=null){
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection Failed");
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return connection;
    };
    public static Integer criar_cliente(String documento) {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "clientes";
        ResultSet rs = null;
        Integer id = 1;
        try {
            String sequence = "SELECT nextval('clientes_id');";
            statement=conn.createStatement();
            rs = statement.executeQuery(sequence);
            if(rs.next()){
                id = rs.getInt("nextval");
            }
            System.out.println(id);
            String query = String.format("insert into %s(id,documento) values (%s,'%s');",table_name,id,documento);
            statement.execute(query);
            System.out.println("Cliente Criado!");
        } catch(Exception e){
            System.out.println(e);
        }
        return id;
    };
    public static List<Cliente> retornar_clientes() {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "clientes";
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            String query = String.format("select * from %s;",table_name);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                Cliente cliente = new Cliente(rs.getInt("id"),rs.getString("documento"));
                clientes.add(cliente);
            }
            System.out.println("Consulta Realziada com Sucesso!");
        } catch(Exception e){
            System.out.println(e);
        }
        return clientes;
    };

    public static Integer atualizar_cliente(Integer id, String documento) {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "clientes";
        ResultSet rs = null;
        try {
            statement=conn.createStatement();
            String query = String.format("update %s set documento = '%s' where id = %s;",table_name,documento,id);
            statement.execute(query);
            System.out.println("Cliente Atualizado!");
        } catch(Exception e){
            System.out.println(e);
        }
        return id;
    };

    public static Integer criar_produto(String nome, String categoria, Integer valor_venda, Integer valor_produto, Integer valor_desconto) {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "produtos";
        ResultSet rs = null;
        Integer id = 1;
        try {
            String sequence = "SELECT nextval('produtos_id');";
            statement=conn.createStatement();
            rs = statement.executeQuery(sequence);
            if(rs.next()){
                id = rs.getInt("nextval");
            }
            System.out.println(id);
            String query = String.format("insert into %s(id,nome,categoria,valor_venda,valor_produto,valor_desconto)" +
                    " values (%s,'%s','%s',%s,%s,%s);",table_name,id,nome,categoria, valor_venda, valor_produto, valor_desconto);
            statement.execute(query);
            System.out.println("Produto Criado!");
        } catch(Exception e){
            System.out.println(e);
        }
        return id;
    };

    public static List<Produto> retornar_produtos() {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "produtos";
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            String query = String.format("select * from %s;",table_name);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("categoria"),
                        rs.getInt("valor_venda"),
                        rs.getInt("valor_produto"),
                        rs.getInt("valor_desconto"));
                produtos.add(produto);
            }
            System.out.println("Consulta Realziada com Sucesso!");
        } catch(Exception e){
            System.out.println(e);
        }
        return produtos;
    };

    public static Integer atualizar_produto(Integer id, String nome, String categoria, Integer valor_venda, Integer valor_produto, Integer valor_desconto) {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "produtos";
        ResultSet rs = null;
        try {
            statement=conn.createStatement();
            String query = String.format("update %s set " +
                    "nome = '%s'," +
                    "categoria = '%s'," +
                    "valor_venda = '%s'," +
                    "valor_produto = '%s'," +
                    "valor_desconto = '%s'" +
                    " where id = %s;",table_name,nome,categoria, valor_venda, valor_produto, valor_desconto, id);
            statement.execute(query);
            System.out.println("Produto Atualizado!");
        } catch(Exception e){
            System.out.println(e);
        }
        return id;
    };

    public static Integer criar_pedido(Date data_criacao, String status, List<QuantidadeProduto> listaprodutos){
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name1 = "pedidos";
        ResultSet rs = null;
        Integer id = 1;
        try {
            String sequence = "SELECT nextval('pedidos_id');";
            statement = conn.createStatement();
            rs = statement.executeQuery(sequence);
            if (rs.next()) {
                id = rs.getInt("nextval");
            }
            System.out.println(id);
            Integer finalId = id;
            String query1 = String.format("insert into %s(id,data_criacao,status)" +
                    " values (%s,'%s','%s');",table_name1,id,data_criacao,status);
            statement.execute(query1);
            System.out.println("Pedido Criado com Sucesso!");
            listaprodutos.forEach((produto_da_lista)->{
                String table_name2 = "lista";
                String query2 = String.format("insert into %s(pedido_id,produto_id,quantidade_produtos,valor_venda_produto)" +
                        " values (%s,%s,%s,%s);",
                        table_name2,
                        finalId,
                        produto_da_lista.getProduto_id(),
                        produto_da_lista.getQuantidade(),
                        produto_da_lista.getValor_venda_produto());
                try {
                    statement.execute(query2);
                } catch(Exception e){
                    System.out.println(e);
                }
            });
        }catch(Exception e){
            System.out.println(e);
        }
        return id;
    };

    public static Pedido resgatar_pedido(Integer id){
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "pedidos";
        ResultSet rs = null;
        Pedido pedido= new Pedido();
        try {
            String query = String.format("select * from %s where id = '%s';", table_name, id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                pedido.setId(rs.getInt("id"));
                System.out.println(pedido.getId());
                pedido.setData_da_criacao(rs.getDate("data_criacao"));
                System.out.println(pedido.getData_da_criacao());
                pedido.setStatus(rs.getString("status"));
                System.out.println(pedido.getStatus());
            }
            table_name = "lista";
            query = String.format("select * from %s where pedido_id = '%s';", table_name, id);
            rs = statement.executeQuery(query);
            while(rs.next()){
                QuantidadeProduto quantidadeProduto = new QuantidadeProduto(
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade_produtos"),
                        rs.getInt("valor_venda_produto"));
                System.out.println(quantidadeProduto.getProduto_id());
                System.out.println(quantidadeProduto.getQuantidade());
                System.out.println(quantidadeProduto.getValor_venda_produto());
                pedido.listaprodutos.add(quantidadeProduto);
            }
            System.out.println("Consulta Realziada com Sucesso!");
        } catch(Exception e){
            System.out.println(e);
        }
        return pedido;
    };

    public static Integer alterar_status_pedido(Integer id, String status) {
        Connection conn = BDConnection.connect_to_bd(dbname,user,pass);
        Statement statement;
        String table_name = "pedidos";
        ResultSet rs = null;
        try {
            statement=conn.createStatement();
            String query = String.format("update %s set " +
                    "status = '%s'" +
                    " where id = %s;",table_name,status,id);
            statement.execute(query);
            System.out.println("Pedido Atualizado!");
        } catch(Exception e){
            System.out.println(e);
        }
        return id;
    };

}
