package Logic;

import Entitati.PlantaMedicinala;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class func {
    // Functie pentru a creea planta medicinala
    public static PlantaMedicinala createPlanta(String denumire, String cantitate, String pret) {
        int parsedCantitate;
        double parsedPret;
        try {
            parsedCantitate = Integer.parseInt(cantitate);
            parsedPret = Double.parseDouble(pret);
        } catch (NumberFormatException e) {
            return null;
        }
        return new PlantaMedicinala(denumire, parsedCantitate, parsedPret);
    }

    // Function pentru a salva datele
    public static void salvareDate(List<PlantaMedicinala> plante) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Data/plante_medicinale.txt"))) {
            for (PlantaMedicinala planta : plante) {
                bw.write(planta.denumire + "," + planta.cantitate + "," + planta.pret);
                bw.newLine();
            }
            JOptionPane.showMessageDialog(null, "Datele au fost actualizate cu success !");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "A aparut o eroare la salvarea plantelor !");
        }
    }

    // Functie pentru a citi datele din fisierul text
    public static List<PlantaMedicinala> citesteDate() {
        List<PlantaMedicinala> plante = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Data/plante_medicinale.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String denumire = parts[0].trim();
                int cantitate = Integer.parseInt(parts[1].trim());
                double pret = Double.parseDouble(parts[2].trim());

                plante.add(new PlantaMedicinala(denumire, cantitate, pret));
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Eroare la fisierul text !");
        }
        return plante;
    }

    // Functie pentru verificarea credentialelor

    public static boolean verificaCredentiale(String username, String password) {
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
