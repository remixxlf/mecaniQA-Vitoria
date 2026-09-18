package entidades;

public class Carros {
	String modelo, placa, ano;
	EstiloCarro estilo;

	public Carros(String modelo, String placa, String ano, EstiloCarro estilo) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
		this.estilo = estilo;
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

	public String toCsv(int codigoDono) {
		return placa + ";" + modelo + ";" + ano + ";" + estilo.name() + ";" + codigoDono;
	}

	public String toCsv() {
		return placa + ";" + modelo + ";" + ano + ";" + estilo.name();
	}

	public static Carros fromCsv(String linha) {
		String[] c = linha.split(";");
		return new Carros(c[1], c[0], c[2], EstiloCarro.valueOf(c[3]));
	}

	public static int donoFromCsv(String linha) {
		String[] c = linha.split(";");
		if (c.length < 5) {
			return -1;
		}
		return Integer.parseInt(c[4]);
	}

	@Override
	public String toString() {
		return modelo + " (" + placa + ", " + ano + ", " + estilo + ")";
	}
}
