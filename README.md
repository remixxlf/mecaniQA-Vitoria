## MecâniQA - Automotive Tech

## Sobre a empresa

A MecâniQA - Automotive Tech é uma empresa feirense situada na Avenida Visconde do Rio Branco, 1273 - Baraúna, Feira de Santana - BA, 44020-012. Somos uma startup que vem atuando na cidade de Feira de Santana e região com uma solução para gestão de lojas de autopeças, auto centers e oficinas mecânicas. Estamos há pelo menos 3 anos no mercado e vamos iniciar, neste segundo semestre, o nosso programa de trainee. Parabéns por terem sido selecionados. Abaixo, nós vamos explicar o modo como trabalhamos na empresa, como vocês serão avaliados no nosso programa e quais os desafios que serão propostos para vocês.

## Sobre o projeto

### Contexto Inicial do Produto

Nossa base de clientes está crescendo, e precisamos construir a primeira versão do nosso sistema interno de gerenciamento de estoque e serviços (o MVP - Minimum Viable Product). Para garantir que a base do nosso software seja sólida e executada com o máximo de performance inicial, o nosso Diretor de Engenharia (o professor) determinou que o núcleo do sistema seja construído gerenciando a memória de forma manual e contígua.
Neste primeiro desafio, você e sua equipe implementarão um MVP na linguagem de programação Java. A missão de vocês é desenvolver o módulo fundacional do sistema. Vocês deverão modelar os dados fundamentais do nosso negócio (Peças e Serviços) e criar o motor de gerenciamento dessas informações utilizando unicamente listas estáticas na memória.


## Membros da Equipe

* Brendha Maria Costa Seixas 
* Matheus da Costa Pereira
* Victor Paulo Teixeira da Silva e Silva 
* Luis Filipe Silva Lima
* AGSON ASAFE



## OAT 4 - A Melhor Rota de Entrega

Modulo de roteamento para a distribuicao de pecas entre o Centro de Distribuicao e as filiais.

### Funcionalidades

* Malha viaria carregada dinamicamente do arquivo `dados/rotas.csv` (formato `Origem;Destino;Distancia_em_Km`), sem nenhuma via escrita no codigo-fonte.
* Grafo nao direcionado representado por Lista de Adjacencia: cada via e registrada na ida e na volta com o mesmo peso.
* Algoritmo de Dijkstra implementado do zero, com vetores de distancias, antecessores e vertices visitados.
* Menu "Logistica de Entrega": informa distancia total, percurso completo e distancia de cada trecho.
* Integracao com a OAT 2 e a OAT 3: o pedido transportado e localizado na Arvore AVL de pedidos e suas pecas sao listadas.

### Estrutura

* `services/Grafo.java`: vertices, arestas e lista de adjacencia.
* `services/LeitorRotas.java`: leitura e validacao do `rotas.csv`.
* `services/Dijkstra.java`: calculo do caminho minimo.
* `services/RotaEntrega.java`: resultado do calculo (distancia, caminho e trechos).
* `services/RoteamentoService.java`: carrega a malha e calcula a rota a partir do Centro de Distribuicao.
* `main/MenuLogistica.java`: menu de Logistica de Entrega.
* `main/TesteQaOat4.java`: testes automatizados dos cenarios da OAT 4.

### Trocando o mapa

Basta editar `dados/rotas.csv`. O vertice de origem e `Centro_Distribuicao`; se ele nao existir no arquivo, a primeira localidade lida passa a ser a origem.

### Como executar

* Sistema completo: executar `main.Main` (o menu de logistica abre ao final).
* Testes da OAT 4: executar `main.TesteQaOat4`.