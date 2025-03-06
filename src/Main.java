import com.ada.Pedido;
import com.ada.Produto;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        Cliente cliente = new Cliente();
        Integer id = cliente.cadastrar("26584085015");
        List<Cliente> clientes = Cliente.listar();
        cliente.atualizar("46009384028", id);
        */
        /*
        Produto produto = new Produto();
        Integer id = produto.cadastrar("Boneca","Brinquedo",7000,2500,500);
        List<Produto> produtos = Produto.listar();
        produto.atualizar(id,"Boneca","Brinquedo",6000,2500,0);
        */
        /*
        List<Produto> produtos = Produto.listar();
        Pedido pedido = new Pedido();
        System.out.println(pedido.getListaprodutos().toString());
        pedido.addProduto(produtos.get(0).getId(),4,4500);
        System.out.println(pedido.getListaprodutos().toString());
        System.out.println(pedido.getListaprodutos().get(0).getQuantidade());
        pedido.removeProduto(produtos.get(0).getId(),2);
        System.out.println(pedido.getListaprodutos().get(0).getQuantidade());
        pedido.addProduto(produtos.get(0).getId(),1,4500);
        System.out.println(pedido.getListaprodutos().get(0).getQuantidade());
        pedido.addProduto(produtos.get(1).getId(),5,4500);
        Integer id = pedido.fecharPedido();
        System.out.println(id);
        */
        Pedido pedido = new Pedido();
        pedido.resgatar_pedido(4);
        //pedido.pagarPedido();
        pedido.finalizarPedido();

    }

}