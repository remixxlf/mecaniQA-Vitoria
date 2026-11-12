package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotaEntrega {

	private final boolean alcancavel;
	private final double distanciaTotal;
	private final List<String> caminho;
	private final List<Double> trechos;

	public RotaEntrega(boolean alcancavel, double distanciaTotal, List<String> caminho, List<Double> trechos) {
		this.alcancavel = alcancavel;
		this.distanciaTotal = distanciaTotal;
		this.caminho = new ArrayList<>(caminho);
		this.trechos = new ArrayList<>(trechos);
	}

	public boolean isAlcancavel() {
		return alcancavel;
	}

	public double getDistanciaTotal() {
		return distanciaTotal;
	}

	public List<String> getCaminho() {
		return Collections.unmodifiableList(caminho);
	}

	public List<Double> getTrechos() {
		return Collections.unmodifiableList(trechos);
	}

	public String descricaoCaminho() {
		return String.join(" -> ", caminho);
	}
}
