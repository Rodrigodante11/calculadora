package modelo;

import java.util.ArrayList;
import java.util.List;

public class Memoria {
	
	private enum TipoComando  {
		ZERAR , NUMERO , DIV ,MULT ,SUB , SOMA ,IGUAL , VIRGULA
	};
	
	private static final   Memoria instancia = new Memoria();
	
	private TipoComando ultimaOperacao = null;
	private boolean subtituir = false;
	private String textoAtual = "";
	private String textoBuffer = "";
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
	
	public void processarComando(String texto) {
		

		TipoComando tipoComando = detectarTipoComando(texto);
		
		if(tipoComando ==null) {
			return;
		}else if(tipoComando == TipoComando.ZERAR) {
			textoAtual ="";
			textoBuffer = "";
			subtituir = false;
			ultimaOperacao = null;
		}else if(tipoComando == TipoComando.NUMERO || tipoComando ==TipoComando.VIRGULA) {
			textoAtual =subtituir ? texto : textoAtual+texto;
			subtituir = false;
		}
		
		observadores.forEach(o -> o.valorAlternado(textoAtual));
	}

	private TipoComando detectarTipoComando(String texto) {
		
		if(textoAtual.isEmpty() && texto =="0") {
			return null;
		}
		try {
			Integer.parseInt(texto); //tranforemado em numero
			return TipoComando.NUMERO;
			
		} catch (NumberFormatException e) {
			
			//Quando não for numero
			if("AC".equals(texto)) {
				return TipoComando.ZERAR;
				
			}else if("/".equals(texto)){
				return TipoComando.DIV;
				
			}else if("*".equals(texto)){
				return TipoComando.MULT;
				
			}else if("-".equals(texto)){
				return TipoComando.SUB;
				
			}else if(",".equals(texto) && !textoAtual.contains(",")){
				return TipoComando.VIRGULA;
				
			}else if("+".equals(texto)){
				return TipoComando.SOMA;
				
			}else if("=".equals(texto)){
				return TipoComando.IGUAL;
			}
		}
		return null;
		
	}
}
