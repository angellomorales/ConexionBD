package cuentas;

import javax.swing.JOptionPane;

import persona.Estilista;

public class Transaccion extends Caja {

    private int descuentoEstilista;
    private int anticipo;
    private String descripcion;
    private final String[] opcion = {"compartido", "completo", "consumible"};
    private String tipo;
    private Estilista estilista;

    public Transaccion(Estilista estilista, int ingresos, int gastos, int tipo, String descripcion, int anticipo) {
        super(ingresos, 0);
        this.setGastos(gastos, tipo, descripcion);
        this.setEstilista(estilista);
        this.setAnticipo(anticipo);
        // TODO Auto-generated constructor stub
    }

    public Transaccion(Estilista estilista, int ingresos, int anticipo) {
        super(ingresos, 0);
        this.setEstilista(estilista);
        this.setAnticipo(anticipo);
        // TODO Auto-generated constructor stub
    }

    public Transaccion(Estilista estilista, int gastos, int tipo, String descripcion) {
        super(0, 0);
        this.setGastos(gastos, tipo, descripcion);
        this.setEstilista(estilista);
        // TODO Auto-generated constructor stub
    }

    public int getDescuentoEstilista() {
        return descuentoEstilista;
    }

    public void setDescuentoEstilista(int descuentoEstilista) {
        this.descuentoEstilista = descuentoEstilista;
    }

    public int getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(int anticipo) {
        this.anticipo = anticipo;
    }

    public Estilista getEstilista() {
        return estilista;
    }

    public void setEstilista(Estilista estilista) {
        this.estilista = estilista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(int opcion) {
        if (opcion >= 0 && opcion <= 2) {
            this.tipo = this.opcion[opcion];
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setGastos(int valor, int opcion, String descripcion) {
        // TODO Auto-generated method stub
        this.setDescripcion(descripcion);
        this.setTipo(opcion);

        if (this.getTipo() == "compartido") {
            super.setGastos(valor);
            this.setDescuentoEstilista(valor / 2);
        } else {
            if (this.getTipo() == "completo") {
                super.setGastos(valor);
                this.setDescuentoEstilista(valor);
            } else {
                if (this.getTipo() == "consumible") {
                    this.setDescuentoEstilista(valor);
                } else {
                    JOptionPane.showMessageDialog(null, "ingrese valores entre 0 y 2", "Error de tipo gasto", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
