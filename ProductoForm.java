package Formularios;

import Clases.Producto;
import DAO.DAOProducteImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductoForm {
    private JLabel Header;
    private JPanel MainPanel;
    private JPanel panelTabla;
    private JTable table1;
    private JButton add;
    private JButton delete;
    private JButton update;
    private TableModel tableModel;
    private final String[] columnas = new String[]{"Id Producto", "Nombre", "Stock", "Precio", "Empresa"};
    private final DAOProducteImpl daop = new DAOProducteImpl();
    private NewProductoDialog pdiag = new NewProductoDialog();

    // Tabla
    private List<List<Object>> list = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();

    public ProductoForm() throws Exception {
        init();
        generarTabla();
        meterListeners();
    }

    private void meterListeners() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto p = pdiag.crearProducto();
                    daop.insertar(p);
                    actualizarTabla();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarFilas();
                    actualizarTabla();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto;
                try {
                    producto = pdiag.actualizar(productos.get(table1.getSelectedRow()));
                    if (producto != null) {
                        daop.actualizar(producto);
                        actualizarTabla();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void init() throws Exception {
        productos.clear();
        list.clear();
        productos = daop.listarProductos();
        list = obtenerLista();
    }

    private void generarTabla() {
        tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return list.size();
            }

            @Override
            public int getColumnCount() {
                return columnas.length;
            }

            @Override
            public String getColumnName(int column) {
                return columnas[column];
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return list.get(rowIndex).get(columnIndex);
            }
        };
        table1.setModel(tableModel);
        panelTabla.add(new JScrollPane(table1));
    }

    private List<List<Object>> obtenerLista(){
        for (Producto producto : productos) {
            List<Object> l = new ArrayList<>();
            l.add(producto.getId());
            l.add(producto.getNombre());
            l.add(producto.getStock());
            l.add(producto.getPrecio());
            l.add(producto.getEmpresa());
            list.add(l);
        }
        return list;
    }

    private void eliminarFilas() throws Exception {
        int filaSeleccionada = table1.getSelectedRow();
        if (table1.getSelectedRows().length != 0){
            try {
               daop.eliminar(productos.get(filaSeleccionada));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        actualizarTabla();
    }

    private void actualizarTabla() throws Exception {
        init();
        ((AbstractTableModel) tableModel).fireTableDataChanged();
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }
}