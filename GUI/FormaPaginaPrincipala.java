package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.FormaAfisarePlante;
import GUI.FormaInformatiiAutor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormaPaginaPrincipala {
    public static void load() {
        JFrame frame = new JFrame("Plafar_Trasca Robert-Valentin");

        // Setarea dimensiunii si pozitiei JFrame-ului
        frame.setSize(700, 400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // Inchidere fereastra
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crearea de butoane pentru afisarea plantelor si informatii despre autor
        JButton buttonA = new JButton("Afisare plante");
        JButton buttonB = new JButton("Informatii autor");
        Dimension buttonSize = new Dimension(300, 300);
        buttonA.setPreferredSize(buttonSize);
        buttonB.setPreferredSize(buttonSize);

        // Setarea dimensiunilor fontului pentru butoane
        int fontSize = 20;
        Font buttonFont = new Font("Arial", Font.PLAIN, fontSize);
        buttonA.setFont(buttonFont);
        buttonB.setFont(buttonFont);

        // Adaugarea actiunilor pentru butoane
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormaAfisarePlante formaAfisarePlante = new FormaAfisarePlante();
                FormaAfisarePlante.load();
            }
        });
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormaInformatiiAutor formaInfoAutor = new FormaInformatiiAutor();
                formaInfoAutor.load();
            }
        });

        // Adaugarea butoanelor la in frame
        frame.getContentPane().add(buttonA);
        frame.getContentPane().add(buttonB);

        // Setarea layout-ului pentru centrarea butoanelor
        java.awt.FlowLayout layout = new java.awt.FlowLayout();
        layout.setAlignment(java.awt.FlowLayout.CENTER);
        frame.setLayout(layout);

        // Afisare frame
        frame.setVisible(true);
    }
}
