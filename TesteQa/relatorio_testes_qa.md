# Relatório de Testes de Q.A — mecaniQA Vitória
**Classe avaliada:** `Gerenciador.java`
**Versão do código:** Original (sem alterações)
**Sessões de referência:** Encontro 2 — Inserção, Remoção e Gestão de Quantidade | Encontro 3 — Busca Linear e Atualização de Dados

---

## Encontro 2 — Inserção, Remoção e Gestão de Quantidade

---

### TC-01 — Inserção de Peça com nome válido

| Campo | Detalhe |
|---|---|
| **ID** | TC-01 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Inserção de uma peça com dados válidos no array |
| **Cenário** | Dado que o sistema está vazio, quando `createPeca(1, "Carburador", "Bosch", 100, 45.50, 20.00)` é chamado, então a peça deve ser salva no índice 0 do array |

**Status:** ✅ PASSOU

**Observação:** O método varre o array com um `for` até encontrar a primeira posição `null` e insere nela. Funciona corretamente, porém de forma ineficiente (varre até 100 posições a cada inserção).

---

### TC-02 — Inserção de Peça com nome vazio

| Campo | Detalhe |
|---|---|
| **ID** | TC-02 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Validação de nome vazio na inserção de Peça |
| **Cenário** | Dado um `nomePeca` nulo ou em branco, quando `createPeca` é chamado, então o sistema deve retornar mensagem de erro e não salvar |

**Status:** ✅ PASSOU

**Observação:** A validação `if (nomePeca == null || nomePeca.isBlank())` existe e funciona corretamente.

---

### TC-03 — Inserção de Serviço com descrição válida

| Campo | Detalhe |
|---|---|
| **ID** | TC-03 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Inserção de um serviço com dados válidos no array |
| **Cenário** | Dado que o sistema está vazio, quando `createServico(101, "Troca de Óleo", 30, 50.00)` é chamado, então o serviço deve ser salvo no array |

**Status:** ✅ PASSOU

**Observação:** Funciona, porém sem validação de dado vazio (veja TC-04).

---

### TC-04 — Inserção de Serviço com descrição vazia

| Campo | Detalhe |
|---|---|
| **ID** | TC-04 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Validação de descrição vazia na inserção de Serviço |
| **Cenário** | Dado uma `descricaoServico` nula ou em branco, quando `createServico` é chamado, então o sistema deve retornar mensagem de erro e não salvar |

**Status:** ❌ NEGADO

**Causa do erro:** O método `createServico` não possui nenhuma validação de dado vazio. O objeto `Servico` será criado e inserido no array mesmo com descrição nula ou em branco.

**Alteração sugerida:**
```java
// Adicionar no início de createServico:
if (descricaoServico == null || descricaoServico.isBlank()) {
    System.out.println("Erro ao criar Serviço, descrição vazia");
    return;
}
```

---

### TC-05 — Remoção de Peça existente (sem "buracos" no array)

| Campo | Detalhe |
|---|---|
| **ID** | TC-05 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Remoção de uma peça e reorganização do array para evitar posições `null` no meio |
| **Cenário** | Dado dois registros nas posições 0 e 1, quando o item da posição 0 é deletado, o item da posição 1 deve ser movido para a posição 0 sem deixar buracos |

**Status:** ✅ PASSOU

**Observação:** O método já usa o loop de reorganização `for (int j = i; j < pecas.length - 1; j++)` corretamente.

---

### TC-06 — Remoção de Serviço existente (sem "buracos" no array)

| Campo | Detalhe |
|---|---|
| **ID** | TC-06 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Remoção de um serviço e reorganização do array |
| **Cenário** | Dado dois registros, quando o primeiro é deletado, o segundo deve ocupar a posição anterior sem buracos |

**Status:** ✅ PASSOU

**Observação:** Funciona corretamente para a reorganização.

---

### TC-07 — Controle de índice de inserção sem percorrer o array

| Campo | Detalhe |
|---|---|
| **ID** | TC-07 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Eficiência de inserção: o sistema deve saber onde está o primeiro espaço vazio sem percorrer o array inteiro |
| **Cenário** | A cada inserção, o sistema deve inserir diretamente no índice correto sem laço de repetição auxiliar |

