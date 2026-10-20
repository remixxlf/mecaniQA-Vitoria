package services;

import java.util.ArrayList;
import java.util.List;


public class ArvoreAVL {

	private No raiz;
	private int quantidade;

	public ArvoreAVL() {
		this.raiz = null;
		this.quantidade = 0;
	}

	public No getRaiz() {
		return raiz;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public boolean estaVazia() {
		return raiz == null;
	}

	public void limpar() {
		this.raiz = null;
		this.quantidade = 0;
	}

	
	public int altura(No no) {
		return (no == null) ? 0 : no.altura;
	}

	
	public int getAltura() {
		return altura(this.raiz);
	}

	
	public int getFatorBalanceamento(No no) {
		if (no == null) {
			return 0;
		}
		return altura(no.esquerda) - altura(no.direita);
	}

	
	private No rotacaoDireita(No y) {
		No x = y.esquerda;
		No T2 = x.direita;

		
		x.direita = y;
		y.esquerda = T2;


		y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
		x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

		return x; 
	}

	
	private No rotacaoEsquerda(No x) {
		No y = x.direita;
		No T2 = y.esquerda;

		
		y.esquerda = x;
		x.direita = T2;

		
		x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
		y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

		return y; 
	}

	
	public void inserir(int id, Object registro) {
		this.raiz = inserirRecursivo(this.raiz, id, registro);
	}

	private No inserirRecursivo(No no, int id, Object registro) {
	
		if (no == null) {
			this.quantidade++;
			return new No(id, registro);
		}

		if (id < no.id) {
			no.esquerda = inserirRecursivo(no.esquerda, id, registro);
		} else if (id > no.id) {
			no.direita = inserirRecursivo(no.direita, id, registro);
		} else {
			
			no.registro = registro;
			return no;
		}

		
		no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

		
		int fb = getFatorBalanceamento(no);

		

		
		if (fb > 1 && id < no.esquerda.id) {
			return rotacaoDireita(no);
		}

		
		if (fb < -1 && id > no.direita.id) {
			return rotacaoEsquerda(no);
		}

	
		if (fb > 1 && id > no.esquerda.id) {
			no.esquerda = rotacaoEsquerda(no.esquerda);
			return rotacaoDireita(no);
		}

		
		if (fb < -1 && id < no.direita.id) {
			no.direita = rotacaoDireita(no.direita);
			return rotacaoEsquerda(no);
		}

	
		return no;
	}

	
	public Object buscar(int id) {
		No encontrado = buscarNo(id);
		return (encontrado != null) ? encontrado.registro : null;
	}

	public No buscarNo(int id) {
		return buscarNoRecursivo(this.raiz, id);
	}

	private No buscarNoRecursivo(No no, int id) {
		if (no == null || no.id == id) {
			return no;
		}
		if (id < no.id) {
			return buscarNoRecursivo(no.esquerda, id);
		}
		return buscarNoRecursivo(no.direita, id);
	}


	public List<Object> obterEmOrdem() {
		List<Object> lista = new ArrayList<>();
		percursoEmOrdem(this.raiz, lista);
		return lista;
	}

	private void percursoEmOrdem(No no, List<Object> lista) {
		if (no != null) {
			percursoEmOrdem(no.esquerda, lista);
			lista.add(no.registro);
			percursoEmOrdem(no.direita, lista);
		}
	}

	
	public void imprimirEmOrdem() {
		if (estaVazia()) {
			System.out.println("Árvore AVL vazia.");
			return;
		}
		imprimirEmOrdemRecursivo(this.raiz);
	}

	private void imprimirEmOrdemRecursivo(No no) {
		if (no != null) {
			imprimirEmOrdemRecursivo(no.esquerda);
			System.out.println("[ID: " + no.id + " | Altura: " + no.altura + " | FB: " + getFatorBalanceamento(no)
					+ " | Registro: " + no.registro + "]");
			imprimirEmOrdemRecursivo(no.direita);
		}
	}

	
	public void exibirEstrutura() {
		if (estaVazia()) {
			System.out.println("(Árvore vazia)");
			return;
		}
		exibirEstruturaRecursiva(this.raiz, "", true);
	}

	private void exibirEstruturaRecursiva(No no, String prefixo, boolean isUltimo) {
		if (no != null) {
			System.out.println(prefixo + (isUltimo ? "└── " : "├── ") + "ID " + no.id + " (h=" + no.altura
					+ ", fb=" + getFatorBalanceamento(no) + ")");
			String novoPrefixo = prefixo + (isUltimo ? "    " : "│   ");
			if (no.esquerda != null) {
				exibirEstruturaRecursiva(no.esquerda, novoPrefixo, no.direita == null);
			}
			if (no.direita != null) {
				exibirEstruturaRecursiva(no.direita, novoPrefixo, true);
			}
		}
	}
}
