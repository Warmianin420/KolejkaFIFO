import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.awt.GridLayout;

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
        this.setLocationRelativeTo(null);

        String[] columnNames = {"ID Zamówienia", "Klient", "Produkt", "Ilość"};
        model = new DefaultTableModel(columnNames, 0);
        Tabela.setModel(model);
        wczytajKolejke(); //żeby na start dane były wczytane

        dodajButton.addActionListener(e -> dodajZamowienie());
        usuńButton.addActionListener(e -> usunZamowienie());
        zapiszButton.addActionListener(e -> zapiszKolejke());
        wczytajButton.addActionListener(e -> wczytajKolejke()); //żeby odświeżyć tabelę
        wyjdźButton.addActionListener(e -> dispose());
    }

    private void dodajZamowienie() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField customerNameField = new JTextField(10);
        JTextField productNameField = new JTextField(10);
        JTextField quantityField = new JTextField(10);

        panel.add(new JLabel("Imię klienta:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Produkt:"));
        panel.add(productNameField);
        panel.add(new JLabel("Ilość:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Dodaj zamówienie", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String customerName = customerNameField.getText();
            String productName = productNameField.getText();
            int quantity;

            try {
                quantity = Integer.parseInt(quantityField.getText());

                if (quantity <= 0) {
                    throw new IllegalArgumentException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nieprawidłowa ilość", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Ilość nie może być mniejsza lub równa zero", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (customerName.isEmpty() || productName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pola nie mogą być puste", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!czyZawieraTylkoLitery(customerName) || !czyZawieraTylkoLitery(productName)) {
                JOptionPane.showMessageDialog(this, "Imię klienta i nazwa produktu mogą zawierać tylko litery", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            int id = kolejka.getNextId();
            int id = (int) Tabela.getValueAt(model.getRowCount() - 1, 0);
            Zamowienie zamowienie = new Zamowienie(customerName, productName, quantity);
            kolejka.addOrder(zamowienie);
            model.addRow(new Object[]{id + 1, customerName, productName, quantity});

            JOptionPane.showMessageDialog(this, "Dodano zamówienie do bazy danych", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void usunZamowienie() {
        if (!kolejka.isEmpty()) {
            kolejka.poll(); //usuwa pierwszy element w kolejce
            model.removeRow(0); // Usuwa pierwszy wiersz tabeli
        }
    }

    private void zapiszKolejke() {
        try (PrintWriter plik = new PrintWriter(new FileWriter("kolejka.txt"))) {
            if (!kolejka.isEmpty()) {
                for (Zamowienie zamowienie : kolejka.getOrder()) {
                    plik.println(zamowienie.toString()); // Zakładamy, że Zamowienie ma odpowiednio zaimplementowaną metodę toString
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JOptionPane.showMessageDialog(this, "Zapisano kolejkę do bazy danych", "Success", JOptionPane.INFORMATION_MESSAGE);
        wczytajKolejke();
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

    private boolean czyZawieraTylkoLitery(String tekst) {
        return tekst.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\s]*$");
    }
}