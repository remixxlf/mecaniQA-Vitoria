package services;

import entidades.Pecas;
import entidades.Servico;

public class Gerenciador {
	// Boa, parabéns! O uso de arrays estáticos unidimensionais de tamanho fixo atende corretamente
	// à restrição de gerenciamento manual de memória sem utilizar Collections (java.util).
	// Porém, faltaram as variáveis de controle de quantidade (ex: totalPecas e totalServicos)
	// para permitir inserções diretas em O(1) sem precisar varrer todo o array.
	static Pecas[] pecas = new Pecas[100];
	static Servico[] servicos = new Servico[50];

	public static void createPeca(int codNumerico, String nomePeca, String nomeFabricante, int quantidadePeca,
								  double precoVenda, double precoCusto) {
		if (nomePeca == null || nomePeca.isBlank()) {
			System.out.println("Erro ao criar Peça, nome vazio");
			return; // isso é um hábito comum em programação, usando o early return, ajuda na legibilidade.

		}

		// Nota: o código numérico deveria ser gerado automaticamente de forma sequencial pelo sistema.
		Pecas novaPeca = new Pecas(codNumerico, nomePeca, nomeFabricante, quantidadePeca, precoVenda, precoCusto);

		// Aqui vocês percorrem o array inteiro procurando o primeiro espaço null (O(n)).
		// Com uma variável controladora (ex: totalPecas), seria possível inserir diretamente em pecas[totalPecas]
		// e incrementar totalPecas em O(1). Também seria bom avisar se o array estiver cheio!
		for (int i = 0; i < pecas.length; i++) {

			if (pecas[i] == null) {
				pecas[i] = novaPeca;
				System.out.println("Peça criada e salva com sucesso!");
				return;
			}
		}
	}

	public static void deletePeca(int codNumerico) {
		// Aqui teria sido bem melhor reutilizar o método de busca linear (retornando o índice)
		// em vez de repetir o laço de busca.
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null && pecas[i].getCodNumerico() == codNumerico) {
				// Boa, parabéns! O deslocamento dos elementos à esquerda (shift-left) evita "buracos" no array.
				// Se houvesse uma variável totalPecas, o laço só precisaria ir até totalPecas - 1.
				for (int j = i; j < pecas.length - 1; j++) {
					pecas[j] = pecas[j + 1];
				}
				pecas[pecas.length - 1] = null;

				System.out.println("Peça removida com sucesso!");
				break;
			}
		}
	}

	public static void createServico(int codNumerico, String descricaoServico, int tempoEstimado, double valorMaoObra) {

		Servico novaServico = new Servico(codNumerico, descricaoServico, tempoEstimado, valorMaoObra);

		// Mesmo comentário da inserção de peças: o ideal era manter uma variável de controle totalServicos
		// para inserir no primeiro espaço vazio e incrementar o contador.
		for (int i = 0; i < servicos.length; i++) {

			if (servicos[i] == null) {
				servicos[i] = novaServico;
				System.out.println("Serviço criado e salvo com sucesso!");
				break;
			}
		}
	}

	public static void deleteServico(int codNumerico) {
		// Boa, parabéns pelo shift-left para não deixar buracos!
		// Mas novamente faltou reutilizar a busca linear e atualizar uma variável controladora totalServicos.
		for (int i = 0; i < servicos.length; i++) {
			if (servicos[i] != null && servicos[i].getCodNumerico() == codNumerico) {
				for (int j = i; j < servicos.length - 1; j++) {
					servicos[j] = servicos[j + 1];
				}
				servicos[servicos.length - 1] = null;

				System.out.println("Serviço removida com sucesso!");
				break;
			}
		}
	}

	public static void readPecas() {
		// Boa, listagem isolada em método estático no Gerenciador!
		// Apenas faltou exibir precoCusto e precoVenda da peça.
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null) {
				System.out.println("Código: " + pecas[i].getCodNumerico() + " | Nome: " + pecas[i].getNomePeca()
						+ " | Fabricante: " + pecas[i].getNomeFabricante() + " | Quantidade: "
						+ pecas[i].getQuantidadePeca());
			}
		}
	}

	public static void updatePeca(int codigoAlvo, String novoNomePeca, String novoNomeFabricante, int novaQuantidadePeca,
								  double novoPrecoVenda, double novoPrecoCusto) {
		// Atenção: o barema exigia que os métodos de atualização chamassem obrigatoriamente
		// o método de Busca Linear para descobrir o índice do objeto antes de modificar seus atributos diretamente.
		// Além disso, aqui foram usados setters em vez de acesso direto aos atributos públicos.
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null && pecas[i].getCodNumerico() == codigoAlvo) {
				pecas[i].setNomePeca(novoNomePeca);
				pecas[i].setNomeFabricante(novoNomeFabricante);
				pecas[i].setQuantidadePeca(novaQuantidadePeca);
				pecas[i].setPrecoVenda(novoPrecoVenda);
				pecas[i].setPrecoCusto(novoPrecoCusto);
				System.out.println("Peça atualizada com sucesso!");
				break;
			}
		}
	}

	public static void buscaLinearPeca(int codigoBuscado) {
		// Atenção: o método de busca linear deveria retornar um int (o índice encontrado ou -1 se não existir),
		// em vez de ser void e apenas imprimir no console. Assim, ele poderia ser reutilizado no update e no delete!
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null && pecas[i].getCodNumerico() == codigoBuscado) {
				System.out.println("PecaIndex = " + i + "\n " + "Codigo da peca: " + pecas[i].getCodNumerico());
			}
		}
	}

	public static void readServicos() {
		// Boa, parabéns pela exibição completa dos dados dos serviços!
		for (int i = 0; i < servicos.length; i++) {
			if (servicos[i] != null) {
				System.out.println("Código: " + servicos[i].getCodNumerico() +
						" | Descrição: " + servicos[i].getDescricaoServico() +
						" | Tempo Estimado: " + servicos[i].getTempoEstimado() + " min" +
						" | Valor Mão de Obra: R$ " + servicos[i].getValorMaoObra());
			}
		}
	}

	public static void updateServico(int codigoAlvo, String novaDescricao, int novoTempoEstimado, double novoValorMaoObra) {
		// Mesmo ponto do updatePeca: deveria chamar a busca linear para obter o índice
		// e atualizar os atributos diretamente (sem setters).
		for (int i = 0; i < servicos.length; i++) {
			if (servicos[i] != null && servicos[i].getCodNumerico() == codigoAlvo) {
				servicos[i].setDescricaoServico(novaDescricao);
				servicos[i].setTempoEstimado(novoTempoEstimado);
				servicos[i].setValorMaoObra(novoValorMaoObra);
				System.out.println("Serviço atualizado com sucesso!");
				break;
			}
		}
	}

	public static void buscaLinearServico(int codigoBuscado) {
		// Mesmo problema da buscaLinearPeca: deveria retornar int (índice ou -1).
		// Além disso, a validação "codigoBuscado < 0 || codigoBuscado > servicos.length" está dentro
		// do if que só executa quando o serviço já foi encontrado, e confunde código do serviço com tamanho do array!
		for (int i = 0; i < servicos.length; i++) {
			if (servicos[i] != null && servicos[i].getCodNumerico() == codigoBuscado) {
				System.out.println("ServicoIndex = " + i + "\n " + "Codigo do servico: " + servicos[i].getCodNumerico());
				if (codigoBuscado < 0 || codigoBuscado> servicos.length) {
					System.out.println("Erro ao buscar serviço");
					return;
				}
			}
		}
	}
}
