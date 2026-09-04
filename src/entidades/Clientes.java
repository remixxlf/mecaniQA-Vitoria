package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Clientes {
	String nome, email, telefoneZapZap;
	int codIndent;
	List<Carros> carrosCliente;
	Random gerador = new Random();

	public Clientes(String nome, String email, String telefoneZapZap, int codIndent) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefoneZapZap = telefoneZapZap;
		this.codIndent = gerador.nextInt(1, 1001);
		this.carrosCliente = new ArrayList<>();

	}

	public void adicionarCarro(Carros carro) {
		this.carrosCliente.add(carro);
	}

	public List<Carros> getCarros() {
		return this.carrosCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefoneZapZap() {
		return telefoneZapZap;
	}

	public int getCodIndent() {
		return codIndent;
	}

	public List<Carros> getCarrosCliente() {
		return carrosCliente;
	}

	public Random getGerador() {
		return gerador;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefoneZapZap(String telefoneZapZap) {
		this.telefoneZapZap = telefoneZapZap;
	}

	public void setCodIndent(int codIndent) {
		this.codIndent = codIndent;
	}

	public void setCarrosCliente(List<Carros> carrosCliente) {
		this.carrosCliente = carrosCliente;
	}

	public void setGerador(Random gerador) {
		this.gerador = gerador;
	}
}
