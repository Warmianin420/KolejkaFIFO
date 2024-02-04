import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Kolejka extends JFrame {
    private JPanel JPanelK;
    private JButton cancelButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel zamówieniaLabel;
    private JPanel JPanel1;
    private JPanel JPanel2;
    private JPanel JPanel3;
    private JScrollPane JScrollPane1;
    private JTable Tabela;
    private JButton dodajButton;
    private JButton zapiszButton;
    private JButton usuńButton;
    private JButton wczytajButton;
    private JButton wyjdźButton;
    private DefaultTableModel model;

    private OrderQueue kolejka = new OrderQueue();

    public Kolejka() {
        super("Kolejka FIFO");
        setContentPane(this.JPanelK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 800, height = 600;
        setSize(width, height);

        String[] columnNames = {"ID Zamówienia", "Klient", "Produkt", "Ilość"};
        model = new DefaultTableModel(columnNames, 0);
        Tabela.setModel(model);

        dodajButton.addActionListener(e -> dodajZamowienie());
        usuńButton.addActionListener(e -> usunZamowienie());
        zapiszButton.addActionListener(e -> zapiszKolejke());
        wczytajButton.addActionListener(e -> wczytajKolejke());
        wyjdźButton.addActionListener(e -> dispose());
    }

    private void dodajZamowienie() {
        String customerName = JOptionPane.showInputDialog(this, "Podaj imię klienta:");
        String productName = JOptionPane.showInputDialog(this, "Podaj nazwę produktu:");
        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Podaj ilość:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowa ilość", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Zamowienie zamowienie = new Zamowienie(customerName, productName, quantity);
        kolejka.addOrder(zamowienie);
        model.addRow(new Object[]{zamowienie.getId(), customerName, productName, quantity});
    }


    private void usunZamowienie() {
        if (!kolejka.isEmpty()) {
            kolejka.poll(); //usuwa pierwszy element w kolejce
            model.removeRow(0); // Usuwa pierwszy wiersz tabeli
        }
    }

    private void zapiszKolejke() {
        try (PrintWriter out = new PrintWriter(new FileWriter("kolejka.txt"))) {
            for (Zamowienie zamowienie : kolejka.getOrder()) {
                out.println(zamowienie.toString()); // Zakładamy, że Zamowienie ma odpowiednio zaimplementowaną metodę toString
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajKolejke() {
        try (BufferedReader br = new BufferedReader(new FileReader("kolejka.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    String customerName = parts[1].trim();
                    String productName = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());

                    Zamowienie zamowienie = new Zamowienie(customerName, productName, quantity);
                    kolejka.addOrder(zamowienie);
                    model.addRow(new Object[]{zamowienie.getId(), customerName, productName, quantity});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Błąd odczytu pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy format danych w pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

}
