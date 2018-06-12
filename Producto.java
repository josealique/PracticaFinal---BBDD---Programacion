package Clases;

public class Producto {
    private int Id;
    private String Nombre;
    private int Stock;
    private double Precio;
    private int Cantidad;
    private Empresa empresa;

    public Producto(){}

    public Producto(String Nombre, int Stock, double Precio){
        this.Nombre = Nombre;
        this.Stock = Stock;
        this.Precio = Precio;
    }

    public int getId() {return Id;}

    public void setId(int id) {Id = id;}

    public String getNombre() {return Nombre;}

    public void setNombre(String Nombre) {this.Nombre = Nombre;}

    public int getStock() {return Stock;}

    public void setStock(int Stock){this.Stock = Stock;}

    public double getPrecio() {return Precio;}

    public void setPrecio(double precio) {Precio = precio;}

    public int getCantidad() {return Cantidad;}

    public void setCantidad(int cantidad) {this.Cantidad = cantidad;}

    public Empresa getEmpresa() {return empresa;}

    public void setEmpresa(Empresa empresa) {this.empresa = empresa;}

    @Override
    public String toString() {
        return "Producto{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Stock=" + Stock + '\'' +
                ", Precio=" + Precio + '\'' +
                ", Empresa=" + this.empresa + '\'' +
                '}';
    }
}