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
