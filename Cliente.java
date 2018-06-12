package Clases;

public class Cliente extends Persona {
    private String email;

    public Cliente() {}

    public Cliente(String email){
        this.email = email;
    }

    public Cliente(String dni, String nombre, String apellidos, String email, Integer telefono) {
        super(dni, nombre, apellidos, telefono);
        this.email = email;
    }
    public Cliente(String dni, String nombre, String apellidos, Integer telefono, String email) {
        super(dni, nombre, apellidos, telefono);
        this.email = email;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}