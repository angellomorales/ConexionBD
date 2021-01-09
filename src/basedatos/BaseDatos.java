package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {

    private final String URL = "jdbc:mysql://localhost:3306/";//direccion donde esta la BD siempre tiene esa notacion
    private final String DB = "Peluqueria";//nombre de base de datos
    private final String USUARIO = "epocas";//usuario para conetar a BD
    private final String PASSWORD = "OrlandoCardona";//pass para conectar aBD

    private String query = "";

    private Connection conexion = null;//objeto conexion

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @SuppressWarnings("finally")
    public Connection conectar() throws SQLException {//declarar exepciones para SQL

        try {

            Class.forName("com.mysql.jdbc.Driver");// ejecutar DRIVER MSQL para crear conexion, escribir tal cual
            conexion = DriverManager.getConnection(URL + DB, USUARIO, PASSWORD);//establecer conexion

            if (conexion != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            return conexion;
        }
    }

    public void instruccionWriteSQL(String query) throws SQLException {
        this.setQuery(query);
        Statement sentencia = null;

        //BaseDatos bd=new BaseDatos();//crear objeto de BaseDatos
        sentencia = this.conectar().createStatement();	//conectar a BaseDatos, iniciar consulta (query)

        if (sentencia.executeUpdate(this.getQuery()) > 0) {	//ejecutar consulta
            System.out.println("el registro se realizo exitosamente");
        } else {
            System.out.println("no se pudo realizar el registro");
            System.out.println(this.getQuery());
        }
        conexion.close();
        if (conexion.isClosed()) {
            System.out.println("conexion cerrada");
        }
    }

    public ResultSet instruccionReadSQL(String query) {
        ResultSet rs = null;
        try {
            // Se crea un Statement, para realizar la consulta
            Statement sentencia = this.conectar().createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = sentencia.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void cierraConexion() {
        try {
            conexion.close();
            if (conexion.isClosed()) {
                System.out.println("conexion cerrada");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
