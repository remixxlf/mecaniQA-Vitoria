package services;

import java.util.ArrayList;
import java.util.List;

import entidades.Carros;
import entidades.ChaveOrdenacao;
import entidades.Clientes;
import entidades.EstiloCarro;
import entidades.ItemCatalogo;
import entidades.OS;
import entidades.Pecas;
import entidades.Pedido;
import entidades.Servico;
import entidades.StatusOs;

public class Gerenciador {

	public static final String PASTA_DADOS = "dados";
	public static final String ARQUIVO_PECAS = PASTA_DADOS + "/pecas.csv";
	public static final String ARQUIVO_SERVICOS = PASTA_DADOS + "/servicos.csv";
	public static final String ARQUIVO_CLIENTES = PASTA_DADOS + "/clientes.csv";
	public static final String ARQUIVO_CARROS = PASTA_DADOS + "/carros.csv";
	public static final String ARQUIVO_OS = PASTA_DADOS + "/ordens_servico.csv";
	public static final String ARQUIVO_PEDIDOS = PASTA_DADOS + "/pedidos.csv";

	static List<Pecas> pecas = new ArrayList<>();
	static List<Servico> servicos = new ArrayList<>();
	static FilaAtendimento filaAtendimento = new FilaAtendimento();
	static List<Clientes> clientes = new ArrayList<>();
	static List<OS> ordensServico = new ArrayList<>();
	static List<Pedido> pedidos = new ArrayList<>();

	public static List<Pecas> getPecas() {
		return pecas;
	}

	public static List<Servico> getServicos() {
		return servicos;
	}

	public static List<Clientes> getClientes() {
		return clientes;
	}

	public static List<OS> getOrdensServico() {
		return ordensServico;
	}

	public static List<Pedido> getPedidos() {
		return pedidos;
	}

	public static FilaAtendimento getFilaAtendimento() {
		return filaAtendimento;
	}

	public static void createPeca(int codNumerico, String nomePeca, String nomeFabricante, int quantidadePeca,
			double precoVenda, double precoCusto) {
		if (nomePeca == null || nomePeca.isBlank()) {
			System.out.println("Erro ao criar Peça, nome vazio");
			return;
		}

		Pecas novaPeca = new Pecas(codNumerico, nomePeca, nomeFabricante, quantidadePeca, precoVenda, precoCusto);
		pecas.add(novaPeca);
		System.out.println("Peça criada com sucesso!");
	}

