package services;

import entidades.Servico;

public class FilaAtendimento {

	private class No {
		Servico servico;
		No proximo;

		No(Servico servico) {
			this.servico = servico;
			this.proximo = null;
		}
	}

	private No inicio;
	private No fim;
	private int tamanho;

	public FilaAtendimento() {
		this.inicio = null;
		this.fim = null;
		this.tamanho = 0;
	}

	public void enfileirar(Servico servico) {
		if (servico == null) {
			System.out.println("Nao e possivel enfileirar um servico nulo.");
			return;
		}
		No novo = new No(servico);
		if (estaVazia()) {
			this.inicio = novo;
		} else {
			this.fim.proximo = novo;
		}
		this.fim = novo;
		this.tamanho++;
	}

	public Servico desenfileirar() {
		if (estaVazia()) {
			System.out.println("Nao ha nenhum servico na fila de atendimento.");
			return null;
		}
		Servico servico = this.inicio.servico;
		this.inicio = this.inicio.proximo;
		if (this.inicio == null) {
			this.fim = null;
		}
		this.tamanho--;
		return servico;
	}

	public Servico espiarProximo() {
		if (estaVazia()) {
			return null;
		}
		return this.inicio.servico;
	}

	public boolean estaVazia() {
		return this.inicio == null;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void verListaServicos() {
		System.out.println("---- Fila de Atendimento (" + tamanho + " servico(s)) ----");
		if (estaVazia()) {
			System.out.println("Fila vazia.");
			return;
		}
		int posicao = 1;
		No atual = this.inicio;
		while (atual != null) {
			System.out.println(posicao + "o) " + atual.servico.getCodNumerico() + " - "
					+ atual.servico.getDescricaoServico());
			atual = atual.proximo;
			posicao++;
		}
	}
}
