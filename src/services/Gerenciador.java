package services;

import entidades.Pecas;
import entidades.Servico;

public class Gerenciador {
	static Pecas[] pecas = new Pecas[100];
	static Servico[] servicos = new Servico[50];

	public static void createPeca(int codNumerico, String nomePeca, String nomeFabricante, int quantidadePeca,
								  double precoVenda, double precoCusto) {
		if (nomePeca == null || nomePeca.isBlank()) {
			System.out.println("Erro ao criar Peça, nome vazio");
			return;

		}

		Pecas novaPeca = new Pecas(codNumerico, nomePeca, nomeFabricante, quantidadePeca, precoVenda, precoCusto);

		for (int i = 0; i < pecas.length; i++) {

			if (pecas[i] == null) {
				pecas[i] = novaPeca;
				System.out.println("Peça criada e salva com sucesso!");
				return;
			}
		}
	}

	public static void deletePeca(int codNumerico) {
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null && pecas[i].getCodNumerico() == codNumerico) {
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

		for (int i = 0; i < servicos.length; i++) {

			if (servicos[i] == null) {
				servicos[i] = novaServico;
				System.out.println("Serviço criado e salvo com sucesso!");
				break;
			}
		}
	}

	public static void deleteServico(int codNumerico) {
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
		for (int i = 0; i < pecas.length; i++) {
			if (pecas[i] != null && pecas[i].getCodNumerico() == codigoBuscado) {
				System.out.println("PecaIndex = " + i + "\n " + "Codigo da peca: " + pecas[i].getCodNumerico());
			}
		}
	}

	public static void readServicos() {
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
