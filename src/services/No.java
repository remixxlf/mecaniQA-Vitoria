package services;


public class No {

	public int id;
	public Object registro;
	public int altura;
	public No esquerda;
	public No direita;

	public No(int id, Object registro) {
		this.id = id;
		this.registro = registro;
		this.altura = 1;
		this.esquerda = null;
		this.direita = null;
	}

	@Override
	public String toString() {
		return "No{id=" + id + ", altura=" + altura + ", registro=" + registro + "}";
	}
}