**Status:** ❌ NEGADO

**Causa do erro:** O código original usa um `for` que percorre o array do início até encontrar uma posição `null`. Não existe variável auxiliar de controle de quantidade. Isso representa um custo computacional desnecessário a cada inserção.

**Alteração sugerida:**
```java
// Declarar variável auxiliar:
static int qtdPecas = 0;

// Na inserção, usar diretamente:
pecas[qtdPecas] = novaPeca;
qtdPecas++;
```

---

### TC-08 — Aviso de capacidade máxima atingida

| Campo | Detalhe |
|---|---|
| **ID** | TC-08 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Comportamento do sistema ao tentar inserir além da capacidade máxima do array |
| **Cenário** | Dado que o array de 100 peças está cheio, quando uma 101ª peça é inserida, o sistema deve avisar o usuário |

**Status:** ❌ NEGADO

**Causa do erro:** O `for` simplesmente encerra sem encontrar posição `null` e o método retorna silenciosamente, sem nenhuma mensagem ao usuário.

**Alteração sugerida:**
```java
// Adicionar verificação de capacidade antes de inserir:
if (qtdPecas >= pecas.length) {
    System.out.println("Capacidade máxima de peças atingida!");
    return;
}
```

---

## Encontro 3 — Busca Linear e Atualização de Dados

---

### TC-09 — Busca Linear de Peça existente

| Campo | Detalhe |
|---|---|
| **ID** | TC-09 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Localização de uma peça pelo código numérico |
| **Cenário** | Dado uma peça com `codNumerico = 1` cadastrada, quando `buscaLinearPeca(1)` é chamado, então o índice e código da peça devem ser exibidos |

**Status:** ✅ PASSOU

**Observação:** O método localiza e exibe o resultado corretamente. Porém, continua varrendo o array após encontrar o item (sem `return`), e não avisa o usuário quando o item não existe.

---

### TC-10 — Busca Linear de Peça inexistente (feedback de erro)

| Campo | Detalhe |
|---|---|
| **ID** | TC-10 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Comportamento da busca quando a peça não existe no array |
| **Cenário** | Dado que nenhuma peça com `codNumerico = 99` foi cadastrada, quando `buscaLinearPeca(99)` é chamado, então o sistema deve informar que o item não foi encontrado |

**Status:** ❌ NEGADO

**Causa do erro:** O método retorna `void` e falha silenciosamente. Quando o código não é encontrado, o `for` encerra e nada é impresso na tela.

**Alteração sugerida:**
```java
public static int buscaLinearPeca(int codigoBuscado) {
    for (int i = 0; i < pecas.length; i++) {
        if (pecas[i] != null && pecas[i].getCodNumerico() == codigoBuscado) {
            return i; // Retorna o índice encontrado
        }
    }
    System.out.println("Erro: Peça não encontrada.");
    return -1; // Indicativo padrão de não encontrado em Java
}
```

---

### TC-11 — Busca Linear de Serviço existente

| Campo | Detalhe |
|---|---|
| **ID** | TC-11 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | Localização de um serviço pelo código numérico |
| **Cenário** | Dado um serviço com `codNumerico = 101` cadastrado, quando `buscaLinearServico(101)` é chamado, então o sistema deve localizar e exibir o serviço corretamente |

**Status:** ❌ NEGADO — BUG CRÍTICO

**Causa do erro:** O método possui uma validação logicamente impossível inserida dentro do bloco de sucesso da busca:

```java
// Código original com bug crítico
if (codigoBuscado < 0 || codigoBuscado > servicos.length) {
    System.out.println("Erro ao buscar serviço");
    return;
}
```

Esta linha compara o **ID do serviço** (ex: 101) com o **tamanho do array** (50). Como `101 > 50` é verdadeiro, o sistema encontra o serviço e na mesma linha dispara um erro, abortando com resultado contraditório.

**Alteração sugerida:**
```java
public static int buscaLinearServico(int codigoBuscado) {
    for (int i = 0; i < servicos.length; i++) {
        if (servicos[i] != null && servicos[i].getCodNumerico() == codigoBuscado) {
            return i;
        }
    }
    System.out.println("Erro: Serviço não encontrado.");
    return -1;
}
```

