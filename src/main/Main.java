package main;

import entidades.Pecas;
import entidades.Servico;
import services.Gerenciador;

public class Main {
	public static void main(String[] args) {
		// Testando as operações do CRUD unificado

		System.out.println("--- Teste de Peças ---");
		Gerenciador.createPeca(1, "Carburador", "Bosch", 100, 45.50, 20.00);
		Gerenciador.createPeca(2, "Vela de Ignição", "NGK", 4, 30.00, 12.50);
		
		Gerenciador.readPecas();
		
		System.out.println("\nAtualizando peça 1...");
		Gerenciador.updatePeca(1, "Filtro de Óleo Premium", "Bosch", 15, 60.00, 25.00);
		
		System.out.println("\nBuscando peça 1:");
		Gerenciador.buscaLinearPeca(1);
		
		System.out.println("\nDeletando peça 2...");
		Gerenciador.deletePeca(2);
		Gerenciador.readPecas();


		System.out.println("\n--- Teste de Serviços ---");
		Gerenciador.createServico(101, "Troca de Óleo", 30, 50.00);
		Gerenciador.createServico(102, "Alinhamento", 60, 100.00);
		
		Gerenciador.readServicos();
		
		System.out.println("\nAtualizando serviço 101...");
		Gerenciador.updateServico(101, "Troca de Óleo e Filtro", 45, 80.00);
		
		System.out.println("\nBuscando serviço 101:");
		Gerenciador.buscaLinearServico(101);
		
		System.out.println("\nDeletando serviço 102...");
		Gerenciador.deleteServico(102);
		Gerenciador.readServicos();
	}
}
