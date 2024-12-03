# language: pt

Funcionalidade: Listagem e busca de produtos

  Cenario: Listar todos os produtos
    Dado existem produtos cadastrados no sistema
    Quando eu faço uma requisição GET para "/v1/products"
    Entao a resposta deve ter o status 200

  Cenario: Buscar um produto por SKU
    Dado existe um produto com SKU "ABC123" no sistema
    Quando eu faço uma requisição GET para "/v1/products/ABC123"
    Entao a resposta deve ter o status 200