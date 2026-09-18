package entidades;

import java.util.ArrayList;
import java.util.List;

public class Clientes {
	String nome, email, telefoneZapZap;
	int codIdent;
	List<Carros> carrosCliente;

	public Clientes(String nome, String email, String telefoneZapZap, int codIdent) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefoneZapZap = telefoneZapZap;
		this.codIdent = codIdent;
		this.carrosCliente = new ArrayList<>();
	}

	public void adicionarCarro(Carros carro) {
		this.carrosCliente.add(carro);
	}

	public boolean removerCarro(String placa) {
		for (int i = 0; i < carrosCliente.size(); i++) {
			if (carrosCliente.get(i).getPlaca().equalsIgnoreCase(placa)) {
				carrosCliente.remove(i);
				return true;
			}
		}
		return false;
	}

	public Carros buscarCarro(String placa) {
		for (Carros carro : carrosCliente) {
			if (carro.getPlaca().equalsIgnoreCase(placa)) {
				return carro;
			}
		}
		return null;
	}

	public List<Carros> getCarros() {
		return this.carrosCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneZapZap() {
		return telefoneZapZap;
	}

	public void setTelefoneZapZap(String telefoneZapZap) {
		this.telefoneZapZap = telefoneZapZap;
	}

	public int getCodIdent() {
		return codIdent;
	}

	public void setCodIdent(int codIdent) {
		this.codIdent = codIdent;
	}

	public List<Carros> getCarrosCliente() {
		return carrosCliente;
	}

	public void setCarrosCliente(List<Carros> carrosCliente) {
		this.carrosCliente = carrosCliente;
	}

	public String toCsv() {
		return codIdent + ";" + nome + ";" + email + ";" + telefoneZapZap;
	}

	public static Clientes fromCsv(String linha) {
		String[] c = linha.split(";");
		return new Clientes(c[1], c[2], c[3], Integer.parseInt(c[0]));
	}
}
