Feature: Listagem e busca de produtos

  Scenario: Listar todos os produtos
    Given existem produtos cadastrados no sistema
    When eu faço uma requisição GET para "/v1/products"
    Then a resposta deve ter o status 200
    And o corpo da resposta deve conter uma lista de produtos

  Scenario: Buscar um produto por SKU
    Given existe um produto com SKU "ABC123" no sistema
    When eu faço uma requisição GET para "/v1/products/ABC123"
    Then a resposta deve ter o status 200
    And o corpo da resposta deve conter "ABC123" no campo "sku"
