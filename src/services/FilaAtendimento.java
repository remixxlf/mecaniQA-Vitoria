package services;

import java.util.ArrayList;
import java.util.List;

import entidades.Servico;

public class FilaAtendimento {

	private List<Servico> fila = new ArrayList<>();

	public void enfileirar(Servico x) {
		fila.add(x);
	}

	public boolean estaVazia() {
		return this.fila.isEmpty();
	}
	
	public Servico desenfileirar( ) {
		if(this.fila.isEmpty()) {
			System.out.println("Nao ha nenhum serviço");
			return null;
		}
		return this.fila.remove(0);
	}
	
	public void verListaServicos() {
		for( Servico t : fila) {
			System.out.println(t.getDescricaoServico());
		}
	}
}