	public static void deletePeca(int codNumerico) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codNumerico) {
				pecas.remove(i);
				System.out.println("Peça removida com sucesso!");
				return;
			}
		}
		System.out.println("Peça com código " + codNumerico + " não encontrada.");
	}

	public static Pecas buscarPeca(int codNumerico) {
		for (Pecas p : pecas) {
			if (p.getCodNumerico() == codNumerico) {
				return p;
			}
		}
		return null;
	}

	public static void createServico(int codNumerico, String descricaoServico, int tempoEstimado, double valorMaoObra) {
		if (descricaoServico == null || descricaoServico.isBlank()) {
			System.out.println("Erro ao criar Serviço, descrição vazia");
			return;
		}

		Servico novaServico = new Servico(codNumerico, descricaoServico, tempoEstimado, valorMaoObra);
		servicos.add(novaServico);
		System.out.println("Serviço criado e salvo com sucesso!");
	}

	public static void deleteServico(int codNumerico) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codNumerico) {
				servicos.remove(i);
				System.out.println("Serviço removido com sucesso!");
				return;
			}
		}
		System.out.println("Serviço com código " + codNumerico + " não encontrado.");
	}

	public static Servico buscarServico(int codNumerico) {
		for (Servico s : servicos) {
			if (s.getCodNumerico() == codNumerico) {
				return s;
			}
		}
		return null;
	}

	public static void readPecas() {
		if (pecas.isEmpty()) {
			System.out.println("Nenhuma peça cadastrada.");
			return;
		}
		for (int i = 0; i < pecas.size(); i++) {
			System.out.println("Código: " + pecas.get(i).getCodNumerico() + " | Nome: " + pecas.get(i).getNomePeca()
					+ " | Fabricante: " + pecas.get(i).getNomeFabricante() + " | Quantidade: "
					+ pecas.get(i).getQuantidadePeca());
		}
	}

	public static void updatePeca(int codigoAlvo, String novoNomePeca, String novoNomeFabricante,
			int novaQuantidadePeca, double novoPrecoVenda, double novoPrecoCusto) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codigoAlvo) {
				pecas.get(i).setNomePeca(novoNomePeca);
				pecas.get(i).setNomeFabricante(novoNomeFabricante);
				pecas.get(i).setQuantidadePeca(novaQuantidadePeca);
				pecas.get(i).setPrecoVenda(novoPrecoVenda);
				pecas.get(i).setPrecoCusto(novoPrecoCusto);
				System.out.println("Peça atualizada com sucesso!");
				return;
			}
		}
		System.out.println("Peça com código " + codigoAlvo + " não encontrada para atualização.");
	}

	public static void buscaLinearPeca(int codigoBuscado) {
		for (int i = 0; i < pecas.size(); i++) {
			if (pecas.get(i).getCodNumerico() == codigoBuscado) {
				System.out.println("PecaIndex = " + i + "\n " + "Codigo da peca: " + pecas.get(i).getCodNumerico());
				return;
			}
		}
		System.out.println("Peça com código " + codigoBuscado + " não encontrada.");
	}

	public static void readServicos() {
		if (servicos.isEmpty()) {
			System.out.println("Nenhum serviço cadastrado.");
			return;
		}
		for (int i = 0; i < servicos.size(); i++) {
			System.out.println("Código: " + servicos.get(i).getCodNumerico() + " | Descrição: "
					+ servicos.get(i).getDescricaoServico() + " | Tempo Estimado: " + servicos.get(i).getTempoEstimado()
					+ " min" + " | Valor Mão de Obra: R$ " + servicos.get(i).getValorMaoObra());
		}
	}

	public static void updateServico(int codigoAlvo, String novaDescricao, int novoTempoEstimado,
			double novoValorMaoObra) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codigoAlvo) {
				servicos.get(i).setDescricaoServico(novaDescricao);
				servicos.get(i).setTempoEstimado(novoTempoEstimado);
				servicos.get(i).setValorMaoObra(novoValorMaoObra);
				System.out.println("Serviço atualizado com sucesso!");
				return;
			}
		}
		System.out.println("Serviço com código " + codigoAlvo + " não encontrado para atualização.");
	}

	public static void buscaLinearServico(int codigoBuscado) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).getCodNumerico() == codigoBuscado) {
				System.out.println(
						"ServicoIndex = " + i + "\n " + "Codigo do servico: " + servicos.get(i).getCodNumerico());
				return;
			}
		}
		System.out.println("Serviço com código " + codigoBuscado + " não encontrado.");
	}

	public static Clientes createCliente(int codIdent, String nome, String telefoneZapZap, String email) {
		if (nome == null || nome.isBlank()) {
			System.out.println("Erro ao criar Cliente, nome vazio");
			return null;
		}
		Clientes novoCliente = new Clientes(nome, email, telefoneZapZap, codIdent);
		clientes.add(novoCliente);
		System.out.println("Cliente " + nome + " cadastrado com sucesso!");
		return novoCliente;
	}

	public static Clientes buscarCliente(int codIdent) {
		for (Clientes c : clientes) {
			if (c.getCodIdent() == codIdent) {
				return c;
			}
		}
		return null;
	}

	public static void deleteCliente(int codIdent) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCodIdent() == codIdent) {
				clientes.remove(i);
				System.out.println("Cliente removido com sucesso!");
				return;
			}
		}
		System.out.println("Cliente com código " + codIdent + " não encontrado.");
	}

	public static Carros createCarro(int codCliente, String modelo, String placa, String ano, EstiloCarro estilo) {
		Clientes dono = buscarCliente(codCliente);
		if (dono == null) {
			System.out.println("Cliente com código " + codCliente + " não encontrado. Carro não cadastrado.");
			return null;
		}
		Carros novoCarro = new Carros(modelo, placa, ano, estilo);
		dono.adicionarCarro(novoCarro);
		System.out.println("Carro " + modelo + " (" + placa + ") vinculado ao cliente " + dono.getNome() + ".");
		return novoCarro;
	}

	public static void readClientes() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
			return;
		}
		for (Clientes c : clientes) {
			System.out.println("Código: " + c.getCodIdent() + " | Nome: " + c.getNome() + " | WhatsApp: "
					+ c.getTelefoneZapZap() + " | E-mail: " + c.getEmail());
			if (c.getCarros().isEmpty()) {
				System.out.println("   Nenhum carro associado.");
			} else {
				for (Carros carro : c.getCarros()) {
					System.out.println("   Carro: " + carro.getModelo() + " | Placa: " + carro.getPlaca() + " | Ano: "
							+ carro.getAno() + " | Estilo: " + carro.getEstilo());
				}
			}
		}
	}

	public static OS abrirOS(Clientes cliente, Carros carro) {
		OS novaOs = new OS(cliente, carro);
		ordensServico.add(novaOs);
		System.out.println("OS #" + novaOs.getNumeroOS() + " aberta com sucesso!");
		return novaOs;
	}

	public static OS buscarOS(int numeroOS) {
		for (OS os : ordensServico) {
			if (os.getNumeroOS() == numeroOS) {
				return os;
			}
		}
		return null;
	}

	public static void mudarStatusOs(OS os, StatusOs novoStatus) {
		if (os == null) {
			System.out.println("OS inexistente.");
			return;
		}
		os.setStatus(novoStatus);
		System.out.println("OS #" + os.getNumeroOS() + " agora esta " + novoStatus);
		if (novoStatus == StatusOs.AGUARDANDO_EXECUCAO) {
			despacharOs(os);
		}
	}

	public static void despacharOs(OS os) {
		if (os.getStatus() == StatusOs.AGUARDANDO_EXECUCAO) {
			for (Servico s : os.getServicos()) {
				filaAtendimento.enfileirar(s);
			}
			System.out.println("Servicos da OS #" + os.getNumeroOS() + " despachados para a fila com sucesso!");
		} else {
			System.out.println("A OS #" + os.getNumeroOS() + " ainda nao esta aguardando execucao. Servicos retidos.");
		}
	}

	public static Servico atenderProximoServico() {
		Servico atendido = filaAtendimento.desenfileirar();
		if (atendido != null) {
			System.out.println("Servico em execucao: " + atendido.getDescricaoServico());
		}
		return atendido;
	}

	public static Pedido abrirPedido() {
		Pedido novoPedido = new Pedido();
		pedidos.add(novoPedido);
		System.out.println("Pedido #" + novoPedido.getCodigoPedido() + " aberto com sucesso!");
		return novoPedido;
	}

	public static Pedido buscarPedido(int codigoPedido) {
		for (Pedido p : pedidos) {
			if (p.getCodigoPedido() == codigoPedido) {
				return p;
			}
		}
		return null;
	}

	public static List<ItemCatalogo> montarCatalogo() {
		List<ItemCatalogo> catalogo = new ArrayList<>();
		catalogo.addAll(pecas);
		catalogo.addAll(servicos);
		return catalogo;
	}

	public static void relatorioCatalogoOrdenado(ChaveOrdenacao chave) {
		OrdenacaoService.imprimirRelatorio(montarCatalogo(), chave);
	}

	public static void salvarTudo() {
		PersistenciaService.exportarPecas(pecas, ARQUIVO_PECAS);
		PersistenciaService.exportarServicos(servicos, ARQUIVO_SERVICOS);
		PersistenciaService.exportarClientes(clientes, ARQUIVO_CLIENTES);
		PersistenciaService.exportarCarros(clientes, ARQUIVO_CARROS);
		PersistenciaService.exportarOS(ordensServico, ARQUIVO_OS);
		PersistenciaService.exportarPedidos(pedidos, ARQUIVO_PEDIDOS);
		System.out.println("Todos os dados foram salvos em arquivos .csv na pasta " + PASTA_DADOS + ".");
	}

	public static void carregarTudo() {
		pecas = PersistenciaService.importarPecas(ARQUIVO_PECAS);
		servicos = PersistenciaService.importarServicos(ARQUIVO_SERVICOS);
		clientes = PersistenciaService.importarClientes(ARQUIVO_CLIENTES);
		PersistenciaService.importarCarros(ARQUIVO_CARROS, clientes);
		ordensServico = PersistenciaService.importarOS(ARQUIVO_OS, clientes, servicos);
		pedidos = PersistenciaService.importarPedidos(ARQUIVO_PEDIDOS, pecas);
		System.out.println("Dados recuperados: " + pecas.size() + " peca(s), " + servicos.size() + " servico(s), "
				+ clientes.size() + " cliente(s), " + ordensServico.size() + " OS e " + pedidos.size() + " pedido(s).");
	}
}
