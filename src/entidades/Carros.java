package entidades;

public class Carros {
	String modelo, placa, ano;
	EstiloCarro estilo;

	public Carros(String modelo, String placa, String ano) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

}
