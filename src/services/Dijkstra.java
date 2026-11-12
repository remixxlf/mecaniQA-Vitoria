package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	public static RotaEntrega calcular(Grafo grafo, int origem, int destino) {
		int n = grafo.quantidadeVertices();
		if (origem < 0 || origem >= n || destino < 0 || destino >= n) {
			throw new IllegalArgumentException("Vertice inexistente no grafo.");
		}

		double[] distancia = new double[n];
		int[] anterior = new int[n];
		boolean[] visitado = new boolean[n];
		Arrays.fill(distancia, Double.POSITIVE_INFINITY);
		Arrays.fill(anterior, -1);
		distancia[origem] = 0;

		for (int i = 0; i < n; i++) {
			int atual = selecionarMenorDistancia(distancia, visitado);
			if (atual == -1) {
				break;
			}
			visitado[atual] = true;
			if (atual == destino) {
				break;
			}
			for (Grafo.Aresta aresta : grafo.getAdjacentes(atual)) {
				int vizinho = aresta.getDestino();
				if (visitado[vizinho]) {
					continue;
				}
				double candidata = distancia[atual] + aresta.getPeso();
				if (candidata < distancia[vizinho]) {
					distancia[vizinho] = candidata;
					anterior[vizinho] = atual;
				}
			}
		}

		if (Double.isInfinite(distancia[destino])) {
			return new RotaEntrega(false, Double.POSITIVE_INFINITY, new ArrayList<>(), new ArrayList<>());
		}

		List<Integer> indicesCaminho = new ArrayList<>();
		for (int v = destino; v != -1; v = anterior[v]) {
			indicesCaminho.add(v);
		}
		Collections.reverse(indicesCaminho);

		List<String> nomes = new ArrayList<>();
		List<Double> trechos = new ArrayList<>();
		for (int i = 0; i < indicesCaminho.size(); i++) {
			nomes.add(grafo.nomeDe(indicesCaminho.get(i)));
			if (i > 0) {
				trechos.add(grafo.pesoEntre(indicesCaminho.get(i - 1), indicesCaminho.get(i)));
			}
		}
		return new RotaEntrega(true, distancia[destino], nomes, trechos);
	}

	private static int selecionarMenorDistancia(double[] distancia, boolean[] visitado) {
		int escolhido = -1;
		double menor = Double.POSITIVE_INFINITY;
		for (int i = 0; i < distancia.length; i++) {
			if (!visitado[i] && distancia[i] < menor) {
				menor = distancia[i];
				escolhido = i;
			}
		}
		return escolhido;
	}
}
