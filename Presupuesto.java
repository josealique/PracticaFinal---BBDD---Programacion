package Formularios;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presupuesto {
    public Presupuesto(){
        nuevoClienteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                NewClientDialog nc = new NewClientDialog();
                nc.setModal(true);
                nc.pack();
                nc.setVisible(true);
                String docid = nc.getDNI().getText();
            }
        });

        nuevaEmpresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewEmpresaDialog ne = new NewEmpresaDialog();
                ne.setModal(true);
                ne.pack();
                ne.setVisible(true);
                String cif = ne.getCIF().getText();
                String nombre = ne.getNombre().getText();
                String dir = ne.getDireccion().getText();
            }
        });

        buscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buscarEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        nuevaLineaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        eliminarLineaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    private TableModel tableModel;
//    private List<ArrayList>;


    static class LineaPresupuesto{
        int cantidad;
        String concepto;
        double precio;
        int total;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private JPanel MainPanel;
    private JButton nuevoClienteButton;
    private JButton buscarCliente;
    private JTextField Cliente;
    private JButton buscarEmpresa;
    private JButton nuevaEmpresaButton;
    private JTextField Empresa;
    private JTable Productos;
    private JButton nuevaLineaButton;
    private JTextArea Observaciones;
    private JButton eliminarLineaButton;
}
