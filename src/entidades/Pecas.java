package entidades;

public class Pecas implements ItemCatalogo {


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

	@Override
	public String getNomeParaOrdenação() {
		return codNumerico;
	}
	public String toCSV() {
		return codNumerico+ ";"+ nomePeca+";"+nomeFabricante+";"+quantidadePeca+";"+precoVenda+";"+precoCusto;
	}
	public static Peccas fromCsv(String Linha){
		String[] linha = Linha.split(";");
		return new Pecas(Integer.parseInt(c[0]),c[1],c[2], Integer.parseInt(c[3])),
		Double.ParseDouble(c[4]), Double.parseDouble(c[5]));
	}


}
