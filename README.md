# MecâniQA - Automotive Tech

## Sobre a empresa

A MecâniQA - Automotive Tech é uma empresa feirense situada na Avenida Visconde do Rio Branco, 1273 - Baraúna, Feira de Santana - BA, 44020-012. Somos uma startup que vem atuando na cidade de Feira de Santana e região com uma solução para gestão de lojas de autopeças, auto centers e oficinas mecânicas. Estamos há pelo menos 3 anos no mercado e vamos iniciar, neste segundo semestre, o nosso programa de trainee. Parabéns por terem sido selecionados. Abaixo, nós vamos explicar o modo como trabalhamos na empresa, como vocês serão avaliados no nosso programa e quais os desafios que serão propostos para vocês.

## Sobre o projeto

### Contexto Inicial do Produto

Nossa base de clientes está crescendo, e precisamos construir a primeira versão do nosso sistema interno de gerenciamento de estoque e serviços (o MVP - Minimum Viable Product). Para garantir que a base do nosso software seja sólida e executada com o máximo de performance inicial, o nosso Diretor de Engenharia (o professor) determinou que o núcleo do sistema seja construído gerenciando a memória de forma manual e contígua.
Neste primeiro desafio, você e sua equipe implementarão um MVP na linguagem de programação Java. A missão de vocês é desenvolver o módulo fundacional do sistema. Vocês deverão modelar os dados fundamentais do nosso negócio (Peças e Serviços) e criar o motor de gerenciamento dessas informações utilizando unicamente listas estáticas na memória.


## Membros da Equipe

* Brendha Maria Costa Seixas 
* Matheus da Costa Pereira
* Guilherme Pereira Barreto 
* Victor Paulo Teixeira da Silva e Silva 
* Luis Filipe Silva Lima
* AGSON ASAFE

## OAT 2 - Ordenando a Fila

Segunda fase do MVP: Modulo de Atendimento e Vendas.

### O que foi implementado

* **Clientes e Carros:** cliente com codigo, nome, WhatsApp e e-mail, com lista dinamica de carros associados. Carro com modelo, placa, ano e estilo restrito pelo enum `EstiloCarro` (HATCH, SEDAN, SUV).
* **Ordem de Servico:** codigo unico gerado pelo sistema, lista dinamica de servicos, ciclo de vida pelo enum `StatusOs` (EM_ABERTO, AGUARDANDO_EXECUCAO, EM_EXECUCAO, FINALIZADA), bloqueio de adicao/remocao apos o fechamento e os metodos `quantidadeServicos()`, `valorTotal()` e `tabelaServicos()`.
* **Fila de Atendimento (FIFO):** implementada do zero com lista encadeada (`enfileirar`, `desenfileirar`, `espiarProximo`, `estaVazia`). Ao mudar a OS para AGUARDANDO_EXECUCAO os servicos sao despachados para a fila.
* **Pedidos:** codigo unico, estrutura intermediaria `ItemPedido` (peca + quantidade), calculo de total, aplicacao de desconto, relatorio e bloqueio apos finalizacao.
* **Motor de ordenacao:** Bubble Sort implementado do zero em `OrdenacaoService`, recebendo a chave via enum `ChaveOrdenacao` (NOME ou CODIGO_IDENTIFICADOR), ordenando pecas e servicos pela interface `ItemCatalogo`.
* **Persistencia CSV:** `PersistenciaService` grava e le Clientes, Carros, Pecas, Servicos, Pedidos e OS em arquivos `.csv` com delimitador `;` na pasta `dados/`.

### Estrutura

```
src/
  entidades/   Clientes, Carros, Pecas, Servico, OS, Pedido, ItemPedido, ItemCatalogo e enums
  services/    Gerenciador, FilaAtendimento, OrdenacaoService, PersistenciaService
  main/        Main (demonstracao de todos os requisitos)
```

### Como executar

```bash
gradle run
```

ou, sem Gradle:

```bash
javac -encoding UTF-8 -d bin $(find src -name "*.java")
java -cp bin main.Main
```
