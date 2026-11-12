package services;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeitorRotas {

	public static final String DELIMITADOR = ";";

	public static Grafo carregar(String caminho) throws IOException {
		Path arquivo = Paths.get(caminho);
		if (!Files.isRegularFile(arquivo)) {
			throw new IOException("Arquivo de rotas nao encontrado: " + caminho);
		}
		Grafo grafo = new Grafo();
		String[] linhas = lerTexto(arquivo).split("\\R");
		for (int i = 0; i < linhas.length; i++) {
			String linha = linhas[i].trim();
			if (linha.isEmpty()) {
				continue;
			}
			String motivo = processarLinha(grafo, linha);
			if (motivo != null) {
				System.out.println("Linha " + (i + 1) + " de " + caminho + " ignorada: " + motivo);
			}
		}
		return grafo;
	}

	private static String processarLinha(Grafo grafo, String linha) {
		String[] campos = linha.split(DELIMITADOR);
		if (campos.length < 3) {
			return "formato esperado Origem;Destino;Distancia_em_Km";
		}
		String origem = campos[0].trim();
		String destino = campos[1].trim();
		if (origem.isEmpty() || destino.isEmpty()) {
			return "origem ou destino vazio";
		}
		double distancia;
		try {
			distancia = Double.parseDouble(campos[2].trim().replace(',', '.'));
		} catch (NumberFormatException e) {
			return "distancia nao numerica";
		}
		if (distancia < 0 || Double.isNaN(distancia) || Double.isInfinite(distancia)) {
			return "distancia invalida";
		}
		grafo.adicionarAresta(origem, destino, distancia);
		return null;
	}

	private static String lerTexto(Path arquivo) throws IOException {
		byte[] bytes = Files.readAllBytes(arquivo);
		String texto;
		try {
			texto = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT)
					.onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(bytes)).toString();
		} catch (CharacterCodingException e) {
			texto = new String(bytes, StandardCharsets.ISO_8859_1);
		}
		if (!texto.isEmpty() && texto.charAt(0) == '\uFEFF') {
			texto = texto.substring(1);
		}
		return texto;
	}
}
