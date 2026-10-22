# Relatório de Testes de Q.A — OAT 3: Indexando os Meus Dados

**Projeto:** MecâniQA — Automotive Tech  
**Classes avaliadas:** `No.java`, `ArvoreAVL.java`, `Gerenciador.java`  
**Referência oficial:** `CADERNO DE GUIAS - OAT 3 - TUTORIAIS.pdf` (Unex) e `OAT 3 - Indexando os meus dados.pdf`  
**Data da Avaliação:** 22/10/2026  
**Responsável (QA):** Luis Filipe (Q.A mecaniQA Vitória)  

---

## 1. Encontro 1 — Nó Genérico e Desacoplamento Arquitetural

### QA-01 — Criação de Nó Genérico

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-01 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 29/09/2026 — 19:30 |
| **O que está sendo testado** | Validação da estrutura `No.java` estilo struct com atributos genéricos |
| **Cenário** | Dado que INICIALIZAMOS UM NOVO NÓ COM ID 10 E UM OBJETO 'CLIENTE', Quando VERIFICARMOS AS PROPRIEDADES DO NÓ, Então A REFERÊNCIA DE 'ESQUERDA' E 'DIREITA' DEVEM SER NULAS E A ALTURA DEVE SER 1. |

**Status:** ✅ PASSOU  

**Observação Técnica:** O nó armazena `id` (chave de navegação) e `registro` (`Object`), com `altura = 1` e filhos `null`. Atende com 100% de conformidade à especificação do Encontro 1.

---

## 2. Encontro 2 — Inserção em BST e Busca Otimizada Logarítmica

### QA-02 — Busca Otimizada Logarítmica O(log n)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-02 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 06/10/2026 — 19:30 |
| **O que está sendo testado** | Localização de registro existente através do motor da Árvore |
| **Cenário** | Dado que INSERIMOS OS IDs 50, 30 e 70, Quando BUSCARMOS O ID 30, Então O SISTEMA DEVE DESCER À ESQUERDA DA RAIZ E RETORNAR O OBJETO CORRESPONDENTE EM FRAÇÕES DE SEGUNDO. |

**Status:** ✅ PASSOU  

**Observação Técnica:** A árvore organiza o ID 30 à esquerda da raiz (50) e o ID 70 à direita. A busca desce diretamente para a subárvore esquerda com tempo $O(\log n)$, sem percorrer elementos sequencialmente.

---

## 3. Encontro 3 — Travessia Em-Ordem e Relatórios sem Bubble Sort

### QA-03 — Listagem Em-Ordem Crescente

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-03 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 13/10/2026 — 19:30 |
| **O que está sendo testado** | Geração de relatórios ordenados crescentemente sem algoritmos temporários |
| **Cenário** | Dado que INSERIMOS OS IDs FORA DE ORDEM: 10, 5, 20, 2, Quando SOLICITARMOS A EXIBIÇÃO GERAL, Então O ALGORITMO DEVE IMPRIMIR NA TELA EXATAMENTE A SEQUÊNCIA: 2, 5, 10, 20. |

**Status:** ✅ PASSOU  

**Observação Técnica:** O percurso Em-Ordem (In-Order: Esquerda $\to$ Raiz $\to$ Direita) visita os nós em ordem estritamente crescente em tempo linear $O(n)$, eliminando a necessidade do Bubble Sort da OAT 2 para classificação por código identificador.

---

## 4. Encontro 4 — Balanceamento Dinâmico e Rotações AVL

### QA-04 — Rotação Simples à Direita (Caso Esquerda-Esquerda)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-04 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 20/10/2026 — 19:30 |
| **O que está sendo testado** | Detecção de desbalanceamento crítico do lado esquerdo e execução de rotação simples |
| **Cenário** | Dado que A RAIZ É 3 E INSERIMOS 2 (FICANDO À ESQUERDA), Quando INSERIRMOS O ID 1, Então A ÁRVORE DEVE DETECTAR DESBALANCEAMENTO (+2) NO NÓ 3 E EXECUTAR UMA ROTAÇÃO, TORNANDO O NÓ 2 A NOVA RAIZ. |

