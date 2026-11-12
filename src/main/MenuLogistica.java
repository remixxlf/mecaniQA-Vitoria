package main;

import java.util.List;
import java.util.Scanner;

import entidades.ItemPedido;
import entidades.Pedido;
import services.Gerenciador;
import services.Grafo;
import services.RoteamentoService;
import services.RotaEntrega;

public class MenuLogistica {

	private final RoteamentoService roteamento;
	private final Scanner scanner;

	public MenuLogistica(RoteamentoService roteamento) {
		this.roteamento = roteamento;
		this.scanner = new Scanner(System.in);
	}

	public void executar() {
		if (!roteamento.isCarregado()) {
			System.out.println("Modulo de logistica indisponivel: a malha viaria nao foi carregada.");
			return;
		}
		while (true) {
			exibirMenu();
			String entrada = lerLinha("Opcao: ");
			if (entrada == null || entrada.equals("0")) {
				System.out.println("Saindo da Logistica de Entrega.");
				return;
			}
			switch (entrada) {
			case "1":
				calcularEntrega();
				break;
			case "2":
				listarFiliais();
				break;
			case "3":
				exibirMalha();
				break;
			default:
				System.out.println("Opcao invalida.");
			}
		}
	}

	private void exibirMenu() {
		System.out.println("\n===== LOGISTICA DE ENTREGA =====");
		System.out.println("1 - Calcular rota de entrega");
		System.out.println("2 - Listar filiais");
		System.out.println("3 - Exibir malha viaria");
		System.out.println("0 - Sair");
	}

	private void listarFiliais() {
		List<String> filiais = roteamento.listarFiliais();
		System.out.println("\nFiliais disponiveis (origem: " + roteamento.getOrigem() + "):");
		for (int i = 0; i < filiais.size(); i++) {
			System.out.println((i + 1) + " - " + filiais.get(i));
		}
	}

	private void exibirMalha() {
		Grafo grafo = roteamento.getGrafo();
		System.out.println("\nMalha viaria (" + grafo.quantidadeVertices() + " pontos, " + grafo.quantidadeArestas()
				+ " vias):");
		for (int i = 0; i < grafo.quantidadeVertices(); i++) {
			StringBuilder linha = new StringBuilder(grafo.nomeDe(i)).append(":");
			for (Grafo.Aresta aresta : grafo.getAdjacentes(i)) {
				linha.append(" ").append(grafo.nomeDe(aresta.getDestino())).append(" (")
						.append(Grafo.formatarDistancia(aresta.getPeso())).append(")");
			}
			System.out.println(linha);
		}
	}

	private void calcularEntrega() {
		listarFiliais();
		String entrada = lerLinha("\nInforme o numero ou o nome da filial que recebera o lote: ");
		if (entrada == null || entrada.isEmpty()) {
			System.out.println("Nenhuma filial informada.");
			return;
		}
		String filial = resolverFilial(entrada);
		if (filial == null) {
			System.out.println("Filial nao encontrada: " + entrada);
			return;
		}
		RotaEntrega rota = roteamento.calcularRota(filial);
		exibirRota(filial, rota);
		if (rota.isAlcancavel()) {
			vincularPedido();
		}
	}

	private String resolverFilial(String entrada) {
		List<String> filiais = roteamento.listarFiliais();
		if (entrada.matches("\\d+")) {
			try {
				int posicao = Integer.parseInt(entrada);
				if (posicao >= 1 && posicao <= filiais.size()) {
					return filiais.get(posicao - 1);
				}
			} catch (NumberFormatException e) {
				return null;
			}
		}
		int indice = roteamento.getGrafo().indiceDe(entrada);
		return indice < 0 ? null : roteamento.getGrafo().nomeDe(indice);
	}

	private void exibirRota(String filial, RotaEntrega rota) {
		System.out.println("\n===== ROTA DE ENTREGA =====");
		System.out.println("Origem: " + roteamento.getOrigem());
		System.out.println("Destino: " + filial);
		if (!rota.isAlcancavel()) {
			System.out.println("Nao existe caminho entre a origem e o destino informado.");
			return;
		}
		System.out.println("Distancia total: " + Grafo.formatarDistancia(rota.getDistanciaTotal()));
		System.out.println("Percurso: " + rota.descricaoCaminho());
		List<String> caminho = rota.getCaminho();
		for (int i = 0; i < rota.getTrechos().size(); i++) {
			System.out.println("  " + (i + 1) + ". " + caminho.get(i) + " -> " + caminho.get(i + 1) + " ("
					+ Grafo.formatarDistancia(rota.getTrechos().get(i)) + ")");
		}
	}

	private void vincularPedido() {
		String entrada = lerLinha("\nCodigo do pedido transportado (ENTER para ignorar): ");
		if (entrada == null || entrada.isEmpty()) {
			return;
		}
		if (!entrada.matches("\\d{1,9}")) {
			System.out.println("Codigo de pedido invalido.");
			return;
		}
		Pedido pedido = Gerenciador.buscarPedido(Integer.parseInt(entrada));
		if (pedido == null) {
			System.out.println("Pedido #" + entrada + " nao encontrado.");
			return;
		}
		System.out.println("\n---- Pecas enviadas no caminhao (Pedido #" + pedido.getCodigoPedido() + " - "
				+ pedido.getStatus() + ") ----");
		if (pedido.getItens().isEmpty()) {
			System.out.println("Nenhuma peca neste pedido.");
			return;
		}
		System.out.printf("%-6s | %-25s | %-4s%n", "Cod", "Peca", "Qtd");
		for (ItemPedido item : pedido.getItens()) {
			System.out.printf("%-6d | %-25s | %-4d%n", item.getPeca().getCodNumerico(), item.getPeca().getNomePeca(),
					item.getQuantidade());
		}
	}

	private String lerLinha(String mensagem) {
		System.out.print(mensagem);
		if (!scanner.hasNextLine()) {
			return null;
		}
		return scanner.nextLine().trim();
	}
}
