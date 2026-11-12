package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import entidades.Pedido;
import services.Dijkstra;
import services.Gerenciador;
import services.Grafo;
import services.LeitorRotas;
import services.RoteamentoService;
import services.RotaEntrega;

public class TesteQaOat4 {

	private static boolean todosPassaram = true;

	public static void main(String[] args) throws IOException {
		Path temporario = Files.createTempFile("rotas_teste", ".csv");
		try {
			testarCarregamento(temporario);
			testarNaoDirecionado(temporario);
			testarMenorCaminho(temporario);
			testarInalcancavel(temporario);
			testarLinhasInvalidas(temporario);
			testarArquivoRealDoProjeto();
			testarPedidoViaAvl();
			testarOrigemAusenteNoArquivo(temporario);
		} finally {
			Files.deleteIfExists(temporario);
		}
		System.out.println(todosPassaram ? "\nRESULTADO FINAL: TODOS OS TESTES PASSARAM" : "\nRESULTADO FINAL: HA TESTES FALHANDO");
		if (!todosPassaram) {
			System.exit(1);
		}
	}

	private static void escrever(Path arquivo, String... linhas) throws IOException {
		Files.write(arquivo, Arrays.asList(linhas), StandardCharsets.UTF_8);
	}

	private static void verificar(String nome, boolean condicao) {
		System.out.println("[" + (condicao ? "PASSOU" : "FALHOU") + "] " + nome);
		todosPassaram &= condicao;
	}

	private static void testarCarregamento(Path arquivo) throws IOException {
		escrever(arquivo, "A;B;4", "B;C;3", "A;C;10", "C;D;2");
		Grafo grafo = LeitorRotas.carregar(arquivo.toString());
		verificar("QA-01 Carregamento dinamico: 4 vertices e 4 arestas",
				grafo.quantidadeVertices() == 4 && grafo.quantidadeArestas() == 4);
	}

	private static void testarNaoDirecionado(Path arquivo) throws IOException {
		escrever(arquivo, "A;B;12");
		Grafo grafo = LeitorRotas.carregar(arquivo.toString());
		int a = grafo.indiceDe("A");
		int b = grafo.indiceDe("B");
		verificar("QA-02 Grafo nao direcionado: ida e volta com mesmo peso",
				grafo.pesoEntre(a, b) == 12 && grafo.pesoEntre(b, a) == 12);
	}

	private static void testarMenorCaminho(Path arquivo) throws IOException {
		escrever(arquivo, "A;B;4", "B;C;3", "A;C;10", "C;D;2");
		Grafo grafo = LeitorRotas.carregar(arquivo.toString());
		RotaEntrega rota = Dijkstra.calcular(grafo, grafo.indiceDe("A"), grafo.indiceDe("D"));
		verificar("QA-03 Dijkstra escolhe o caminho indireto mais curto (A -> B -> C -> D = 9)",
				rota.isAlcancavel() && rota.getDistanciaTotal() == 9
						&& rota.getCaminho().equals(Arrays.asList("A", "B", "C", "D")));
		RotaEntrega inversa = Dijkstra.calcular(grafo, grafo.indiceDe("D"), grafo.indiceDe("A"));
		verificar("QA-03b Rota inversa com mesma distancia", inversa.getDistanciaTotal() == 9);
		RotaEntrega propria = Dijkstra.calcular(grafo, grafo.indiceDe("A"), grafo.indiceDe("A"));
		verificar("QA-03c Origem igual ao destino: distancia 0",
				propria.isAlcancavel() && propria.getDistanciaTotal() == 0 && propria.getCaminho().size() == 1);
	}

	private static void testarInalcancavel(Path arquivo) throws IOException {
		escrever(arquivo, "A;B;4", "C;D;2");
		Grafo grafo = LeitorRotas.carregar(arquivo.toString());
		RotaEntrega rota = Dijkstra.calcular(grafo, grafo.indiceDe("A"), grafo.indiceDe("D"));
		verificar("QA-04 Destino em componente desconexa e inalcancavel", !rota.isAlcancavel());
	}

	private static void testarLinhasInvalidas(Path arquivo) throws IOException {
		escrever(arquivo, "Origem;Destino;Distancia_em_Km", "A;B;5", "", "A;C", "A;D;abc", "A;E;-3", ";F;2", "B;C;2,5");
		Grafo grafo = LeitorRotas.carregar(arquivo.toString());
		verificar("QA-05 Linhas invalidas ignoradas e virgula decimal aceita",
				grafo.quantidadeVertices() == 3 && grafo.quantidadeArestas() == 2
						&& grafo.pesoEntre(grafo.indiceDe("B"), grafo.indiceDe("C")) == 2.5);
	}

	private static void testarArquivoRealDoProjeto() throws IOException {
		RoteamentoService roteamento = new RoteamentoService();
		roteamento.carregar(RoteamentoService.ARQUIVO_ROTAS);
		RotaEntrega sobradinho = roteamento.calcularRota("Filial_Sobradinho");
		RotaEntrega santaMonica = roteamento.calcularRota("filial santa monica");
		verificar("QA-06 rotas.csv: Sobradinho a 35 km via Tomba e Santa Monica",
				sobradinho.getDistanciaTotal() == 35 && sobradinho.getCaminho().equals(Arrays.asList(
						"Centro_Distribuicao", "Filial_Tomba", "Filial_Santa_Monica", "Filial_Sobradinho")));
		verificar("QA-06b Busca de filial sem diferenciar caixa, espacos e acentos",
				santaMonica.getDistanciaTotal() == 21);
	}

	private static void testarPedidoViaAvl() {
		Gerenciador.createPeca(9001, "Peca Teste Rota", "Fab", 10, 10.0, 5.0);
		Pedido pedido = Gerenciador.abrirPedido();
		pedido.adicionarItem(Gerenciador.buscarPeca(9001), 3);
		Pedido encontrado = Gerenciador.buscarPedido(pedido.getCodigoPedido());
		verificar("QA-07 Pedido localizado via Arvore AVL",
				encontrado == pedido && encontrado.getItens().size() == 1 && Gerenciador.buscarPedido(999999) == null);
	}

	private static void testarOrigemAusenteNoArquivo(Path arquivo) throws IOException {
		escrever(arquivo, "X;Y;3", "Y;Z;4");
		RoteamentoService roteamento = new RoteamentoService();
		roteamento.carregar(arquivo.toString());
		verificar("QA-08 Sem Centro_Distribuicao no arquivo, a primeira localidade vira origem",
				roteamento.getOrigem().equals("X") && roteamento.calcularRota("Z").getDistanciaTotal() == 7);
	}
}
