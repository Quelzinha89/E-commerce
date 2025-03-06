# Ada Commerce - E-Commerce

## Descrição do Projeto

Projeto Ada tech para com o objetivo de desenvolver um e-commerce capaz de gerenciar clientes, produtos e vendas. O sistema deve permitir:

* Cadastro, listagem e atualização de clientes.
* Cadastro, listagem e atualização de produtos.
* Criação de vendas, incluindo adição, remoção e alteração de itens, pagamento e entrega.

## Regras de Negócio

* Todo cliente deve ter um documento de identificação.
* Pedidos devem ter data de criação e status inicial "aberto".
* Pedidos abertos permitem adicionar, alterar e remover itens.
* Para finalizar um pedido, ele deve ter itens e valor maior que zero, alterando o status para "Aguardando pagamento" e notificando o cliente.
* Pagamentos só podem ser realizados em pedidos "Aguardando pagamento", alterando o status para "Pago" e notificando o cliente.
* Pedidos pagos podem ser entregues, alterando o status para "Finalizado" e notificando o cliente.
* Os produtos adicionados ao pedido devem ter um valor de venda informado, pois esse valor pode ser diferente do valor do produto.
* Não é necessário a ação de excluir clientes e produtos, pois esses devem permanecer na base como histórico.

## Tecnologias Utilizadas

* **Banco de Dados:** PostgreSQL
* **Linguagem de Banco de Dados:** SQL
* **Linguagem de Programação:** Java

## Motivação

Optei pelo PostgreSQL devido à sua robustez, confiabilidade e conformidade com os padrões SQL. Além disso, o PostgreSQL é uma excelente opção para aplicações de e-commerce que exigem alta performance e escalabilidade. A linguagem SQL, por sua vez, oferece flexibilidade e poder para manipular e consultar os dados de forma eficiente.

Script Criacao das tabelas: 

CREATE TABLE IF NOT EXISTS public.clientes
(
    id integer NOT NULL,
    documento character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT clientes_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.lista
(
    quantidade_produtos integer,
    produto_id integer,
    pedido_id integer,
    valor_venda_produto integer,
    CONSTRAINT lista_pedido_id_fkey FOREIGN KEY (pedido_id)
        REFERENCES public.pedidos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lista_produto_id_fkey FOREIGN KEY (produto_id)
        REFERENCES public.produtos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.pedidos
(
    id integer NOT NULL,
    data_criacao date,
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pedidos_pkey PRIMARY KEY (id)
)

CREATE TABLE IF NOT EXISTS public.produtos
(
    nome character varying(255) COLLATE pg_catalog."default",
    id integer NOT NULL,
    categoria character varying(255) COLLATE pg_catalog."default",
    valor_venda integer,
    valor_produto integer,
    valor_desconto integer,
    CONSTRAINT produtos_pkey PRIMARY KEY (id)
)
