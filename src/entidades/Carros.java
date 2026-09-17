package entidades;

public class Carros {
	String modelo, placa, ano;
	EstiloCarro estilo;

	public Carros(String modelo, String placa, String ano) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
		this.estilo= estilo;
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

	public EstiloCarro getEstilo() {
		return estilo;
	}

	public void setEstilo(EstiloCarro estilo) {
		this.estilo = estilo;
	}

	public String toCSV() {
		return placa";"+modelo+";"+ano+";"+estilo.name();
	}
	public static Carros fromCSV(String linha) {
		String[] c=linha.split(";");
		return new Carros(c[0],c[1],c[2], EstiloCarro.valueOf(c[3]));
	}

}
