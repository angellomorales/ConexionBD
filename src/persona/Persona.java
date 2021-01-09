package persona;

public class Persona {

    private String nombre;
    private int cedula;

    public Persona(String nombre, int cedula) {
        super();
        this.setNombre(nombre);
        this.setCedula(cedula);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

}
