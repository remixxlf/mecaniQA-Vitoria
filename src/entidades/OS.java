package entidades;

import java.util.ArrayList;
import java.util.List;

public class OS {
	int numeroOS;
	Clientes cliente;
	Carros carro;

	List<Servico> servicos;
	List<Pecas> pecas;

	public OS(int numeroOS, Clientes cliente, Carros carro, List<Servico> servicos, List<Pecas> pecas) {
		super();
		this.numeroOS = numeroOS;
		this.cliente = cliente;
		this.carro = carro;
		this.servicos = new ArrayList<>();
		this.pecas = new ArrayList<>();
	}

	public int getNumeroOS() {
		return numeroOS;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public Carros getCarro() {
		return carro;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public List<Pecas> getPecas() {
		return pecas;
	}

	public void setNumeroOS(int numeroOS) {
		this.numeroOS = numeroOS;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public void setCarro(Carros carro) {
		this.carro = carro;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public void setPecas(List<Pecas> pecas) {
		this.pecas = pecas;
	}

	public void addServiço(Servico servico) {
		servicos.add(servico);
	}

	public void addPecas(Pecas peca) {
		pecas.add(peca);
	}

	public double calcularValorTotal() {
		double total = 0.0;

		for (Servico s : this.servicos) {
			total += s.getValorMaoObra();
		}

		for (Pecas p : this.pecas) {
			total += p.getPrecoVenda();
		}
		return total;
	}
}
