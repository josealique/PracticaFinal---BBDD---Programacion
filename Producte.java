package Clases;

public class Producte {
    private int Id;
    private String Nombre;
    private int Precio_Base;
    private int Stock;

    public Producte(int Id, String Nombre, int Stock, int Precio_Base){
        this.Id = Id;
        this.Nombre = Nombre;
        this.Stock = Stock;
        this.Precio_Base = Precio_Base;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPrecio_Base() {
        return Precio_Base;
    }

    public void setPrecio_Base(int Precio_Base) {
        this.Precio_Base = Precio_Base;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock){
        this.Stock = Stock;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
