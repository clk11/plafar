package GUI;

import javax.swing.*;
import java.awt.*;

public class FormaInformatiiAutor {
    //Afisarea informatiilor despre autor
    public static void load() {
        JFrame frame = new JFrame("Informatii Autor");
        JPanel panel = new JPanel();
        JLabel textLabel = new JLabel(
                "<html><div style='text-align: center;'><h1>Detalii autor</h1><p>Numele meu este Trasca Robert-Valentin și sunt autorul aplicației \"Plafar\".</p><p>Data: 24/11/2023</p></div></html>");
        Font font = textLabel.getFont();
        textLabel.setFont(new Font(font.getName(), Font.BOLD, (int) (font.getSize() * 1.9)));
        panel.add(textLabel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
