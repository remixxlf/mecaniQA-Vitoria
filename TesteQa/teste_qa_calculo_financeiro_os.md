# Relatório de Teste de Q.A — Cálculo Financeiro da OS

**Projeto:** MecâniQA — Automotive Tech  
**Entidade avaliada:** `OS.java`  
**Funcionalidade:** Cálculo financeiro automatizado (valor total da OS)  
**Data:** 04/09/2026  

---

### TC-15 — Cálculo Financeiro do Valor Total da OS (Serviços e Peças)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | TC-15 |
| **Responsável (QA)** | Luis Filipe (Q.A mecaniQA) |
| **O que estamos testando? (Contexto)** | Cálculo financeiro automatizado do valor total da Ordem de Serviço varrendo as listas dinâmicas com base na quantidade exata de elementos da própria OS |
| **Cenário (Ação e Resultado)** | **Dado que** uma OS (#1001) foi criada contendo 2 serviços (Troca de Óleo: R$ 60,00 e Alinhamento: R$ 120,00) e 2 peças (Óleo: R$ 45,00 e Filtro: R$ 35,00),<br>**Quando** o método `calcularValorTotal()` for chamado,<br>**Então** o sistema deve varrer dinamicamente os elementos da própria OS e retornar o valor total exato de **R$ 260,00** (R$ 180,00 de serviços + R$ 80,00 de peças) sem erros de execução. |

**Status:** ✅ PASSOU

**Observação Técnica:**  
O somatório utiliza laço de repetição (`for-each` ou `for` com `.size()`) tendo como limite a quantidade real de itens cadastrados na própria OS (`this.servicos.size()` e `this.pecas.size()`). Não há consumo desnecessário de memória nem varredura de posições vazias (`null`), garantindo precisão matemática e alta performance no cálculo financeiro.

---

### TC-16 — Cálculo do Valor Total de uma OS recém-criada (Cenário de Borda: Sem Itens)

| Campo | Detalhe |
|---|---|
| **ID do Teste** | TC-16 |
| **Responsável (QA)** | Luis Filipe (Q.A mecaniQA) |
| **O que estamos testando? (Contexto)** | Comportamento do cálculo financeiro em uma OS vazia (sem serviços e sem peças adicionadas) |
| **Cenário (Ação e Resultado)** | **Dado que** uma nova OS foi instanciada e ainda não possui nenhum serviço nem peça adicionada,<br>**Quando** o método `calcularValorTotal()` for chamado,<br>**Então** o sistema deve retornar **R$ 0,00** sem disparar nenhuma exceção de `NullPointerException` ou índice inválido. |

**Status:** ✅ PASSOU

**Observação Técnica:**  
As listas dinâmicas são inicializadas vazias no construtor (`new ArrayList<>()`). Portanto, com `.size() == 0`, o laço de repetição simplesmente não é executado e o acumulador retorna `0.0` de forma segura.

---

## Resumo dos Resultados

| ID | Funcionalidade | Cenário | Status |
|---|---|---|---|
| **TC-15** | `calcularValorTotal()` | OS completa (Serviços + Peças) | ✅ Passou |
| **TC-16** | `calcularValorTotal()` | OS vazia (Cenário de borda) | ✅ Passou |

**Total: 2 Passaram ✅ | 0 Falharam ❌**
