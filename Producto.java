package Formularios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Producto {
    private JLabel Header;
    private JButton Aceptar;
    private JButton cancelarButton;
    private JTable table1;
    private JPanel MainPanel;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JButton getAceptar() {
        return Aceptar;
    }

    public Producto(){
        Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,"Los campos no pueden estar vacios","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Tabla
//    List<Producte> list = new ArrayList<>();
//    TableModel tableModel = new AbstractTableModel() {
//        @Override
//        public int getRowCount() {
//            return list.size();
//        }
//
//        @Override
//        public int getColumnCount() {
//            return 4;
//        }
//
//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//            return null;
//        }
//
//        @Override
//        public String getColumnName(int column) {
//            switch (column){
//                case 0:
//                    return "Id Producto";
//                case 1:
//                    return "Nombre";
//                case 2:
//                    return "Precio_Base";
//                case 3:
//                    return "Stock";
//            }
//        }
//    }
}
