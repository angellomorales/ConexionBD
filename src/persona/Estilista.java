package persona;

import javax.swing.JOptionPane;

public class Estilista extends Persona{
	
	private int puesto;

	public Estilista(String nombre, int cedula, int puesto) {
		super(nombre, cedula);
		// TODO Auto-generated constructor stub
		this.setPuesto(puesto);
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		if(puesto!=0){
			this.puesto = puesto;
		}else{
			JOptionPane.showMessageDialog(null,"La posición del puesto inician a partir de 1","Posición invalida", JOptionPane.ERROR_MESSAGE);
		}
	}



}
