package Clases;

public class Empresa {
    private int CIF;
    private int Telefono;
    private String Nombre;
    private String Direccion;
    private String Estado;
    private String CampoObservaciones;

    public Empresa() {}

    public Empresa(int CIF, int Telefono, String Nombre, String Direccion, String Estado, String CampoObservaciones){
        this.CIF = CIF;
        this.Telefono = Telefono;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Estado = Estado;
        this.CampoObservaciones = CampoObservaciones;
    }

    public int getCIF() {return CIF;}

    public void setCIF(int CIF) {this.CIF = CIF;}

    public int getTelefono() {return Telefono;}

    public void setTelefono(int telefono) {Telefono = telefono;}

    public String getNombre() {return Nombre;}

    public void setNombre(String nombre) {Nombre = nombre;}

    public String getDireccion() {return Direccion;}

    public void setDireccion(String direccion) {Direccion = direccion;}

    public String getEstado() {return Estado;}

    public void setEstado(String estado) {Estado = estado;}

    public String getCampoObservaciones() {return CampoObservaciones;}

    public void setCampoObservaciones(String campoObservaciones) {CampoObservaciones = campoObservaciones;}
}