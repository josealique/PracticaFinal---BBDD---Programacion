package Formularios;

import Clases.Cliente;
import Clases.Empresa;
import Clases.Presupuesto;
import Clases.Producto;
import DAO.DAOClienteImpl;
import DAO.DAOEmpresaImpl;
import DAO.DAOPresupuestoImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PresupuestoForm {
    private JPanel MainPanel;
    private TableModel tableModel;
    private JComboBox<String> comboCli;
    private JComboBox<String> comboEmp;
    private JButton nuevoCli;
    private JButton nuevaEmp;
    private JTable productosTabla = new JTable();
    private JPanel panelTabla;
    private JTextArea observaciones;
    private JButton insert;
    private JButton delete;
    private JButton update;
    private final String[] columnas = new String[]{"Id Presupuesto", "Fecha de creacion", "DNI_Cliente", "Nombre Cliente", "DNI_Trabajador", "Nombre Trabajador", "Empresa", "Estado", "Precio Total"};
    private List<Presupuesto> presupuestos = new ArrayList<>();
    private List<List<Object>> list = new ArrayList<>();
    private final DAOPresupuestoImpl dpres = new DAOPresupuestoImpl();
    private final DAOEmpresaImpl daoEmpresa = new DAOEmpresaImpl();
    private final DAOClienteImpl daoCliente = new DAOClienteImpl();
    private NewPresupuestoDialog presupuestoDialog = new NewPresupuestoDialog();

    public PresupuestoForm() throws Exception {
        setEmpresas();
        setClientes();
        init();
        generarTabla();
        addEventListeners();
    }

    private void init() throws Exception {
        presupuestos.clear();
        list.clear();
        presupuestos = dpres.listarPresupuestos();
        list = obtenerLista();
    }

    private void addEventListeners() {
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Presupuesto presupuesto = presupuestoDialog.crearPresupuesto();
                    if (presupuesto != null){
                        dpres.insertar(presupuesto);
                        actualizarTabla();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        nuevoCli.addActionListener(e -> {
            NewClienteDialog cliente = new NewClienteDialog();
            Cliente cli = cliente.crearCliente();
            if (cli != null){
                try {
                    daoCliente.insertar(cli);
                    init();
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void generarTabla() {
        tableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {return list.size();}

            @Override
            public int getColumnCount() {return columnas.length;}

            @Override
            public String getColumnName(int column) {return columnas[column];}

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {return list.get(rowIndex).get(columnIndex);}
        };
        productosTabla.setModel(tableModel);
        panelTabla.add(new JScrollPane(productosTabla));
    }

    private List<List<Object>> obtenerLista() {
        for (Clases.Presupuesto presupuesto : presupuestos) {
            List<Object> l = new ArrayList<>();
            l.add(presupuesto.getId());
            l.add(presupuesto.getFecha());
            l.add(presupuesto.getCliente().getDNI());
            l.add(presupuesto.getCliente().getNombre());
            l.add(presupuesto.getTrabajador().getDNI());
            l.add(presupuesto.getTrabajador().getNombre());
            l.add(presupuesto.getEmpresa().getNombre());
            l.add(presupuesto.getEmpresa().getEstado());
            l.add(getPrecioTotal(presupuesto.getProducto()));
            list.add(l);
        }
        return list;
    }

    private Integer getPrecioTotal(List<Producto> producto) {
        Double total = 0.0;
        for (Producto p : producto){
            total += p.getPrecio();
        }
        return total.intValue();
    }

    private void eliminarFilas() throws Exception {
        int filaSeleccionada = productosTabla.getSelectedRow();
        if (productosTabla.getSelectedRows().length != 0) {
            try {
                dpres.eliminar(presupuestos.get(filaSeleccionada));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        actualizarTabla();
    }

    private void actualizarTabla() throws Exception {
        init();
        ((AbstractTableModel) tableModel).fireTableDataChanged();
    }

    private void setEmpresas() throws Exception {
        List<Empresa> empresaList = daoEmpresa.listarEmpresas();
        for (Empresa emp : empresaList) {
            this.comboEmp.addItem(emp.getNombre());
        }
    }

    private void setClientes() throws Exception {
        List<Cliente> clienteList = daoCliente.listarClientes();
        for (Cliente cliente : clienteList) {
            this.comboCli.addItem(cliente.getNombre());
        }
    }

    public JPanel getMainPanel() {return MainPanel;}
}