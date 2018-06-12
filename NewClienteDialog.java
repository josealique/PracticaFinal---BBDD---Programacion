package Formularios;

import Clases.Cliente;

import javax.swing.*;

public class NewClienteDialog {
    private JTextField DNI;
    private JTextField Nombre;
    private JTextField Apellidos;
    private JTextField Telefono;
    private JTextField Email;

    public NewClienteDialog() {
        DNI = new JTextField();
        Nombre = new JTextField();
        Apellidos = new JTextField();
        Telefono = new JTextField();
        Email = new JTextField();
    }

    public Cliente crearCliente() {
        JComponent[] comps = new JComponent[]{
                new JLabel("DNI: "), DNI,
                new JLabel("Nombre: "), Nombre,
                new JLabel("Apellidos: "), Apellidos,
                new JLabel("Telefono"), Telefono,
                new JLabel("Email :"), Email
        };
        int resultado = JOptionPane.showConfirmDialog(null, comps, "Añadir Cliente", JOptionPane.YES_NO_CANCEL_OPTION);
        Cliente cliente = new Cliente();
        if (resultado == JOptionPane.YES_OPTION) {
            if (vacio()){
                JOptionPane.showMessageDialog(null,"Los campos no pueden estar vacios","Error",JOptionPane.ERROR_MESSAGE);
                return null;
            } try {
                int telefono = Integer.parseInt(Telefono.getText());
                cliente = new Cliente(DNI.getText(),Nombre.getText(),Apellidos.getText(),telefono,Email.getText());

            } catch (Exception e){
                JOptionPane.showMessageDialog(null,"El campo telefono no puede contener letras","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        return cliente;
    }

    private boolean vacio(){
        return DNI.getText().equals("") || Nombre.getText().equals("") || Apellidos.getText().equals("") || Telefono.getText().equals("") || Email.getText().equals("");
    }
}