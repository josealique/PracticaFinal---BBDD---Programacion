package Formularios;

import Clases.Empresa;
import Clases.Producto;
import DAO.DAOEmpresaImpl;

import javax.swing.*;
import java.util.List;

public class NewProductoDialog {
    private JTextField Nombre = new JTextField();
    private JTextField Stock = new JTextField();
    private JTextField Precio = new JTextField();
    private DAOEmpresaImpl daoEmpresa = new DAOEmpresaImpl();
    private JComboBox<String> comboEmpresa = new JComboBox<>();
    private List<Empresa> list = daoEmpresa.listarEmpresas();
    private final JComponent[] comps = new JComponent[]{
            new JLabel("Nombre: "), Nombre,
            new JLabel("Stock: "), Stock,
            new JLabel("Precio"), Precio,
            new JLabel("Empresa"), comboEmpresa
    };

    public NewProductoDialog() throws Exception {
        setEmpresas();
    }

    public Producto crearProducto() throws Exception {
        int resultado = JOptionPane.showConfirmDialog(null, comps, "Añadir", JOptionPane.YES_NO_OPTION);
        Producto prod = new Producto();
        if (resultado == JOptionPane.YES_OPTION) {
            String comprobarNombre = Nombre.getText();
            String comprobarStock = Stock.getText();
            String comprobarPrecio = Precio.getText();
            if (comprobarNombre.isEmpty() || comprobarStock.isEmpty() || comprobarPrecio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            try {
                int stock = Integer.parseInt(comprobarStock);
                double precio = Double.parseDouble(comprobarPrecio);
                prod = new Producto(comprobarNombre, stock, precio);
                int selecionEmpresa = comboEmpresa.getSelectedIndex();
                prod.setEmpresa(list.get(selecionEmpresa));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Los campos de Stock y Precio no pueden contener letras", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        return prod;
    }

    private void setEmpresas() throws Exception {
        list = daoEmpresa.listarEmpresas();
        for (Empresa emp : list) {
            this.comboEmpresa.addItem(emp.getNombre());
        }
    }

    public Producto actualizar(Producto producto) {
        int resultado = JOptionPane.showConfirmDialog(null, comps, "modificar", JOptionPane.YES_NO_OPTION);
        if (resultado == 0) {
            String comprobarNombre = Nombre.getText();
            String comprobarStock = Stock.getText();
            String comprobarPrecio = Precio.getText();
            if (comprobarNombre.isEmpty() || comprobarStock.isEmpty() || comprobarPrecio.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
                return actualizar(producto);
            }
            producto.setNombre(comprobarNombre);
            producto.setStock(Integer.parseInt(comprobarStock));
            producto.setPrecio(Double.parseDouble(comprobarPrecio));
            return producto;
        }
        if (JOptionPane.showConfirmDialog(null, "Quieres salir?") == 0){
            return null;
        }
        return actualizar(producto);
    }
}