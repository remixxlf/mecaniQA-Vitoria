package services;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Grafo {

	public static class Aresta {
		private final int destino;
		private final double peso;

		public Aresta(int destino, double peso) {
			this.destino = destino;
			this.peso = peso;
		}

		public int getDestino() {
			return destino;
		}

		public double getPeso() {
			return peso;
		}
	}

	private final List<String> vertices = new ArrayList<>();
	private final Map<String, Integer> indices = new HashMap<>();
	private final List<List<Aresta>> adjacencias = new ArrayList<>();

	public static String normalizar(String nome) {
		String semAcento = Normalizer.normalize(nome.trim(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
		return semAcento.replace(' ', '_').toLowerCase(Locale.ROOT);
	}

	public static String formatarDistancia(double distancia) {
		Locale local = Locale.forLanguageTag("pt-BR");
		if (distancia == Math.rint(distancia)) {
			return String.format(local, "%d km", (long) distancia);
		}
		return String.format(local, "%.2f km", distancia);
	}

	public int adicionarVertice(String nome) {
		String chave = normalizar(nome);
		Integer existente = indices.get(chave);
		if (existente != null) {
			return existente;
		}
		int indice = vertices.size();
		vertices.add(nome.trim());
		indices.put(chave, indice);
		adjacencias.add(new ArrayList<>());
		return indice;
	}

	public void adicionarAresta(String origem, String destino, double peso) {
		if (peso < 0 || Double.isNaN(peso) || Double.isInfinite(peso)) {
			throw new IllegalArgumentException("Peso invalido: " + peso);
		}
		int u = adicionarVertice(origem);
		int v = adicionarVertice(destino);
		if (u == v) {
			return;
		}
		registrarAresta(u, v, peso);
		registrarAresta(v, u, peso);
	}

	private void registrarAresta(int de, int para, double peso) {
		List<Aresta> lista = adjacencias.get(de);
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getDestino() == para) {
				if (peso < lista.get(i).getPeso()) {
					lista.set(i, new Aresta(para, peso));
				}
				return;
			}
		}
		lista.add(new Aresta(para, peso));
	}

	public int indiceDe(String nome) {
		if (nome == null) {
			return -1;
		}
		Integer indice = indices.get(normalizar(nome));
		return indice == null ? -1 : indice;
	}

	public String nomeDe(int indice) {
		return vertices.get(indice);
	}

	public int quantidadeVertices() {
		return vertices.size();
	}

	public int quantidadeArestas() {
		int total = 0;
		for (List<Aresta> lista : adjacencias) {
			total += lista.size();
		}
		return total / 2;
	}

	public List<String> getVertices() {
		return Collections.unmodifiableList(vertices);
	}

	public List<Aresta> getAdjacentes(int indice) {
		return Collections.unmodifiableList(adjacencias.get(indice));
	}

	public double pesoEntre(int de, int para) {
		for (Aresta aresta : adjacencias.get(de)) {
			if (aresta.getDestino() == para) {
				return aresta.getPeso();
			}
		}
		return Double.POSITIVE_INFINITY;
	}
}
