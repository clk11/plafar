package GUI;
import Entitati.PlantaMedicinala;
import Logic.func;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormaAdaugarePlanta {
    private JTextField denumireField;
    private JTextField cantitateField;
    private JTextField pretField;
    private PlantaMedicinala newPlanta;
    private JDialog dialog;

    public PlantaMedicinala load() {
        // Crearea unui nou dialog pentru introducerea datelor pentru noua planta
        dialog = new JDialog((Frame) null, "Adaugare Planta", true);
        dialog.setSize(300, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Adaugarea formei pentru introducerea noii plante

        JLabel denumireLabel = new JLabel("Denumire:");
        denumireLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        denumireField = new JTextField();
        denumireField.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel cantitateLabel = new JLabel("Cantitate:");
        cantitateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        cantitateField = new JTextField();
        cantitateField.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel pretLabel = new JLabel("Pret:");
        pretLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        pretField = new JTextField();
        pretField.setFont(new Font("Arial", Font.PLAIN, 18));

        panel.add(denumireLabel);
        panel.add(denumireField);
        panel.add(cantitateLabel);
        panel.add(cantitateField);
        panel.add(pretLabel);
        panel.add(pretField);

        // Butonul pentru adaugarea unei noi plante
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 18));
        confirmButton.setForeground(Color.RED);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPlanta = func.createPlanta(denumireField.getText(),cantitateField.getText(),pretField.getText());
                if (newPlanta != null) {
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Input invalid !");
                }
            }
        });
    
        panel.add(confirmButton);
        dialog.getContentPane().add(panel);
        dialog.setVisible(true);

        // Returnarea noii plante
        return newPlanta;
    }

    // Se creeaza planta in functie de valorile introduse in campuri

}
