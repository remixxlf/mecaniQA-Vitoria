package entidades;

public class Pecas {

	int codNumerico;
	String nomePeca;
	String nomeFabricante;
	int quantidadePeca;
	double precoVenda;
	double precoCusto;

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
