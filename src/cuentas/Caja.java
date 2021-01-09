package cuentas;

public class Caja {
	
	private int ingresos;
	private int gastos;
	
	public Caja(int ingresos, int gastos) {
		super();
		this.ingresos = ingresos;
		this.gastos = gastos;
	}
	public int getIngresos() {
		return ingresos;
	}
	public void setIngresos(int ingresos) {
		this.ingresos = ingresos;
	}
	public int getGastos() {
		return gastos;
	}
	public void setGastos(int gastos) {
		this.gastos = gastos;
	}
	

}
