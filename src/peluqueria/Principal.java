package peluqueria;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import cuentas.Transaccion;
import cuentas.TransaccionDB;
import persona.Estilista;
//import com.epocas.persona.EstilistaDB;

public class Principal {

    public static void main(String[] args) throws SQLException, ParseException {
        // TODO Auto-generated method stub

        Estilista p1 = new Estilista("Jorge", 173455230, 1);
//		Estilista p2=new Estilista("Miriam",345355353,2);

        Transaccion transaccionesP1 = new Transaccion(p1, 5000, 1000, 0, "tinte", 0);
//		Transaccion transaccionesP2=new Transaccion(p2,100000,2000);

        JFrame ventana = new JFrame("Contenido base de datos");
        JTable tabla = new JTable();

        JScrollPane scroll = new JScrollPane(tabla);
        DefaultTableModel modelo = new DefaultTableModel();

        ventana.getContentPane().add(scroll);
        ventana.pack();
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        //BASE DE DATOS
//		EstilistaDB estilistaDB=new EstilistaDB();
        /*//estilistaDB.agregar(p1);
		//estilistaDB.agregar(p2);
		estilistaDB.editar(p1);
		estilistaDB.editar(p2);
		//estilistaDB.eliminar(p1);
		//estilistaDB.eliminar(p2);*/
        //estilistaDB.mostrar(modelo);//extraer y convertir los datos de la bd
        TransaccionDB transaccionDB = new TransaccionDB(); //peatonal 18 entre 6 y 7 luxort condina 10 am
        @SuppressWarnings("deprecation")
        Date fecha = new Date(2016 - 1900, 02 - 1, 17);
        //transaccionDB.agregar(transaccionesP1);
        //transaccionDB.agregar(transaccionesP2);
        transaccionDB.mostrar(modelo);
        int s = transaccionDB.calcularSueldo(transaccionesP1, 50, fecha);
        System.out.println(s);
        tabla.setModel(modelo);//cargar los datos convertidos en la tabla

    }

}
