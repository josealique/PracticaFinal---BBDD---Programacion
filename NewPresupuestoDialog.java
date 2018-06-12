package Formularios;

import Clases.*;
import DAO.DAOClienteImpl;
import DAO.DAOEmpresaImpl;
import DAO.DAOTrabajadorImpl;
import javax.swing.*;
import java.util.List;

public class NewPresupuestoDialog {
    private JTextField Cantidad;
    private JComboBox<String> comboCliente;
    private JComboBox<String> comboTrabajador;
    private JComboBox<String> comboEmpresa;
    private DAOClienteImpl daoCliente = new DAOClienteImpl();
    private DAOEmpresaImpl daoEmpresa = new DAOEmpresaImpl();
    private DAOTrabajadorImpl daoTrabajador = new DAOTrabajadorImpl();
    private List<Cliente> listarClientes;
    private List<Trabajador> listarTrabajadores;
    private List<Empresa> listarEmpresas;

    NewPresupuestoDialog() throws Exception {
        Cantidad = new JTextField();
        listarEmpresas = daoEmpresa.listarEmpresas();
        listarClientes = daoCliente.listarClientes();
        listarTrabajadores = daoTrabajador.listarTrabajadores();
        System.out.println(listarTrabajadores);
        this.comboCliente = new JComboBox<>();
        this.comboEmpresa = new JComboBox<>();
        this.comboTrabajador = new JComboBox<>();
        setClientes();
        setEmpresas();
        setTrabajadores();
    }

    public Presupuesto crearPresupuesto() throws Exception {
        JComponent[] comps = new JComponent[]{
                new JLabel("Cantidad: "), Cantidad,
                new JLabel("Cliente"), comboCliente,
                new JLabel("Trabajador"), comboTrabajador,
                new JLabel("Empresa"), comboEmpresa
        };
        int resultado = JOptionPane.showConfirmDialog(null, comps, "Añadir", JOptionPane.YES_NO_OPTION);
        Presupuesto pre = new Presupuesto();
        if (resultado == JOptionPane.YES_OPTION) {
            String comprobarCantidad = Cantidad.getText();
            try {
                int cantidad = Integer.parseInt(comprobarCantidad);
                pre = new Presupuesto();

                Cliente selecionCliente = listarClientes.get(comboCliente.getSelectedIndex());
                pre.setCliente(selecionCliente);
                Trabajador seleccionTrabajador = listarTrabajadores.get(comboTrabajador.getSelectedIndex());
                pre.setTrabajador(seleccionTrabajador);
                seleccionTrabajador.setEmpresa(daoEmpresa.getEmpresa(seleccionTrabajador));
                pre.setEmpresa(seleccionTrabajador.getEmpresa());
                pre.setCantidad(cantidad);
                return pre;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El campo de Cantidad no puede contener letras", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        return pre;
    }

    private void setEmpresas() throws Exception {
        listarEmpresas = daoEmpresa.listarEmpresas();
        for (Empresa emp : listarEmpresas) {
            this.comboEmpresa.addItem(emp.getNombre());
        }
    }

    private void setClientes() throws Exception {
        for (Cliente cli : listarClientes) {
            this.comboCliente.addItem(cli.getNombre());
        }
    }

    private void setTrabajadores() throws Exception {
        for (Trabajador trab : listarTrabajadores) {
            this.comboTrabajador.addItem(trab.getNombre());
        }
    }
}