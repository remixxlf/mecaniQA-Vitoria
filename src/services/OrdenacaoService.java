package services;

import java.util.ArrayList;
import java.util.List;

import entidades.ChaveOrdenacao;
import entidades.ItemCatalogo;

public class OrdenacaoService {

	public static void bubbleSort(List<ItemCatalogo> lista, ChaveOrdenacao chave) {
		if (lista == null || lista.size() < 2) {
			return;
		}
		int n = lista.size();
		for (int i = 0; i < n - 1; i++) {
			boolean trocou = false;
			for (int j = 0; j < n - 1 - i; j++) {
				ItemCatalogo atual = lista.get(j);
				ItemCatalogo proximo = lista.get(j + 1);

				boolean deveTrocar;
				if (chave == ChaveOrdenacao.NOME) {
					deveTrocar = atual.getNomeParaOrdenacao()
							.compareToIgnoreCase(proximo.getNomeParaOrdenacao()) > 0;
				} else {
					deveTrocar = atual.getCodigoParaOrdenacao() > proximo.getCodigoParaOrdenacao();
				}

				if (deveTrocar) {
					lista.set(j, proximo);
					lista.set(j + 1, atual);
					trocou = true;
				}
			}
			if (!trocou) {
				break;
			}
		}
	}

	public static List<ItemCatalogo> ordenar(List<ItemCatalogo> lista, ChaveOrdenacao chave) {
		List<ItemCatalogo> copia = new ArrayList<>(lista);
		bubbleSort(copia, chave);
		return copia;
	}

	public static void imprimirRelatorio(List<ItemCatalogo> catalogo, ChaveOrdenacao chave) {
		List<ItemCatalogo> ordenado = ordenar(catalogo, chave);
		System.out.println("---- Catalogo (Pecas e Servicos) ordenado por " + chave + " ----");
		if (ordenado.isEmpty()) {
			System.out.println("Nenhum item cadastrado.");
			return;
		}
		System.out.printf("%-8s | %-40s%n", "Codigo", "Nome");
		for (ItemCatalogo item : ordenado) {
			System.out.printf("%-8d | %-40s%n", item.getCodigoParaOrdenacao(), item.getNomeParaOrdenacao());
		}
	}

	public static void imprimirRelatorioOrdenadoPorNome(List<ItemCatalogo> catalogo) {
		imprimirRelatorio(catalogo, ChaveOrdenacao.NOME);
	}
}
