package Clases;

public class Trabajador extends Persona {
    private Empresa empresa;

    public Trabajador() {}

    public Trabajador(String dni,String nombre, String apellidos, Integer telefono, Empresa empresa) {
        super(dni, nombre, apellidos, telefono);
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {return empresa;}

    public void setEmpresa(Empresa empresa) {this.empresa = empresa;}

}