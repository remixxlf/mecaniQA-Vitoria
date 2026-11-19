# Resumo da Avaliação - Equipe Vitória (OAT 1)

## 1. Arquivos Revisados
- `src/entidades/Pecas.java`
- `src/entidades/Servico.java`
- `src/services/Gerenciador.java`
- `src/main/Main.java`

## 2. Grade de Avaliação (Barema)

| Critério | Peso | Porcentagem Atingida | Nota Obtida | Resumo da Avaliação |
| :--- | :---: | :---: | :---: | :--- |
| **Aspectos dos Códigos** — Nome significativos de Classes, atributos e métodos | 0,5 | 75% | 0,38 | Nomes no geral claros e compreensíveis, mas houve mistura de idiomas nos métodos (`createPeca` vs `buscaLinearPeca`), classe de entidade no plural (`Pecas`) e variável `novaServico`. |
| **Aspectos dos Códigos** — Nomenclatura de identificadores de acordo com o estilo da linguagem | 0,5 | 100% | 0,50 | Padrões Java (PascalCase para classes e camelCase para atributos e métodos) seguidos corretamente. |
| **TADs e Gerenciador** — Estrutura Base: Definição das classes Peça e Serviço (TADs) estritamente como structs, contendo apenas atributos públicos e nenhum método interno. | 1,0 | 25% | 0,25 | As classes `Pecas` e `Servico` foram criadas com os atributos esperados, porém violam a restrição de serem structs: os atributos não são `public` (visibilidade de pacote) e foram implementados construtores e métodos getters/setters. |
| **TADs e Gerenciador** — Memória: Declaração e inicialização correta dos arrays unidimensionais de tamanho fixo para armazenar os dados. | 1,0 | 100% | 1,00 | Arrays estáticos unidimensionais (`Pecas[100]` e `Servico[50]`) declarados e inicializados corretamente na classe `Gerenciador`, sem uso de `java.util`. |
| **TADs e Gerenciador** — Isolamento: Toda a lógica de negócio (CRUD) foi isolada e implementada através de métodos estáticos na classe de Gerenciador | 1,0 | 100% | 1,00 | Toda a lógica de negócio (Create, Read, Update, Delete e Busca) está isolada em métodos estáticos dentro da classe `Gerenciador`. |
| **Listas Estáticas** — Algoritmo (Busca): Implementação correta do método estático de Busca Linear, retornando o índice do registro ou um indicativo de erro. | 1,0 | 25% | 0,25 | Os métodos `buscaLinearPeca` e `buscaLinearServico` foram declarados como `void` (não retornam o índice encontrado nem `-1` em caso de erro), apenas imprimem no console. Além disso, `buscaLinearServico` tem uma condição de erro lógica incorreta dentro do bloco de sucesso. |
| **Listas Estáticas** — Os métodos de atualização chamam obrigatoriamente os métodos de Busca Linear para descobrir o índice do objeto antes de acessar os atributos diretamente para modificá-los. | 1,0 | 25% | 0,25 | Os métodos `updatePeca` e `updateServico` funcionam, mas implementam laços `for` próprios em vez de chamar os métodos de busca linear, além de usarem setters em vez de acesso direto aos atributos públicos. |
| **Listas Estáticas** — Inserção de Peça: O método isolado adiciona a peça corretamente no primeiro espaço vazio e incrementa a variável de controle específica do array de peças. | 1,0 | 50% | 0,50 | Insere corretamente no primeiro espaço `null` percorrendo o array com um `for`, mas não utiliza variável de controle de quantidade (`totalPecas`) para inserção direta e controle do tamanho lógico. |
| **Listas Estáticas** — Remoção de Peça: Algoritmo (Busca) e reorganização dos elementos contíguos do array de peças, atualizando o índice de controle sem deixar "buracos". | 1,0 | 75% | 0,75 | Realiza corretamente o deslocamento à esquerda (shift-left) sem deixar buracos e limpa a última posição com `null`, mas não reutiliza a busca linear e não possui variável de controle de tamanho para atualizar. |
| **Listas Estáticas** — Inserção de Serviço: O método isolado adiciona o serviço no primeiro espaço vazio e incrementa a variável de controle específica do array de serviços. | 1,0 | 50% | 0,50 | Insere no primeiro espaço `null` via varredura linear, mas também não mantém nem incrementa variável de controle específica (`totalServicos`). |
| **Listas Estáticas** — Remoção de Serviço: O método aplica exclusão lógica ou reorganiza os elementos contíguos do array de serviços, atualizando o índice de controle sem deixar "buracos". | 1,0 | 75% | 0,75 | Faz corretamente o shift-left e limpa a última posição sem deixar buracos, mas sem reutilizar a busca linear e sem variável de controle de tamanho. |

## 3. Resultado Final
- **Aspectos dos Códigos:** 0,88 / 1,00 (0,875)
- **TADs e Gerenciador:** 2,25 / 3,00
- **Listas Estáticas:** 3,00 / 6,00
- **Nota Final:** **6,13 / 10,00** (exato: 6,125)

**Considerações Gerais:** A equipe entregou um CRUD completo e funcional usando arrays estáticos sem `java.util`, e fez um ótimo trabalho no deslocamento de elementos (shift-left) durante a remoção. Entretanto, pontos fundamentais do barema não foram atendidos: as classes `Pecas` e `Servico` receberam construtores e getters/setters em vez de serem structs com atributos públicos; os métodos de busca linear não retornam o índice ou `-1`; as atualizações não chamam a busca linear; e não foram utilizadas variáveis de controle de quantidade (`totalPecas` / `totalServicos`).
