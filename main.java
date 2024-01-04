import javax.swing.SwingUtilities;
import GUI.FormaLogare;
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