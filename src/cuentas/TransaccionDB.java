package cuentas;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import basedatos.BaseDatos;
import basedatos.ConversorRSaDTM;

public class TransaccionDB {

    private Date fecha;
    private Time hora;

    protected BaseDatos bd = new BaseDatos();

    public Date getFecha() {
        this.fecha = Date.valueOf(LocalDate.now());
        return fecha;
    }

    public Time getHora() {
        this.hora = Time.valueOf(LocalTime.now());
        return hora;
    }

    public void agregar(Transaccion transaccion) {
        String query = "INSERT INTO cuenta(fecha,estilistas_puesto,ingreso,descripcion,tipo,gasto,valorDescuento,anticipo)"
                + "VALUES('" + this.getFecha() + "'," + transaccion.getEstilista().getPuesto() + "," + transaccion.getIngresos() + ",'" + transaccion.getDescripcion()
                + "','" + transaccion.getTipo() + "'," + transaccion.getGastos() + "," + transaccion.getDescuentoEstilista() + "," + transaccion.getAnticipo() + ")";

        try {
            bd.instruccionWriteSQL(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "No se realizo el ingreso", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editar(Transaccion transaccion, int id) throws SQLException {
        String query = "UPDATE cuenta SET "
                + "ingreso=" + transaccion.getIngresos() + ","
                + "gasto=" + transaccion.getGastos() + ","
                + "valorDescuento=" + transaccion.getDescuentoEstilista() + ","
                + "tipo='" + transaccion.getTipo() + "',"
                + "descripcion='" + transaccion.getDescripcion() + "',"
                + "estilistas_puesto=" + transaccion.getEstilista().getPuesto()
                + "anticipo=" + transaccion.getAnticipo() + ","
                + " WHERE id=" + id;

        bd.instruccionWriteSQL(query);
    }

    public void eliminar(Transaccion transaccion, int id) throws SQLException {
        String query = "DELETE FROM cuenta "
                + "WHERE id=" + id;

        bd.instruccionWriteSQL(query);
    }

    public void mostrar(DefaultTableModel modelo) throws SQLException { //modelo es el objeto JTable

        String query = "SELECT * FROM cuenta";
        ResultSet rs = bd.instruccionReadSQL(query);//almacenar datos en un objeto ResultSet
        ConversorRSaDTM.rellena(rs, modelo);

        rs.close();
        bd.cierraConexion();

    }

    @SuppressWarnings("deprecation")
    public int calcularSueldo(Transaccion transaccion, double porcentaje, Date fecha) throws SQLException {
        int sueldo = 0;
        int fechaInicial = 0;
        int fechaFinal = 0;
        if (fecha.getDate() >= 1 && fecha.getDate() <= 15) {
            fechaInicial = 1;
            fechaFinal = 15;
        } else {
            fechaInicial = 16;
            fechaFinal = 31;
        }
        if (porcentaje >= 0 && porcentaje <= 100) {
            String query = "SELECT estilistas_puesto AS puesto,SUM(ingreso) AS totalIngresos,"
                    + "SUM(gasto) AS totalGasto, SUM(valorDescuento) AS descuentoAplicado, SUM(anticipo) AS anticipo "
                    + "FROM cuenta "
                    + "WHERE estilistas_puesto= " + transaccion.getEstilista().getPuesto()
                    + " AND fecha >='" + (fecha.getYear() + 1900) + "-" + (fecha.getMonth() + 1) + "-" + fechaInicial + "' "
                    + "AND fecha <='" + (fecha.getYear() + 1900) + "-" + (fecha.getMonth() + 1) + "-" + fechaFinal + "'";
            System.out.println(query);
            ResultSet rs = bd.instruccionReadSQL(query);
            while (rs.next()) {
                System.out.println("ingresos: " + rs.getInt("totalIngresos"));
                System.out.println("sueldo: " + (rs.getInt("totalIngresos") * (porcentaje / 100)));
                System.out.println("descuento: " + rs.getInt("descuentoAplicado"));
                System.out.println("anticipo: " + rs.getInt("anticipo"));

                sueldo = (int) (sueldo + (rs.getInt("totalIngresos") * (porcentaje / 100)) - rs.getInt("descuentoAplicado") - rs.getInt("anticipo"));
            }
        } else {
            JOptionPane.showMessageDialog(null, "ingrese valores entre 0% y 100%", "Error Porcentaje", JOptionPane.ERROR_MESSAGE);
        }
        return sueldo;
    }

    public void calcularCaja(DefaultTableModel modelo) throws SQLException { //modelo es el objeto JTable

        String query = "SELECT * FROM cuenta";
        ResultSet rs = bd.instruccionReadSQL(query);//almacenar datos en un objeto ResultSet
        ConversorRSaDTM.rellena(rs, modelo);

        rs.close();
        bd.cierraConexion();

    }

}
