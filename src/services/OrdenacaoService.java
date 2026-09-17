package services;

import java.util.List;
import entidades.ChaveOrdenacao;
import entidades.ItemCatalogo;

public class OrdenacaoService {
    public static void bubbleSort(List<ItemCatalogo> lista, ChaveOrdenacao chave) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                ItemCatalogo atual = lista.get(j);
                ItemCatalogo proximo = lista.get(j + 1);

                boolean deveTrocar = (chave == ChaveOrdenacao.NOME)
                        ? atual.getNomeParaOrdenacao().compareToIgnoreCase(proximo.getNomeParaOrdenacao()) > 0
                        : atual.getCodigoParaOrdenacao() > proximo.getCodigoParaOrdenacao();
                if (deveTrocar) {
                    lista.set(j, proximo);
                    lista.set(j + 1, atual);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }
    public static void imprimirRelatorioOrdenadoPorNome(List<ItemCatalogo> catalogo) {
        bubbleSort(catalogo, ChaveOrdenacao.NOME);
        System.out.println("---- Catalogo (Pecas e Servicos) ordenado por nome ----");
        for (ItemCatalogo item : catalogo) {
            System.out.println(item.getCodigoParaOrdenacao() + " - " + item.getNomeParaOrdenacao());
        }
    }
}