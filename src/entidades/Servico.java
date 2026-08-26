package entidades;

public class Servico {

	int codNumerico;
	String descricaoServico;
	int tempoEstimado;
	double valorMaoObra;

	public Servico(int codNumerico, String descricaoServico, int tempoEstimado, double valorMaoObra) {
		super();
		this.codNumerico = codNumerico;
		this.descricaoServico = descricaoServico;
		this.tempoEstimado = tempoEstimado;
		this.valorMaoObra = valorMaoObra;
	}

	public int getCodNumerico() {
		return codNumerico;
	}

	public void setCodNumerico(int codNumerico) {
		this.codNumerico = codNumerico;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public int getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(int tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public double getValorMaoObra() {
		return valorMaoObra;
	}

	public void setValorMaoObra(double valorMaoObra) {
		this.valorMaoObra = valorMaoObra;
	}

}
