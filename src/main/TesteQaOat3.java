package main;

import entidades.Clientes;
import services.ArvoreAVL;
import services.No;

/**
 * Validação automatizada dos Cenários de Teste formais da OAT 3
 * extraídos do CADERNO DE GUIAS - OAT 3 - TUTORIAIS.pdf (Unex):
 * - QA-01: Criação de Nó Genérico
 * - QA-02: Busca Otimizada Logarítmica
 * - QA-03: Listagem Em-Ordem Crescente
 * - QA-04: Rotação Simples à Direita
 */
public class TesteQaOat3 {

	public static void main(String[] args) {
		System.out.println("==================================================================");
		System.out.println("EXECUÇÃO DOS CENÁRIOS DE TESTE DO CADERNO DE TUTORIAIS — OAT 3");
		System.out.println("==================================================================\n");

		boolean todosPassaram = true;

		// -------------------------------------------------------------
		// QA-01: CRIAÇÃO DE NÓ GENÉRICO
		// -------------------------------------------------------------
		System.out.println("[TESTE QA-01] CRIAÇÃO DE NÓ GENÉRICO");
		Clientes clienteExemplo = new Clientes("Cliente Teste", "cliente@teste.com", "(75) 99999-9999", 10);
		No no10 = new No(10, clienteExemplo);
		boolean qa01Passou = (no10.id == 10)
				&& (no10.registro == clienteExemplo)
				&& (no10.esquerda == null)
				&& (no10.direita == null)
				&& (no10.altura == 1);

		System.out.println("   ID: " + no10.id + " (Esperado: 10)");
		System.out.println("   Altura inicial: " + no10.altura + " (Esperado: 1)");
		System.out.println("   Filho Esquerda: " + no10.esquerda + " (Esperado: null)");
		System.out.println("   Filho Direita: " + no10.direita + " (Esperado: null)");
		System.out.println("   Status QA-01: " + (qa01Passou ? "✅ PASSOU" : "❌ FALHOU") + "\n");
		todosPassaram &= qa01Passou;

		// -------------------------------------------------------------
		// QA-02: BUSCA OTIMIZADA LOGARÍTMICA
		// -------------------------------------------------------------
		System.out.println("[TESTE QA-02] BUSCA OTIMIZADA LOGARÍTMICA");
		ArvoreAVL avlBusca = new ArvoreAVL();
		avlBusca.inserir(50, "Objeto 50");
		avlBusca.inserir(30, "Objeto 30");
		avlBusca.inserir(70, "Objeto 70");

		Object encontrado = avlBusca.buscar(30);
		boolean qa02Passou = "Objeto 30".equals(encontrado)
				&& avlBusca.getRaiz() != null
				&& avlBusca.getRaiz().esquerda != null
				&& avlBusca.getRaiz().esquerda.id == 30;

		System.out.println("   Raiz: ID " + avlBusca.getRaiz().id + " (Esperado: 50)");
		System.out.println("   Filho Esquerdo da Raiz: ID " + avlBusca.getRaiz().esquerda.id + " (Esperado: 30)");
		System.out.println("   Resultado da busca ID 30: " + encontrado + " (Esperado: Objeto 30)");
		System.out.println("   Status QA-02: " + (qa02Passou ? "✅ PASSOU" : "❌ FALHOU") + "\n");
		todosPassaram &= qa02Passou;

		// -------------------------------------------------------------
		// QA-03: LISTAGEM EM-ORDEM CRESCENTE
		// -------------------------------------------------------------
		System.out.println("[TESTE QA-03] LISTAGEM EM-ORDEM CRESCENTE");
		ArvoreAVL avlOrdem = new ArvoreAVL();
		// Inserção fora de ordem: 10, 5, 20, 2
		avlOrdem.inserir(10, "Reg 10");
		avlOrdem.inserir(5, "Reg 5");
		avlOrdem.inserir(20, "Reg 20");
		avlOrdem.inserir(2, "Reg 2");

		java.util.List<Object> listaEmOrdem = avlOrdem.obterEmOrdem();
		System.out.println("   Itens obtidos via Em-Ordem:");
		for (Object reg : listaEmOrdem) {
			System.out.println("      " + reg);
		}
		boolean qa03Passou = listaEmOrdem.size() == 4
				&& "Reg 2".equals(listaEmOrdem.get(0))
				&& "Reg 5".equals(listaEmOrdem.get(1))
				&& "Reg 10".equals(listaEmOrdem.get(2))
				&& "Reg 20".equals(listaEmOrdem.get(3));

		System.out.println("   Sequência esperada: Reg 2 -> Reg 5 -> Reg 10 -> Reg 20");
		System.out.println("   Status QA-03: " + (qa03Passou ? "✅ PASSOU" : "❌ FALHOU") + "\n");
		todosPassaram &= qa03Passou;

		// -------------------------------------------------------------
		// QA-04: ROTAÇÃO SIMPLES À DIREITA
		// -------------------------------------------------------------
		System.out.println("[TESTE QA-04] ROTAÇÃO SIMPLES À DIREITA (Caso Esquerda-Esquerda)");
		ArvoreAVL avlRotacao = new ArvoreAVL();
		// Inserindo na ordem: Raiz 3, depois 2 à esquerda, depois 1 à esquerda de 2
		avlRotacao.inserir(3, "Item 3");
		avlRotacao.inserir(2, "Item 2");
		avlRotacao.inserir(1, "Item 1");

		No raizApos = avlRotacao.getRaiz();
		boolean qa04Passou = (raizApos != null)
				&& (raizApos.id == 2)
				&& (raizApos.esquerda != null && raizApos.esquerda.id == 1)
				&& (raizApos.direita != null && raizApos.direita.id == 3)
				&& (raizApos.altura == 2)
				&& (avlRotacao.fatorBalanceamento(raizApos) == 0);

		System.out.println("   Nova Raiz após balanceamento: ID " + (raizApos != null ? raizApos.id : "null") + " (Esperado: 2)");
		System.out.println("   Filho Esquerdo: ID " + (raizApos != null && raizApos.esquerda != null ? raizApos.esquerda.id : "null") + " (Esperado: 1)");
		System.out.println("   Filho Direito: ID " + (raizApos != null && raizApos.direita != null ? raizApos.direita.id : "null") + " (Esperado: 3)");
		System.out.println("   Fator de Balanceamento da Raiz: " + avlRotacao.fatorBalanceamento(raizApos) + " (Esperado: 0)");
		System.out.println("   Status QA-04: " + (qa04Passou ? "✅ PASSOU" : "❌ FALHOU") + "\n");
		todosPassaram &= qa04Passou;

		System.out.println("==================================================================");
		if (todosPassaram) {
			System.out.println("RESULTADO GERAL: TODOS OS 4 CENÁRIOS DO CADERNO PASSARAM! (100%)");
		} else {
			System.out.println("RESULTADO GERAL: HOUVE FALHA EM CENÁRIO DE TESTE.");
		}
		System.out.println("==================================================================");
	}
}
