package Clases;

import java.util.List;

public class Presupuesto {
    private int id;
    private String fecha;
    private int cantidad;
    private Cliente cliente;
    private Trabajador trabajador;
    private Empresa empresa;
    private List<Producto> producto;

    public Presupuesto() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getFecha() {return fecha;}

    public void setFecha(String fecha) {this.fecha = fecha;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public Trabajador getTrabajador() {return trabajador;}

    public void setTrabajador(Trabajador trabajador) {this.trabajador = trabajador;}

    public Empresa getEmpresa() {return empresa;}

    public void setEmpresa(Empresa empresa) {this.empresa = empresa;}

    public List<Producto> getProducto() {return producto;}

    public void setProducto(List<Producto> producto) {this.producto = producto;}

    @Override
    public String toString() {
        return "PresupuestoForm{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", cliente=" + cliente +
                ", trabajador=" + trabajador +
                ", empresa=" + empresa +
                ", producto=" + producto +
                '}';
    }
}