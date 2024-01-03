import Componente.FormaLogare;
import javax.swing.SwingUtilities;

class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormaLogare().setVisible(true);
            }
        });
    }
}