package persona;

public class Cliente extends Persona{
	
	private int puntos;

	public Cliente(String nombre, int cedula, int puntos) {
		super(nombre, cedula);
		// TODO Auto-generated constructor stub
		this.setPuntos(puntos);
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	

}
