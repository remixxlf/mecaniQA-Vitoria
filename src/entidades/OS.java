package entidades;

import java.util.ArrayList;
import java.util.List;

public class OS {
	private static int proximoCodigo = 1;

	int numeroOS;
	Clientes cliente;
	Carros carro;
	StatusOs status;
	List<Servico> servicos;

	public OS(Clientes cliente, Carros carro) {
		super();
		this.numeroOS = proximoCodigo++;
		this.cliente = cliente;
		this.carro = carro;
		this.servicos = new ArrayList<>();
		this.status = StatusOs.EM_ABERTO;
	}

	public OS(int numeroOS, Clientes cliente, Carros carro, StatusOs status) {
		super();
		this.numeroOS = numeroOS;
		this.cliente = cliente;
		this.carro = carro;
		this.status = status;
		this.servicos = new ArrayList<>();
		if (numeroOS >= proximoCodigo) {
			proximoCodigo = numeroOS + 1;
		}
	}

	public StatusOs getStatus() {
		return status;
	}

	public void setStatus(StatusOs status) {
		this.status = status;
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

	public boolean estaFechada() {
		return this.status != StatusOs.EM_ABERTO;
	}

	public boolean addServico(Servico servico) {
		if (estaFechada()) {
			System.out.println("Erro: OS #" + numeroOS + " ja foi fechada (" + status
					+ "). Nao e possivel adicionar servicos.");
			return false;
		}
		servicos.add(servico);
		return true;
	}

	public boolean removerServico(Servico servico) {
		if (estaFechada()) {
			System.out.println("Erro: OS #" + numeroOS + " ja foi fechada (" + status
					+ "). Nao e possivel remover servicos.");
			return false;
		}
		return servicos.remove(servico);
	}

	public int quantidadeServicos() {
		return servicos.size();
	}

	public double valorTotal() {
		double total = 0.0;
		for (Servico s : servicos) {
			total += s.getValorMaoObra();
		}
		return total;
	}

	public void tabelaServicos() {
		System.out.println("---- Servicos da OS #" + numeroOS + " (" + status + ") ----");
		if (servicos.isEmpty()) {
			System.out.println("Nenhum servico cadastrado nesta OS.");
			return;
		}
		System.out.printf("%-6s | %-30s | %-10s | %-10s%n", "Cod", "Descricao", "Tempo(min)", "Valor(R$)");
		for (Servico s : servicos) {
			System.out.printf("%-6d | %-30s | %-10d | %-10.2f%n", s.getCodNumerico(), s.getDescricaoServico(),
					s.getTempoEstimado(), s.getValorMaoObra());
		}
		System.out.printf("Total: R$ %.2f (%d servico(s))%n", valorTotal(), quantidadeServicos());
	}

	public String toCsv() {
		StringBuilder codsServicos = new StringBuilder();
		for (Servico s : servicos) {
			if (codsServicos.length() > 0) {
				codsServicos.append(",");
			}
			codsServicos.append(s.getCodNumerico());
		}
		return numeroOS + ";" + cliente.getCodIdent() + ";" + carro.getPlaca() + ";" + status.name() + ";"
				+ codsServicos;
	}

	public static OS fromCsv(String linha, List<Clientes> clientes, List<Servico> catalogoServicos) {
		String[] c = linha.split(";");
		int numero = Integer.parseInt(c[0]);
		int codCliente = Integer.parseInt(c[1]);
		String placa = c[2];
		StatusOs statusLido = StatusOs.valueOf(c[3]);

		Clientes dono = null;
		for (Clientes cliente : clientes) {
			if (cliente.getCodIdent() == codCliente) {
				dono = cliente;
			}
		}

		Carros veiculo = null;
		if (dono != null) {
			veiculo = dono.buscarCarro(placa);
		}

		OS os = new OS(numero, dono, veiculo, StatusOs.EM_ABERTO);
		if (c.length > 4 && !c[4].isBlank()) {
			String[] codigos = c[4].split(",");
			for (String codigo : codigos) {
				int cod = Integer.parseInt(codigo.trim());
				for (Servico s : catalogoServicos) {
					if (s.getCodNumerico() == cod) {
						os.addServico(s);
					}
				}
			}
		}
		os.setStatus(statusLido);
		return os;
	}
}
