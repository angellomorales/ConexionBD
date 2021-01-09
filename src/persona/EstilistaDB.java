package persona;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import basedatos.BaseDatos;
import basedatos.ConversorRSaDTM;

public class EstilistaDB {

    protected BaseDatos bd = new BaseDatos();

    public void agregar(Estilista estilista) {
        String query = "INSERT INTO estilistas(puesto,nombre,cedula) "
                + "VALUES (" + estilista.getPuesto() + ",'" + estilista.getNombre() + "'," + estilista.getCedula() + ")";

        try {
            bd.instruccionWriteSQL(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "No se realizo el ingreso", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editar(Estilista estilista) throws SQLException {
        String query = "UPDATE estilistas SET "
                + "nombre='" + estilista.getNombre() + "',"
                + "Cedula=" + estilista.getCedula()
                + " WHERE puesto=" + estilista.getPuesto();

        bd.instruccionWriteSQL(query);
    }

    public void eliminar(Estilista estilista) throws SQLException {
        String query = "DELETE FROM estilistas "
                + "WHERE puesto=" + estilista.getPuesto();

        bd.instruccionWriteSQL(query);
    }

    public void mostrar(DefaultTableModel modelo) throws SQLException { //modelo es el objeto JTable

        String query = "SELECT * FROM estilistas";
        ResultSet rs = bd.instruccionReadSQL(query);//almacenar datos en un objeto ResultSet
        ConversorRSaDTM.rellena(rs, modelo);

        rs.close();
        bd.cierraConexion();

    }

}
