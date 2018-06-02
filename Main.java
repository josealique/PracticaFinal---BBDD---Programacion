import Clases.Producte;
import DAO.DAOProducteImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static CardLayout cl;
    private static JPanel jp;
    public static void main(String[] args) throws Exception {

        Producte prod = new Producte(1,"Intel",45,860);
        DAOProducteImpl daoProducte = new DAOProducteImpl();
        daoProducte.actualizar(prod);


//        JFrame jf = new JFrame();
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setJMenuBar(doMenu());
//
//        Login lf = new Login();
//        Presupuesto pre = new Presupuesto();
//        Producto prod = new Producto();
//
//        cl = new CardLayout();
//
//        jp = new JPanel();
//        jp.setLayout(cl);
////        jp.add(lf.getMainPanel(),"LOGIN");
//        jp.add(pre.getMainPanel(), "PRESUPUESTO");
//        jp.add(prod.getMainPanel(),"PRODUCTO");
////        cl.addLayoutComponent(lf.getMainPanel(),"LOGIN");
//        cl.addLayoutComponent(prod.getMainPanel(), "PRODUCTO");
//
////        addVentanas(pre.getMainPanel(),"PRESUPUESTO");
////        addVentanas(prod.getMainPanel(),"PRODUCTO");
//
//        jf.setContentPane(jp);
//        jf.pack();
//        cambiarVentanas(jp,"PRODUCTO");
//        jf.setVisible(true);
    }

    static void addVentanas(JPanel jp, String tipo){
        jp.add(jp,tipo);
        cl.addLayoutComponent(jp,tipo);
    }

    static void cambiarVentanas(JPanel jp, String tipo){
        cl.show(jp,tipo);
    }

    static JMenuBar doMenu(){
        // Realizamos las ventanas del menú y sus opciones desplegables
        JMenuBar jmb = new JMenuBar();
        JMenu menuFile = new JMenu("Presupuesto");
        JMenuItem addBudget = new JMenuItem("Nuevo Presupuesto");
        JMenuItem removeBudget = new JMenuItem("Eliminar Presupuesto");
        JMenuItem openRecentBudget = new JMenuItem("Presupuestos Recientes");
        JMenu menuProd = new JMenu("Productos");
        JMenuItem openProd = new JMenuItem("Abrir Productos");
        JMenu menuUser = new JMenu("Usuario");
        JMenuItem out = new JMenuItem("Salir de la sesion");

        // Las añadimos a los Items de los menús
        menuFile.add(addBudget);
        menuFile.add(removeBudget);
        menuFile.add(openRecentBudget);
        menuProd.add(openProd);
        menuUser.add(out);

        // Añadimos los items al menú
        jmb.add(menuFile);
        jmb.add(menuProd);
        jmb.add(menuUser);

        // Realizamos eventos sobre los botones para que se lancen en un determinado momento cada vez que queramos que
        // se ejecute una acción
        addBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Añadiendo presupuesto");
                cl.show(jp,"PRESUPUESTO");
            }
        });

        removeBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eliminando presupuesto");
            }
        });

        openRecentBudget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abriendo presupuesto reciente");
            }
        });

        // PRODUCTOS
        openProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cambiando a Productos");
                cl.show(jp,"PRODUCTO");
            }
        });


        out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("¡Bye!");
                cl.show(jp,"LOGIN");
            }
        });
        return jmb;
    }
}