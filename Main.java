import Formularios.PresupuestoForm;
import Formularios.ProductoForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static CardLayout cl;
    private static JPanel jp;

    public static void main(String[] args) throws Exception {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setJMenuBar(doMenu());

        PresupuestoForm pre = new PresupuestoForm();
        ProductoForm prod = new ProductoForm();

        cl = new CardLayout();

        jp = new JPanel();
        jp.setLayout(cl);
        jp.add(pre.getMainPanel(), "PRESUPUESTO");
        jp.add(prod.getMainPanel(), "PRODUCTO");
        cl.addLayoutComponent(prod.getMainPanel(), "PRODUCTO");

        jf.setContentPane(jp);
        jf.pack();
        jf.setLocationRelativeTo(null);
        cambiarVentanas(jp);
        jf.setVisible(true);
    }

    private static void cambiarVentanas(JPanel jp) {
        cl.show(jp, "PRESUPUESTO");
    }

    private static JMenuBar doMenu() {
        // Realizamos las ventanas del menú y sus opciones desplegables
        JMenuBar jmb = new JMenuBar();
        JMenu menuFile = new JMenu("Presupuesto");
        JMenuItem addBudget = new JMenuItem("Administrar Presupuestos");
        JMenu menuProd = new JMenu("Productos");
        JMenuItem openProd = new JMenuItem("Administrar Productos");
        // Las añadimos a los Items de los menús
        menuFile.add(addBudget);
        menuProd.add(openProd);
        // Añadimos los items al menú
        jmb.add(menuFile);
        jmb.add(menuProd);

        // Realizamos eventos sobre los botones para que se lancen en un determinado momento cada vez que queramos que
        // se ejecute una acción
        addBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(jp, "PRESUPUESTO");
            }
        });

        // PRODUCTOS
        openProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(jp, "PRODUCTO");
            }
        });
        return jmb;
    }
}
