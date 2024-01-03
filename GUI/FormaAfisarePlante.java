package GUI;

import Backend.func;
import java.io.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import Entitati.PlantaMedicinala;

public class FormaAfisarePlante {
    public static List<PlantaMedicinala> plante = new ArrayList<>();

    public FormaAfisarePlante() {
        plante.addAll(func.citesteDate());
    }

    public static void load() {
        // Crearea unui nou JFrame pentru lista de plante
        JFrame frame = new JFrame("Lista plante");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // Definirea numelor de coloane pentru tabel
        String[] columnNames = { "Cantitate", "Denumire", "Pret", "Adauga" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Adăugarea datelor plantelor în tabel
        for (PlantaMedicinala planta : plante) {
            Object[] rowData = { planta.cantitate, planta.denumire, planta.pret, "Vinde" };
            tableModel.addRow(rowData);
        }

        // Crearea tabelului și setarea aspectului acestuia
        JTable table = new JTable(tableModel);
        Font tableFont = table.getFont().deriveFont(table.getFont().getSize() * 1.5f);
        table.setFont(tableFont);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), table, tableModel));

        // Adăugarea unui ascultător pentru modificările din tabel
        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column != -1) {
                    Object data = tableModel.getValueAt(row, column);
                    switch (column) {
                        case 0:
                            plante.get(row).cantitate = Integer.parseInt(data.toString());
                            break;
                        case 1:
                            plante.get(row).denumire = data.toString();
                            break;
                        case 2:
                            plante.get(row).pret = Double.parseDouble(data.toString());
                            break;
                        case 3:
                            break;
                    }
                }
            }
        });

        // Adăugarea tabelului într-un panou cu scroll
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Adăugarea unei etichete pentru indicarea stării listei de plante
        JLabel label = new JLabel("Plante afisate");
        label.setFont(new Font("Arial", Font.PLAIN, 10));
        frame.add(label, BorderLayout.NORTH);

        // ======================================================================ButtonC

        // Crearea unui buton pentru adăugarea unei noi plante
        JButton buttonC = new JButton("Adauga planta");
        int fontSize = 20;
        Font buttonFont = new Font("Arial", Font.PLAIN, fontSize);
        buttonC.setFont(buttonFont);

        // Adăugarea unui ascultător pentru butonul de adăugare a plantei
        buttonC.addActionListener(e -> {
            FormaAdaugarePlanta formaAdaugarePlanta = new FormaAdaugarePlanta();
            PlantaMedicinala newPlanta = formaAdaugarePlanta.load();
            if (newPlanta != null) {
                plante.add(newPlanta);
                tableModel.addRow(new Object[] { newPlanta.cantitate, newPlanta.denumire, newPlanta.pret, "Vinde" });
            }
        });

        // ======================================================================ButtonSave
        // Crearea unui buton pentru salvarea datelor in fisierul text

        JButton buttonSave = new JButton("Salvare date");
        buttonSave.setForeground(Color.black);
        buttonSave.setFont(buttonSave.getFont().deriveFont(Font.BOLD, buttonSave.getFont().getSize() + 2));
        buttonSave.setBackground(new Color(135, 206, 250));
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                func.salvareDate(plante);
            }
        });

        // Setam pozitiile butoanelor pe frame
        JPanel southPanel = new JPanel(new FlowLayout());
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(buttonC);
        northPanel.add(buttonSave);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.add(northPanel, BorderLayout.NORTH);

        // Se afiseaza frame-ul
        frame.setVisible(true);
    }

    // Clasa pentru desenarea tabelului

    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Clasă internă pentru gestionarea acțiunilor butoanelor din tabel
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;

        public ButtonEditor(JCheckBox checkBox, JTable table, DefaultTableModel tableModel) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = table.getSelectedRow();
                    int currentCantitate = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    if (currentCantitate >= 1) {
                        tableModel.setValueAt(currentCantitate - 1, selectedRow, 0);
                    }
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }
    }
}
