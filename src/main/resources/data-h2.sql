DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS item
(
    order_id
    VARCHAR
(
    255
) NOT NULL,
    sku VARCHAR
(
    255
) NOT NULL,
    quantity INT NOT NULL,
    unit_value DECIMAL
(
    10,
    0
) NOT NULL
    );

CREATE TABLE IF NOT EXISTS product
(
    sku
    VARCHAR
(
    100
) NOT NULL,
    name VARCHAR
(
    100
) NOT NULL,
    description VARCHAR
(
    255
) NOT NULL,
    category VARCHAR
(
    100
) NOT NULL,
    price DECIMAL
(
    10,
    2
) NOT NULL,
    PRIMARY KEY
(
    sku
)
    );

INSERT INTO product (sku, name, description, price, category)
VALUES ('123456A', 'MC Lanche Feliz', 'Hamburguer com queijo, salada especial e queijo', 35.00, 'Lanche'),
       ('123456B', 'Quarteirão Com Queijo', 'Hamburguer com queijo e salada', 28.00, 'Lanche'),
       ('123456C', 'Coca-Cola 500ML', 'Refrigerante Coca-Cola servido no copo de 500ML', 15.00, 'Bebida'),
       ('123456D', 'Coca-Cola 300ML', 'Refrigerante Coca-Cola servido no copo de 300ML', 10.00, 'Bebida'),
       ('123456E', 'Torta de Maça', 'Torta de Maça Caramelizada em uma casquinha crocante', 25.00, 'Sobremesa'),
       ('123456F', 'Sorvete de Casquinha', 'Sorvete de Casquinha com creme de nata e chocolate', 8.00, 'Sobremesa'),
       ('123456G', 'Batata Frita', 'Batats fritas tamanho médio', 7.00, 'Acompanhamento'),
       ('123456H', 'Molho Barbecue', 'Blister com 23g de molho barbecue', 2.00, 'Acompanhamento');
