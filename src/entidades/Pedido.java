package entidades;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int proximoCodigo = 1;

    int codigoPedido;
    StatusPedido status;
    List<ItemPedido> itens;

    public Pedido() {
        this.codigoPedido = proximoCodigo++;
        this.status = StatusPedido.ABERTO;
        this.itens = new ArrayList<>();
    }

    public int getCodigoPedido() { return codigoPedido; }
    public StatusPedido getStatus() { return status; }
    public List<ItemPedido> getItens() { return itens; }

    public boolean adicionarItem(Pecas peca, int quantidade) {
        if (this.status == StatusPedido.FINALIZADO) {
            System.out.println("Erro: pedido #" + codigoPedido + " ja finalizado. Nao e possivel adicionar pecas.");
            return false;
        }
        itens.add(new ItemPedido(peca, quantidade));
        return true;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public double calcularTotalComDesconto(double percentualDesconto) {
        double total = calcularTotal();
        return total - (total * (percentualDesconto / 100.0));
    }

    public void imprimirRelatorio() {
        System.out.println("---- Pedido #" + codigoPedido + " (" + status + ") ----");
        if (itens.isEmpty()) {
            System.out.println("Nenhuma peca neste pedido.");
            return;
        }
        System.out.printf("%-6s | %-25s | %-4s | %-10s%n", "Cod", "Peca", "Qtd", "Subtotal(R$)");
        for (ItemPedido item : itens) {
            System.out.printf("%-6d | %-25s | %-4d | %-10.2f%n",
                    item.getPeca().getCodNumerico(), item.getPeca().getNomePeca(),
                    item.getQuantidade(), item.calcularSubtotal());
        }
        System.out.printf("Total do pedido: R$ %.2f%n", calcularTotal());
    }

    public void finalizarPedido() {
        this.status = StatusPedido.FINALIZADO;
        System.out.println("Pedido #" + codigoPedido + " finalizado com sucesso!");
    }

    public String toCsv() {
        StringBuilder itensStr = new StringBuilder();
        for (ItemPedido item : itens) {
            if (itensStr.length() > 0) itensStr.append(",");
            itensStr.append(item.toCsv());
        }
        return codigoPedido + ";" + status.name() + ";" + itensStr;
    }
}