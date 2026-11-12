# Relatório de Testes de Q.A — OAT 4: A Melhor Rota de Entrega

**Projeto:** MecâniQA — Automotive Tech  
**Classes avaliadas:** `Grafo.java`, `LeitorRotas.java`, `Dijkstra.java`, `RotaEntrega.java`, `RoteamentoService.java`, `MenuLogistica.java`, `Gerenciador.java`  
**Referência oficial:** `OAT 4 - A melhor rota de Entrega.docx`  
**Automação:** `TesteQaOat4.java` (testes QA-01 a QA-08) e execução do `Main.java` com o menu de Logística de Entrega (testes QA-09 a QA-11)  
**Data da Avaliação:** 12/11/2026  
**Responsável (QA):** Matheus Costa (Q.A mecaniQA Vitória)  

---

## 1. Mapa Logístico — Grafo e Carregamento Dinâmico

### QA-01 — Carregamento Dinâmico do Grafo a partir do arquivo

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-01 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Leitura linha a linha do arquivo de rotas, `split` por `;` e população da Lista de Adjacência |
| **Cenário** | Dado que o arquivo contém as vias `A;B;4`, `B;C;3`, `A;C;10` e `C;D;2`, Quando o arquivo for carregado, Então o grafo deve possuir 4 vértices e 4 arestas, sem nenhuma via escrita no código-fonte. |

**Status:** ✅ PASSOU  

**Observação Técnica:** Os vértices são criados sob demanda a cada nome novo encontrado no arquivo, o que permite grafos de qualquer tamanho apenas trocando o `rotas.csv`.

---

### QA-02 — Grafo Não-Direcionado (Ida e Volta)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-02 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Registro simétrico das vias de mão dupla |
| **Cenário** | Dado que o arquivo contém `A;B;12`, Quando o grafo for carregado, Então a distância de A para B e de B para A deve ser 12. |

**Status:** ✅ PASSOU  

**Observação Técnica:** Cada linha do arquivo gera duas arestas na Lista de Adjacência, uma em cada sentido, com o mesmo peso.

---

### QA-05 — Tolerância a Linhas Inválidas

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-05 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Robustez da leitura frente a cabeçalho, linhas em branco, campos faltando, distância não numérica, distância negativa e origem vazia |
| **Cenário** | Dado um arquivo com cabeçalho, linha vazia, `A;C`, `A;D;abc`, `A;E;-3`, `;F;2` e `B;C;2,5`, Quando o arquivo for carregado, Então somente as vias válidas devem entrar no grafo e a distância `2,5` deve ser lida como 2.5. |

**Status:** ✅ PASSOU  

**Observação Técnica:** Cada linha rejeitada é ignorada com aviso no terminal informando o número da linha e o motivo. O resultado foi 3 vértices e 2 arestas, sem interromper o carregamento.

---

### QA-08 — Origem Ausente no Arquivo

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-08 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Comportamento do sistema quando o arquivo não possui o vértice `Centro_Distribuicao` |
| **Cenário** | Dado um arquivo com `X;Y;3` e `Y;Z;4`, Quando o serviço de roteamento carregar o arquivo, Então a primeira localidade lida (X) deve virar a origem e a rota X até Z deve ter 7 km. |

**Status:** ✅ PASSOU  

---

## 2. Motor de Roteamento — Algoritmo de Dijkstra

### QA-03 — Menor Caminho por Rota Indireta

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-03 (inclui as verificações 03b e 03c da automação) |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Relaxamento de arestas e escolha do caminho de menor custo, mesmo quando existe via direta mais longa |
| **Cenário** | Dado o grafo `A–B (4)`, `B–C (3)`, `A–C (10)` e `C–D (2)`, Quando calcularmos a rota de A até D, Então o caminho deve ser A → B → C → D com distância total 9. |

**Status:** ✅ PASSOU  

**Observação Técnica:** A rota inversa (D até A) retornou a mesma distância 9, e a rota com origem igual ao destino retornou distância 0 com um único vértice no caminho. O algoritmo foi implementado do zero, com vetores de distância, antecessor e visitado, sem biblioteca de grafos.

---

### QA-04 — Destino Inalcançável

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-04 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Tratamento de filial sem ligação viária com a origem |
| **Cenário** | Dado o grafo com duas partes isoladas (`A;B;4` e `C;D;2`), Quando calcularmos a rota de A até D, Então o sistema deve indicar que o destino é inalcançável, sem travar. |

**Status:** ✅ PASSOU  

---