**Status:** ✅ PASSOU  

**Observação Técnica:** A inserção do nó 1 gerou fator de balanceamento $+2$ no nó 3. A rotação à direita foi disparada imediatamente, promovendo o nó 2 a nova raiz (altura 2, FB = 0), com o nó 1 à esquerda e o nó 3 à direita.

---

### QA-05 — Rotação Simples à Esquerda (Caso Direita-Direita)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-05 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 22/10/2026 — 18:00 |
| **O que está sendo testado** | Detecção de desbalanceamento crítico do lado direito e execução de rotação simples |
| **Cenário** | Dado que inserimos sequencialmente os IDs 10, 20 e 30, Quando o nó 30 for inserido, Então a árvore deve detectar FB = -2 no nó 10 e executar rotação simples à esquerda, tornando o nó 20 a nova raiz. |

**Status:** ✅ PASSOU  

---

### QA-06 — Rotação Dupla Esquerda-Direita (RED)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-06 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 22/10/2026 — 18:00 |
| **O que está sendo testado** | Correção de desbalanceamento em ziguezague com filho esquerdo pesado à direita |
| **Cenário** | Dado inserção dos IDs 30, 10 e 20, Quando o ID 20 for inserido, Então o sistema deve aplicar rotação à esquerda no filho (10) seguida de rotação à direita no pai (30), promovendo 20 a raiz. |

**Status:** ✅ PASSOU  

---

### QA-07 — Rotação Dupla Direita-Esquerda (RDE)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-07 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 22/10/2026 — 18:00 |
| **O que está sendo testado** | Correção de desbalanceamento em ziguezague com filho direito pesado à esquerda |
| **Cenário** | Dado inserção dos IDs 10, 30 e 20, Quando o ID 20 for inserido, Então o sistema deve aplicar rotação à direita no filho (30) seguida de rotação à esquerda no pai (10), promovendo 20 a raiz. |

**Status:** ✅ PASSOU  

---

### QA-08 — Integração dos Índices AVL no Gerenciador

| Campo | Detalhe |
|---|---|
| **ID do Teste** | QA-08 |
| **Responsável (QA)** | Luis Filipe |
| **Data/Hora** | 22/10/2026 — 18:00 |
| **O que está sendo testado** | Sincronização e busca instantânea de Clientes, Peças, Serviços e OS via Árvores AVL |
| **Cenário** | Dado que dados foram cadastrados e recarregados do CSV, Quando `buscarCliente`, `buscarPeca`, `buscarServico` e `buscarOS` forem chamados, Então as consultas devem retornar os objetos via índices AVL em tempo $O(\log n)$. |

**Status:** ✅ PASSOU  

---

## 5. Resumo Geral dos Resultados da OAT 3

| ID | Cenário / Funcionalidade | Encontro de Referência | Status |
|:---:|---|:---:|:---:|
| **QA-01** | Criação de Nó Genérico (id, registro, altura=1) | Encontro 1 (29/09/2026) | ✅ Passou |
| **QA-02** | Busca Otimizada Logarítmica O(log n) | Encontro 2 (06/10/2026) | ✅ Passou |
| **QA-03** | Listagem Em-Ordem Crescente (substituição do Array) | Encontro 3 (13/10/2026) | ✅ Passou |
| **QA-04** | Rotação Simples à Direita (RSD / Esquerda-Esquerda) | Encontro 4 (20/10/2026) | ✅ Passou |
| **QA-05** | Rotação Simples à Esquerda (RSE / Direita-Direita) | Encontro 4 (20/10/2026) | ✅ Passou |
| **QA-06** | Rotação Dupla Esquerda-Direita (RED) | Encontro 4 (20/10/2026) | ✅ Passou |
| **QA-07** | Rotação Dupla Direita-Esquerda (RDE) | Encontro 4 (20/10/2026) | ✅ Passou |
| **QA-08** | Integração Completa dos 4 Índices AVL no Gerenciador | Integração Geral OAT 3 | ✅ Passou |

**Total: 8 Cenários Testados | 8 Aprovados (100% de Sucesso) ✅**
