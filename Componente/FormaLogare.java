package Componente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FormaLogare extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public FormaLogare() {
        setTitle("Login Form");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponente();
    }

    private void initComponente() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel usernameLabel = new JLabel("Nume utilizator :");
        usernameField = new JTextField(40);
        usernameField.setPreferredSize(new Dimension(400, 30));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Parola :");
        passwordField = new JPasswordField(40);
        passwordField.setPreferredSize(new Dimension(400, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Logare");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                if (verificaCredentiale(username, password)) {
                    JOptionPane.showMessageDialog(FormaLogare.this, "Ai fost logat cu success!");
                    FormaPaginaPrincipala form = new FormaPaginaPrincipala();
                    form.load();
                } else {
                    JOptionPane.showMessageDialog(FormaLogare.this, "Nume utilizator sau parola invalide!");
                }
            }
        });

        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPanel.add(loginButton);
        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(buttonPanel);

        add(panel);
    }

    private boolean verificaCredentiale(String username, String password) {
        boolean verifyCreds = !username.trim().isEmpty() && !password.trim().isEmpty();
        if (verifyCreds) {
            try (BufferedReader br = new BufferedReader(new FileReader("Data/users.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].trim().equals(username.trim()) && parts[1].trim().equals(password.trim()))
                        return true;
                }
            } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return false;
        } else
            return false;
    }
}
