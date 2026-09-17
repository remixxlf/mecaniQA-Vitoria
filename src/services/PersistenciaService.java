package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entidades.Clientes;
import entidades.OS;
import entidades.Pecas;
import entidades.Pedido;
import entidades.Servico;

public class PersistenciaService {

    private static void escreverArquivo(String caminho, List<String> linhas) {
        try (FileWriter fw = new FileWriter(caminho)) {
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
                if (!linha.isBlank()) linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Arquivo " + caminho + " ainda nao existe.");
        }
        return linhas;
    }
    public static void exportarPecas(List<Pecas> pecas, String caminho) {
        List<String> linhas = new ArrayList<>();
        for (Pecas p : pecas) linhas.add(p.toCsv());
        escreverArquivo(caminho, linhas);
    }
    public static List<Pecas> importarPecas(String caminho) {
        List<Pecas> pecas = new ArrayList<>();
        for (String linha : lerArquivo(caminho)) pecas.add(Pecas.fromCsv(linha));
        return pecas;
    }
    public static void exportarServicos(List<Servico> servicos, String caminho) {
        List<String> linhas = new ArrayList<>();
        for (Servico s : servicos) linhas.add(s.toCsv());
        escreverArquivo(caminho, linhas);
    }
    public static List<Servico> importarServicos(String caminho) {
        List<Servico> servicos = new ArrayList<>();
        for (String linha : lerArquivo(caminho)) servicos.add(Servico.fromCsv(linha));
        return servicos;
    }
    public static void exportarClientes(List<Clientes> clientes, String caminho) {
        List<String> linhas = new ArrayList<>();
        for (Clientes c : clientes) linhas.add(c.toCsv());
        escreverArquivo(caminho, linhas);
    }
    public static void exportarOS(List<OS> ordens, String caminho) {
        List<String> linhas = new ArrayList<>();
        for (OS os : ordens) linhas.add(os.toCsv());
        escreverArquivo(caminho, linhas);
    }
    public static void exportarPedidos(List<Pedido> pedidos, String caminho) {
        List<String> linhas = new ArrayList<>();
        for (Pedido p : pedidos) linhas.add(p.toCsv());
        escreverArquivo(caminho, linhas);
    }
}