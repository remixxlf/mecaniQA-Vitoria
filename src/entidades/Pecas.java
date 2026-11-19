package entidades;

// Pequena observação de nomenclatura: o nome da classe de entidade geralmente fica melhor no singular ("Peca").
public class Pecas {

	// Atenção: a especificação pedia que as classes de TAD fossem modeladas estritamente como "structs",
	// ou seja, com todos os atributos declarados como "public" (acessíveis externamente)
	// e SEM construtores nem métodos getters/setters.
	int codNumerico;
	String nomePeca;
	String nomeFabricante;
	int quantidadePeca;
	double precoVenda;
	double precoCusto;

	// Conforme os critérios do trabalho, não deveriam existir construtores nem getters/setters aqui.
	public Pecas(int codNumerico, String nomePeca, String nomeFabricante, int quantidadePeca, double precoVenda,
			double precoCusto) {
		super();
		this.codNumerico = codNumerico;
		this.nomePeca = nomePeca;
		this.nomeFabricante = nomeFabricante;
		this.quantidadePeca = quantidadePeca;
		this.precoVenda = precoVenda;
		this.precoCusto = precoCusto;
	}

	public int getCodNumerico() {
		return codNumerico;
	}

	public void setCodNumerico(int codNumerico) {
		this.codNumerico = codNumerico;
	}

	public String getNomePeca() {
		return nomePeca;
	}

	public void setNomePeca(String nomePeca) {
		this.nomePeca = nomePeca;
	}

	public String getNomeFabricante() {
		return nomeFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}

	public int getQuantidadePeca() {
		return quantidadePeca;
	}

	public void setQuantidadePeca(int quantidadePeca) {
		this.quantidadePeca = quantidadePeca;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

}
