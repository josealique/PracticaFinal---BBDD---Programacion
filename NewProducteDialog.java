package Formularios;

import javax.swing.*;
import java.awt.event.*;

public class NewProducteDialog extends JDialog {
    private JPanel MainPanel;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JTextField Nombre;
    private JTextField Precio;
    private JTextField Stock;

    public JTextField getNombre() {
        return Nombre;
    }

    public JTextField getPrecio() {
        return Precio;
    }

    public JTextField getStock() {
        return Stock;
    }

    public NewProducteDialog() {
        setContentPane(MainPanel);
        setModal(true);
        getRootPane().setDefaultButton(aceptarButton);

        aceptarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                onOK();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

//         call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        MainPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NewProducteDialog dialog = new NewProducteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
