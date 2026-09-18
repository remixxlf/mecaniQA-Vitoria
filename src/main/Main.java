package main;

import entidades.Carros;
import entidades.ChaveOrdenacao;
import entidades.Clientes;
import entidades.EstiloCarro;
import entidades.OS;
import entidades.Pedido;
import entidades.Servico;
import entidades.StatusOs;
import services.Gerenciador;

public class Main {
	public static void main(String[] args) {

		System.out.println("=== 1. CADASTRO DE PECAS E SERVICOS ===");
		Gerenciador.createPeca(1, "Filtro de Oleo", "Bosch", 100, 45.50, 20.00);
		Gerenciador.createPeca(2, "Vela de Ignicao", "NGK", 40, 30.00, 12.50);
		Gerenciador.createPeca(3, "Amortecedor Dianteiro", "Monroe", 12, 320.00, 210.00);
		Gerenciador.readPecas();

		Gerenciador.createServico(101, "Troca de Oleo", 30, 50.00);
		Gerenciador.createServico(102, "Alinhamento", 60, 100.00);
		Gerenciador.createServico(103, "Balanceamento", 45, 80.00);
		Gerenciador.readServicos();

		System.out.println("\n=== 2. CLIENTES E CARROS (ENUM DE ESTILO) ===");
		Clientes cliente = Gerenciador.createCliente(1, "Brendha Seixas", "(75) 99999-0001", "brendha@mecaniqa.com");
		Gerenciador.createCliente(2, "Matheus Pereira", "(75) 99999-0002", "matheus@mecaniqa.com");
		Carros carro = Gerenciador.createCarro(1, "Onix", "ABC1D23", "2021", EstiloCarro.HATCH);
		Gerenciador.createCarro(1, "Corolla", "XYZ4E56", "2019", EstiloCarro.SEDAN);
		Gerenciador.createCarro(2, "Compass", "JKL7F89", "2023", EstiloCarro.SUV);
		Gerenciador.readClientes();

		System.out.println("\n=== 3. ORDEM DE SERVICO E CICLO DE VIDA (ENUM DE STATUS) ===");
		OS os = Gerenciador.abrirOS(cliente, carro);
		os.addServico(Gerenciador.buscarServico(101));
		os.addServico(Gerenciador.buscarServico(102));
		os.addServico(Gerenciador.buscarServico(103));
		os.tabelaServicos();
		System.out.println("Quantidade de servicos: " + os.quantidadeServicos());
		System.out.printf("Valor total da OS: R$ %.2f%n", os.valorTotal());

		System.out.println("\n=== 4. FILA DE ATENDIMENTO (FIFO) ===");
		Gerenciador.mudarStatusOs(os, StatusOs.AGUARDANDO_EXECUCAO);
		Gerenciador.getFilaAtendimento().verListaServicos();

		System.out.println("\nTentando adicionar servico apos o fechamento da OS:");
		os.addServico(Gerenciador.buscarServico(101));

		Gerenciador.mudarStatusOs(os, StatusOs.EM_EXECUCAO);
		Servico primeiro = Gerenciador.atenderProximoServico();
		Servico segundo = Gerenciador.atenderProximoServico();
		System.out.println("Ordem de atendimento respeitada (FIFO): " + primeiro.getDescricaoServico() + " -> "
				+ segundo.getDescricaoServico());
		Gerenciador.getFilaAtendimento().verListaServicos();
		Gerenciador.atenderProximoServico();
		Gerenciador.mudarStatusOs(os, StatusOs.FINALIZADA);

		System.out.println("\n=== 5. PEDIDO DE PECAS (VENDA DE BALCAO) ===");
		Pedido pedido = Gerenciador.abrirPedido();
		pedido.adicionarItem(Gerenciador.buscarPeca(1), 2);
		pedido.adicionarItem(Gerenciador.buscarPeca(2), 4);
		pedido.imprimirRelatorio();
		System.out.printf("Total com 10%% de desconto: R$ %.2f%n", pedido.calcularTotalComDesconto(10));
		pedido.finalizarPedido();
		System.out.println("Tentando adicionar peca apos finalizar o pedido:");
		pedido.adicionarItem(Gerenciador.buscarPeca(3), 1);

		System.out.println("\n=== 6. MOTOR DE ORDENACAO (BUBBLE SORT + ENUM DE CHAVE) ===");
		Gerenciador.relatorioCatalogoOrdenado(ChaveOrdenacao.NOME);
		Gerenciador.relatorioCatalogoOrdenado(ChaveOrdenacao.CODIGO_IDENTIFICADOR);

		System.out.println("\n=== 7. PERSISTENCIA EM ARQUIVOS CSV ===");
		Gerenciador.salvarTudo();
		Gerenciador.carregarTudo();
		Gerenciador.readPecas();
		Gerenciador.readClientes();
		for (OS osLida : Gerenciador.getOrdensServico()) {
			osLida.tabelaServicos();
		}
		for (Pedido pedidoLido : Gerenciador.getPedidos()) {
			pedidoLido.imprimirRelatorio();
		}
	}
}
