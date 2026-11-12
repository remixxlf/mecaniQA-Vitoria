package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoteamentoService {

	public static final String ARQUIVO_ROTAS = Gerenciador.PASTA_DADOS + "/rotas.csv";
	public static final String CENTRO_DISTRIBUICAO = "Centro_Distribuicao";

	private Grafo grafo;
	private int origem = -1;

	public void carregar(String caminho) throws IOException {
		Grafo carregado = LeitorRotas.carregar(caminho);
		if (carregado.quantidadeVertices() == 0) {
			throw new IOException("O arquivo " + caminho + " nao possui nenhuma via valida.");
		}
		int centro = carregado.indiceDe(CENTRO_DISTRIBUICAO);
		this.grafo = carregado;
		this.origem = centro >= 0 ? centro : 0;
	}

	public boolean isCarregado() {
		return grafo != null;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public String getOrigem() {
		return grafo.nomeDe(origem);
	}

	public List<String> listarFiliais() {
		List<String> filiais = new ArrayList<>();
		for (int i = 0; i < grafo.quantidadeVertices(); i++) {
			if (i != origem) {
				filiais.add(grafo.nomeDe(i));
			}
		}
		return filiais;
	}

	public RotaEntrega calcularRota(String filial) {
		int destino = grafo.indiceDe(filial);
		if (destino < 0) {
			throw new IllegalArgumentException("Filial nao encontrada: " + filial);
		}
		return Dijkstra.calcular(grafo, origem, destino);
	}
}
