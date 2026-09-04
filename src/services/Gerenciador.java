package services;

import java.util.ArrayList;
import java.util.List;
import entidades.Pecas;
import entidades.Servico;

public class Gerenciador {
	static List<Pecas> pecas = new ArrayList<>();
	static List<Servico> servicos = new ArrayList<>();

	public static void createPeca(int codNumerico, String nomePeca, String nomeFabricante, int quantidadePeca,
			double precoVenda, double precoCusto) {
		if (nomePeca == null || nomePeca.isBlank()) {
			System.out.println("Erro ao criar Peça, nome vazio");
			return;
		}

		Pecas novaPeca = new Pecas(codNumerico, nomePeca, nomeFabricante, quantidadePeca, precoVenda, precoCusto);
		pecas.add(novaPeca);
		System.out.println("Peça criada com sucesso!");
	}

	public static void deletePeca(int codNumerico) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codNumerico) {
				pecas.remove(i);
				System.out.println("Peça removida com sucesso!");
				return;
			}
		}
		System.out.println("Peça com código " + codNumerico + " não encontrada.");
	}

	public static void createServico(int codNumerico, String descricaoServico, int tempoEstimado, double valorMaoObra) {
		if (descricaoServico == null || descricaoServico.isBlank()) {
			System.out.println("Erro ao criar Serviço, descrição vazia");
			return;
		}

		Servico novaServico = new Servico(codNumerico, descricaoServico, tempoEstimado, valorMaoObra);
		servicos.add(novaServico);
		System.out.println("Serviço criado e salvo com sucesso!");
	}

	public static void deleteServico(int codNumerico) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codNumerico) {
				servicos.remove(i);
				System.out.println("Serviço removido com sucesso!");
				return;
			}
		}
		System.out.println("Serviço com código " + codNumerico + " não encontrado.");
	}

	public static void readPecas() {
		if (pecas.isEmpty()) {
			System.out.println("Nenhuma peça cadastrada.");
			return;
		}
		for (int i = 0; i < pecas.size(); i++) {
			System.out.println("Código: " + pecas.get(i).getCodNumerico() + " | Nome: " + pecas.get(i).getNomePeca()
					+ " | Fabricante: " + pecas.get(i).getNomeFabricante() + " | Quantidade: "
					+ pecas.get(i).getQuantidadePeca());
		}
	}

	public static void updatePeca(int codigoAlvo, String novoNomePeca, String novoNomeFabricante,
			int novaQuantidadePeca, double novoPrecoVenda, double novoPrecoCusto) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codigoAlvo) {
				pecas.get(i).setNomePeca(novoNomePeca);
				pecas.get(i).setNomeFabricante(novoNomeFabricante);
				pecas.get(i).setQuantidadePeca(novaQuantidadePeca);
				pecas.get(i).setPrecoVenda(novoPrecoVenda);
				pecas.get(i).setPrecoCusto(novoPrecoCusto);
				System.out.println("Peça atualizada com sucesso!");
				return;
			}
		}
		System.out.println("Peça com código " + codigoAlvo + " não encontrada para atualização.");
	}

	public static void buscaLinearPeca(int codigoBuscado) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codigoBuscado) {
				System.out.println("PecaIndex = " + i + "\n " + "Codigo da peca: " + pecas.get(i).getCodNumerico());
				return;
			}
		}
		System.out.println("Peça com código " + codigoBuscado + " não encontrada.");
	}

	public static void readServicos() {
		if (servicos.isEmpty()) {
			System.out.println("Nenhum serviço cadastrado.");
			return;
		}
		for (int i = 0; i < servicos.size(); i++) {
			System.out.println("Código: " + servicos.get(i).getCodNumerico() + " | Descrição: "
					+ servicos.get(i).getDescricaoServico() + " | Tempo Estimado: " + servicos.get(i).getTempoEstimado()
					+ " min" + " | Valor Mão de Obra: R$ " + servicos.get(i).getValorMaoObra());
		}
	}

	public static void updateServico(int codigoAlvo, String novaDescricao, int novoTempoEstimado,
			double novoValorMaoObra) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codigoAlvo) {
				servicos.get(i).setDescricaoServico(novaDescricao);
				servicos.get(i).setTempoEstimado(novoTempoEstimado);
				servicos.get(i).setValorMaoObra(novoValorMaoObra);
				System.out.println("Serviço atualizado com sucesso!");
				return;
			}
		}
		System.out.println("Serviço com código " + codigoAlvo + " não encontrado para atualização.");
	}

	public static void buscaLinearServico(int codigoBuscado) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codigoBuscado) {
				System.out.println(
						"ServicoIndex = " + i + "\n " + "Codigo do servico: " + servicos.get(i).getCodNumerico());
				return;
			}
		}
		System.out.println("Serviço com código " + codigoBuscado + " não encontrado.");
	}

}