### QA-06 — Mapa Real do Projeto (`dados/rotas.csv`)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-06 (inclui a verificação 06b da automação) |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Cálculo das rotas sobre o arquivo oficial e busca de filial sem diferenciar maiúsculas, espaços e acentos |
| **Cenário** | Dado o `rotas.csv` do projeto, Quando calcularmos a rota até `Filial_Sobradinho`, Então o caminho deve ser Centro_Distribuicao → Filial_Tomba → Filial_Santa_Monica → Filial_Sobradinho com 35 km, e a busca por `filial santa monica` deve retornar 21 km. |

**Status:** ✅ PASSOU  

**Observação Técnica:** A via direta até Sobradinho tem 40 km, portanto o Dijkstra provou que o trajeto indireto de 35 km é o mais curto.

---

## 3. Integração e Menu de Logística de Entrega

### QA-07 — Pedido Localizado via Árvore AVL

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-07 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Novo índice AVL de Pedidos no `Gerenciador` (integração com as OAT 2 e OAT 3) |
| **Cenário** | Dado que um pedido foi aberto com uma peça, Quando `buscarPedido` for chamado com o código do pedido, Então deve retornar o mesmo objeto via Árvore AVL, e um código inexistente deve retornar `null`. |

**Status:** ✅ PASSOU  

**Observação Técnica:** O índice é alimentado em `abrirPedido` e reconstruído em `carregarTudo`, substituindo a busca linear anterior por busca $O(\log n)$.

---

### QA-09 — Menu: Cálculo de Rota com Percurso Completo

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-09 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Saída no terminal do menu "Logística de Entrega" |
| **Cenário** | Dado que o sistema iniciou e carregou a malha de 7 pontos e 10 vias, Quando o usuário escolher a opção 1 e informar `Filial Sobradinho`, Então o terminal deve exibir a distância total, o percurso exato e a distância de cada trecho. |

**Status:** ✅ PASSOU  

**Observação Técnica:** Saída obtida: distância total de 35 km, percurso Centro_Distribuicao → Filial_Tomba → Filial_Santa_Monica → Filial_Sobradinho, com trechos de 15, 6 e 14 km. A filial também pode ser informada pelo número da lista.

---

### QA-10 — Menu: Peças Enviadas no Caminhão

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-10 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Integração da rota com o Pedido da OAT 2 localizado pela AVL da OAT 3 |
| **Cenário** | Dado que a rota foi gerada, Quando o usuário informar o código de pedido 1, Então o sistema deve listar as peças do pedido: Filtro de Oleo (2 un.) e Vela de Ignicao (4 un.). |

**Status:** ✅ PASSOU  

---

### QA-11 — Menu: Entradas Inválidas

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-11 |
| **Responsável (QA)** | Matheus Costa |
| **Data/Hora** | 12/11/2026 |
| **O que está sendo testado** | Tratamento de erros de digitação no menu |
| **Cenário** | Dado o menu aberto, Quando o usuário informar uma filial inexistente e uma opção fora do menu (9), Então o sistema deve exibir mensagem de erro e voltar ao menu sem encerrar. |

**Status:** ✅ PASSOU  

**Observação Técnica:** Mensagens exibidas: `Filial nao encontrada` e `Opcao invalida`. A opção 0 encerra o menu normalmente.

---

## 4. Resumo Geral dos Resultados da OAT 4

| ID | Cenário / Funcionalidade | Área | Status |
|:---:|---|:---:|:---:|
| **QA-01** | Carregamento dinâmico do grafo a partir do arquivo | Mapa Logístico | ✅ Passou |
| **QA-02** | Grafo não-direcionado (ida e volta com mesmo peso) | Mapa Logístico | ✅ Passou |
| **QA-03** | Dijkstra: menor caminho por rota indireta | Motor de Roteamento | ✅ Passou |
| **QA-04** | Destino inalcançável | Motor de Roteamento | ✅ Passou |
| **QA-05** | Tolerância a linhas inválidas no arquivo | Mapa Logístico | ✅ Passou |
| **QA-06** | Mapa real `rotas.csv` e busca de filial flexível | Motor de Roteamento | ✅ Passou |
| **QA-07** | Pedido localizado via Árvore AVL | Integração | ✅ Passou |
| **QA-08** | Origem ausente no arquivo | Mapa Logístico | ✅ Passou |
| **QA-09** | Menu: distância total, percurso e trechos | Menu | ✅ Passou |
| **QA-10** | Menu: peças do pedido enviadas no caminhão | Integração | ✅ Passou |
| **QA-11** | Menu: entradas inválidas | Menu | ✅ Passou |

**Total: 11 Cenários Testados | 11 Aprovados (100% de Sucesso) ✅**
