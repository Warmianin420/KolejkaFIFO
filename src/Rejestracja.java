import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Rejestracja extends JFrame {
    private JPanel JPanelR;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField loginField;
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JPasswordField passwordField;
    private JButton sendButton;
    private JTextField emailField;
    private JLabel emailLabel;
    private JButton cancelButton;
    private JButton backButton;
    private JLabel registerLabel;

    public Rejestracja() {
        super("Zarejestruj się do aplikacji");
        setContentPane(this.JPanelR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 500, height = 400;
        setSize(width, height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logowanie logowanie = new Logowanie();
                dispose();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String email = emailField.getText();

                if (login.isEmpty() || password.isEmpty() || email.isEmpty()) { //sprawdzam czy pola są puste
                    JOptionPane.showMessageDialog(Rejestracja.this,
                            "Uzupełnij wszystkie pola!",
                            "Spróbuj ponownie",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else { //jak pola nie są puste, to sprawdzam za pomocą funkcji, czy taki użytkownik w bazie istnieje
                    if (sprawdz(login, email)) { //jak już taki login i e-mail są (hasło może się powtarzać, dla użytkowników), to wypisuje komunikat
                        JOptionPane.showMessageDialog(Rejestracja.this,
                                "Nie można zarejestrować użytkownika, bo taki login lub e-mail istnieje już w bazie!", "Błąd",
                                JOptionPane.ERROR_MESSAGE);
                    } else { //jak pola nie są puste, i login, e-mail nie są zajęte, to zapisuje dane do bazy
                        zapiszDane(login, password, email);
                    }
                }
            }
        });
    }

    public boolean sprawdz(String login, String email) {
        File plikTekstowy = new File("uzytkownicy.txt");
        if (plikTekstowy.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(plikTekstowy))) {
                String linijka;
                while ((linijka = reader.readLine()) != null) {
                    String[] wartosci = linijka.split(",");
                    if (wartosci[0].equals(login) || wartosci[2].equals(email)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public void zapiszDane(String login, String password, String email) {
        try (FileWriter fw = new FileWriter("uzytkownicy.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(login + "," + password + "," + email);
            JOptionPane.showMessageDialog(Rejestracja.this,
                    "Rejestracja powiodła się!", "Sukces",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

