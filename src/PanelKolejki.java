import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelKolejki extends JFrame {
    private JPanel PanelK;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JScrollPane JScrollPane1;
    private JTable Tabela;
    private JLabel zamówieniaLabel;
    private JButton wyjdźButton;
    private DefaultTableModel model;

    private OrderQueue kolejka = new OrderQueue();

    public PanelKolejki() {
        super("Kolejka FIFO");
        setContentPane(this.PanelK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 800, height = 600;
        setSize(width, height);
        this.setLocationRelativeTo(null);

        String[] columnNames = {"ID Zamówienia", "Klient", "Produkt", "Ilość"};
        model = new DefaultTableModel(columnNames, 0);
        Tabela.setModel(model);
        wczytajKolejke(); //żeby na start dane były wczytane

        wyjdźButton.addActionListener(e -> dispose());
    }

    private void wczytajKolejke() {
        try (BufferedReader br = new BufferedReader(new FileReader("kolejka.txt"))) {
            String line;
            model.setRowCount(0); //żeby dane w tabeli nie duplikowały się
            kolejka.clear(); //żeby dane w tabeli nie duplikowały się
            int id = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
//                    int id = Integer.parseInt(parts[0].trim());
                    String customerName = parts[0].trim();
                    String productName = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());

                    Zamowienie zamowienie = new Zamowienie(customerName, productName, quantity);

                    kolejka.addOrder(zamowienie);
                    model.addRow(new Object[]{id, customerName, productName, quantity});
                    id++;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Błąd odczytu pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy format danych w pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
