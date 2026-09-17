package entidades;

import java.util.ArrayList;
import java.util.List;

public class OS {
	int numeroOS;
	Clientes cliente;
	Carros carro;
	StatusOs status;
	List<Servico> servicos;
	List<Pecas> pecas;

	public OS(Clientes cliente, Carros carro) {
		super();
		this.numeroOS = proximoCodigo++;
		this.cliente = cliente;
		this.carro = carro;
		this.servicos = new ArrayList<>();
		this.status = StatusOs.EM_ABERTO;

		public StatusOs getStatus() { return status; }
		public void setStatus(StatusOs status) { this.status = status; }
		public int getNumeroOS() { return numeroOS; }
		public Clientes getCliente() { return cliente; }
		public Carros getCarro() { return carro; }
		public List<Servico> getServicos() { return servicos; }

		public boolean addServico(Servico servico) {
			if (this.status == StatusOs.FINALIZADA) {
				System.out.println("Erro: OS #" + numeroOS + " ja foi finalizada. Nao e possivel adicionar servicos.");
				return false;
			}
			servicos.add(servico);
			return true;
		}

		public boolean removerServico(Servico servico) {
			if (this.status == StatusOs.FINALIZADA) {
				System.out.println("Erro: OS #" + numeroOS + " ja foi finalizada. Nao e possivel remover servicos.");
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
				System.out.printf("%-6d | %-30s | %-10d | %-10.2f%n",
						s.getCodNumerico(), s.getDescricaoServico(), s.getTempoEstimado(), s.getValorMaoObra());
			}
			System.out.printf("Total: R$ %.2f (%d servico(s))%n", valorTotal(), quantidadeServicos());
		}

		public String toCsv() {
			StringBuilder codsServicos = new StringBuilder();
			for (Servico s : servicos) {
				if (codsServicos.length() > 0) codsServicos.append(",");
				codsServicos.append(s.getCodNumerico());
			}
			return numeroOS + ";" + cliente.getCodIndent() + ";" + carro.getPlaca() + ";" + status.name() + ";" + codsServicos;
		}
	}
