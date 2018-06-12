package Clases;

public class Persona {
    private String DNI;
    private String Nombre;
    private String Apellidos;
    private int Telefono;

    Persona(){}

    public Persona(String DNI, String Nombre, String Apellidos, int Telefono){
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Telefono = Telefono;
    }

    public String getDNI() {return DNI;}

    public void setDNI(String DNI) {this.DNI = DNI;}

    public String getNombre() {return Nombre;}

    public String setNombre(String nombre) {
        Nombre = nombre;
        return nombre;
    }

    public String getApellidos() {return Apellidos;}

    public void setApellidos(String apellidos) {Apellidos = apellidos;}

    public int getTelefono() {return Telefono;}

    public void setTelefono(int telefono) {Telefono = telefono;}

    @Override
    public String toString() {
        return "Persona{" +
                "DNI='" + DNI + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Telefono=" + Telefono +
                '}';
    }
}