---

### TC-12 — Retorno da Busca Linear (tipo `int` com indicativo de erro)

| Campo | Detalhe |
|---|---|
| **ID** | TC-12 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | A busca deve retornar um `int` para ser reutilizada por outros métodos |
| **Cenário** | O método `buscaLinear` deve retornar o índice do item se encontrado, ou `-1` caso contrário |

**Status:** ❌ NEGADO

**Causa do erro:** Ambos os métodos de busca retornam `void`. Isso impede que sejam reutilizados pelos métodos de atualização (`update`), forçando duplicação de código.

**Alteração sugerida:** Alterar a assinatura para `public static int buscaLinearPeca(...)` retornando `i` quando encontrar e `-1` quando não encontrar.

---

### TC-13 — Atualização de Peça reutiliza a Busca Linear (DRY)

| Campo | Detalhe |
|---|---|
| **ID** | TC-13 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | O método `updatePeca` deve usar `buscaLinearPeca` internamente, sem repetir o laço `for` |
| **Cenário** | Dado uma peça cadastrada, quando `updatePeca` é chamado, ele deve delegar a localização à `buscaLinearPeca` antes de atualizar |

**Status:** ❌ NEGADO

**Causa do erro:** O método `updatePeca` repete um laço `for` idêntico ao da busca, violando o princípio **DRY** (Don't Repeat Yourself).

**Alteração sugerida:**
```java
public static void updatePeca(int codigoAlvo, ...) {
    int index = buscaLinearPeca(codigoAlvo); // Reutiliza a busca
    if (index != -1) {
        pecas[index].setNomePeca(novoNomePeca);
        // ...restante dos setters
    }
}
```

---

### TC-14 — Atualização de Serviço reutiliza a Busca Linear (DRY)

| Campo | Detalhe |
|---|---|
| **ID** | TC-14 |
| **Responsável (QA)** | Q.A mecaniQA |
| **Data/Hora** | 25/08/2026 — 22:30 |
| **O que está sendo testado** | O método `updateServico` deve usar `buscaLinearServico` internamente, sem repetir o laço `for` |
| **Cenário** | Dado um serviço cadastrado, quando `updateServico` é chamado, ele deve delegar a localização à `buscaLinearServico` antes de atualizar |

**Status:** ❌ NEGADO

**Causa do erro:** Mesma violação do princípio DRY do TC-13. O `updateServico` duplica o laço de busca desnecessariamente.

**Alteração sugerida:** Mesma abordagem do TC-13, delegando a localização para `buscaLinearServico(codigoAlvo)`.

---

## Resumo dos Resultados

| ID | Descrição | Encontro | Status |
|---|---|---|---|
| TC-01 | Inserção de Peça com nome válido | Encontro 2 | ✅ Passou |
| TC-02 | Inserção de Peça com nome vazio | Encontro 2 | ✅ Passou |
| TC-03 | Inserção de Serviço com descrição válida | Encontro 2 | ✅ Passou |
| TC-04 | Inserção de Serviço com descrição vazia | Encontro 2 | ❌ Negado |
| TC-05 | Remoção de Peça sem buracos no array | Encontro 2 | ✅ Passou |
| TC-06 | Remoção de Serviço sem buracos no array | Encontro 2 | ✅ Passou |
| TC-07 | Inserção sem percorrer o array (variável auxiliar) | Encontro 2 | ❌ Negado |
| TC-08 | Aviso de capacidade máxima atingida | Encontro 2 | ❌ Negado |
| TC-09 | Busca Linear de Peça existente | Encontro 3 | ✅ Passou |
| TC-10 | Busca Linear de Peça inexistente (feedback) | Encontro 3 | ❌ Negado |
| TC-11 | Busca Linear de Serviço existente | Encontro 3 | ❌ NEGADO (Bug Crítico) |
| TC-12 | Retorno `int` com indicativo de erro (-1) | Encontro 3 | ❌ Negado |
| TC-13 | Update de Peça reutiliza busca (DRY) | Encontro 3 | ❌ Negado |
| TC-14 | Update de Serviço reutiliza busca (DRY) | Encontro 3 | ❌ Negado |

**Total: 5 Passaram ✅ | 9 Negados ❌**
