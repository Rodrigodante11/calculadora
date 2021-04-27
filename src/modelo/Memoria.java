package modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	private static final   Memoria instancia = new Memoria();
	
	private String textoAtual = "";
	private final List<MemoriaObservador> observadores = new ArrayList<>();
	
	private Memoria() {
		
	}

	public String getTextoAtual() {
		return textoAtual.isEmpty() ? "0" : textoAtual;
	}

	public static Memoria getInstancia() {
		return instancia;
	}
	
	public void adicionarObservador(MemoriaObservador observador) {
		observadores.add(observador);
	}
	
	public void processarComando(String valor) {
		if("AC".equals(valor)) {
			textoAtual =""; //para limpar o campo
		}else {
			textoAtual+=valor;
		}
		
		observadores.forEach(o -> o.valorAlternado(textoAtual));
	}
}
