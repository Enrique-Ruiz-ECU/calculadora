

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculadora {

	public static void main(String[] args) {
		MarcoCalculadora mimarco = new MarcoCalculadora();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
	}

}

class MarcoCalculadora extends JFrame{
	//constructor 
	public MarcoCalculadora() {
		setTitle("Calculadora");
		setBounds(500, 300, 300, 350);
		LaminaCalculadora milamina = new LaminaCalculadora();//instancia LaminaCalculadora
		add(milamina);
		//pack();//el tamaño que tiene que tener el contenedor se adapta al tamaño de los componentes que hay en su interior
	}
}

class LaminaCalculadora extends JPanel{
	//method constructor 
	public LaminaCalculadora() {
		principio=true;
		ultimaOperacion="=";
		setLayout(new BorderLayout());
		pantalla = new JButton("0");
		pantalla.setEnabled(false);//disable the button.
		add(pantalla,BorderLayout.NORTH);
		
		milamina2=new JPanel();
		milamina2.setLayout(new GridLayout(4,4));//4 rows and 4 columns

		ponerBoton("7",new InsertaNumero());// they are placed by columns
		ponerBoton("8",new InsertaNumero());
		ponerBoton("9",new InsertaNumero());
		ponerBoton("/", new AccionOrden());
		ponerBoton("4",new InsertaNumero());
		ponerBoton("5",new InsertaNumero());
		ponerBoton("6",new InsertaNumero());
		ponerBoton("*",new AccionOrden());
		ponerBoton("1",new InsertaNumero());
		ponerBoton("2",new InsertaNumero());
		ponerBoton("3",new InsertaNumero());
		ponerBoton("-",new AccionOrden());
		ponerBoton("0",new InsertaNumero());
		ponerBoton(".",new AccionOrden());
		ponerBoton("=",new AccionOrden());
		ponerBoton("+",new AccionOrden());
		add(milamina2,BorderLayout.CENTER);
		
	}
	private void ponerBoton(String rotulo, ActionListener oyente) {
		JButton boton = new JButton(rotulo);
		milamina2.add(boton);
		boton.addActionListener(oyente);
	}
	
	private  class InsertaNumero implements  ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String entrada=e.getActionCommand();//Obtenemos el texto del botón pulsado
			if(principio) {
				pantalla.setText("");
				principio=false;
			}
			pantalla.setText(pantalla.getText()+entrada);
		}
		
	}
	private class AccionOrden implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String operacion = e.getActionCommand();
			calcular(Double.parseDouble(pantalla.getText()));
			ultimaOperacion=operacion;
			principio=true;
			
		}
		public void calcular(double x) {
			if(ultimaOperacion.equals("+")) {
				resultado+=x;
			}
			else if(ultimaOperacion.equals("-")) {
				resultado-=x;
			}
			else if(ultimaOperacion.equals("*")) {
				resultado*=x;
			}
			else if(ultimaOperacion.equals("/")) {
				resultado/=x;
			}
			else if(ultimaOperacion.equals("=")) {
				resultado=x;
			}
			pantalla.setText(String.valueOf(resultado));
		}
		
	}
	private double resultado;
	private JPanel milamina2;
	private JButton pantalla;
	private Boolean principio;//by default is false
	private String ultimaOperacion;
}