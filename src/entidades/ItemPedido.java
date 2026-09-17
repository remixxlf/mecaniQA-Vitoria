package entidades;

public class ItemPedido {
    Pecas peca;
    int quantidade;
    public ItemPedido(Pecas peca, int quantidade) {
        this.peca = peca;
        this.quantidade = quantidade;
    }
    public Pecas getPeca() { return peca; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double calcularSubtotal() {
        return peca.getPrecoVenda() * quantidade;
    }
    public String toCsv() {
        return peca.getCodNumerico() + ":" + quantidade;
    }
}