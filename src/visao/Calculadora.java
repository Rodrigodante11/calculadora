package visao;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Calculadora extends JFrame{

	
	public Calculadora() {
		
		setSize(232,322);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//PARA PARA O PROGRAMA AO FECHAR A JANELA
		setLocationRelativeTo(null); //para centralizar a tela
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Calculadora();
	}
}
