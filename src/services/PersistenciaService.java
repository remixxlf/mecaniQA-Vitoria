package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entidades.Carros;
import entidades.Clientes;
import entidades.OS;
import entidades.Pecas;
import entidades.Pedido;
import entidades.Servico;

public class PersistenciaService {

	public static final String DELIMITADOR = ";";

	private static void escreverArquivo(String caminho, List<String> linhas) {
		File arquivo = new File(caminho);
		if (arquivo.getParentFile() != null) {
			arquivo.getParentFile().mkdirs();
		}
		try (FileWriter fw = new FileWriter(arquivo)) {
			for (String linha : linhas) {
				fw.write(linha + System.lineSeparator());
			}
			System.out.println("Arquivo " + caminho + " salvo (" + linhas.size() + " linha(s)).");
		} catch (IOException e) {
			System.out.println("Erro ao gravar " + caminho + ": " + e.getMessage());
		}
	}

	private static List<String> lerArquivo(String caminho) {
		List<String> linhas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				if (!linha.isBlank()) {
					linhas.add(linha);
				}
			}
		} catch (IOException e) {
			System.out.println("Arquivo " + caminho + " ainda nao existe.");
		}
		return linhas;
	}

	public static void exportarPecas(List<Pecas> pecas, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (Pecas p : pecas) {
			linhas.add(p.toCsv());
		}
		escreverArquivo(caminho, linhas);
	}

	public static List<Pecas> importarPecas(String caminho) {
		List<Pecas> pecas = new ArrayList<>();
		for (String linha : lerArquivo(caminho)) {
			pecas.add(Pecas.fromCsv(linha));
		}
		return pecas;
	}

	public static void exportarServicos(List<Servico> servicos, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (Servico s : servicos) {
			linhas.add(s.toCsv());
		}
		escreverArquivo(caminho, linhas);
	}

	public static List<Servico> importarServicos(String caminho) {
		List<Servico> servicos = new ArrayList<>();
		for (String linha : lerArquivo(caminho)) {
			servicos.add(Servico.fromCsv(linha));
		}
		return servicos;
	}

	public static void exportarClientes(List<Clientes> clientes, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (Clientes c : clientes) {
			linhas.add(c.toCsv());
		}
		escreverArquivo(caminho, linhas);
	}

	public static List<Clientes> importarClientes(String caminho) {
		List<Clientes> clientes = new ArrayList<>();
		for (String linha : lerArquivo(caminho)) {
			clientes.add(Clientes.fromCsv(linha));
		}
		return clientes;
	}

	public static void exportarCarros(List<Clientes> clientes, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (Clientes c : clientes) {
			for (Carros carro : c.getCarros()) {
				linhas.add(carro.toCsv(c.getCodIdent()));
			}
		}
		escreverArquivo(caminho, linhas);
	}

	public static void importarCarros(String caminho, List<Clientes> clientes) {
		for (String linha : lerArquivo(caminho)) {
			Carros carro = Carros.fromCsv(linha);
			int codigoDono = Carros.donoFromCsv(linha);
			for (Clientes c : clientes) {
				if (c.getCodIdent() == codigoDono) {
					c.adicionarCarro(carro);
				}
			}
		}
	}

	public static void exportarOS(List<OS> ordens, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (OS os : ordens) {
			linhas.add(os.toCsv());
		}
		escreverArquivo(caminho, linhas);
	}

	public static List<OS> importarOS(String caminho, List<Clientes> clientes, List<Servico> servicos) {
		List<OS> ordens = new ArrayList<>();
		for (String linha : lerArquivo(caminho)) {
			ordens.add(OS.fromCsv(linha, clientes, servicos));
		}
		return ordens;
	}

	public static void exportarPedidos(List<Pedido> pedidos, String caminho) {
		List<String> linhas = new ArrayList<>();
		for (Pedido p : pedidos) {
			linhas.add(p.toCsv());
		}
		escreverArquivo(caminho, linhas);
	}

	public static List<Pedido> importarPedidos(String caminho, List<Pecas> pecas) {
		List<Pedido> pedidos = new ArrayList<>();
		for (String linha : lerArquivo(caminho)) {
			pedidos.add(Pedido.fromCsv(linha, pecas));
		}
		return pedidos;
	}
}
