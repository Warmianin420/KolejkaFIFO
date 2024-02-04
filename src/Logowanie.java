import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logowanie extends JFrame {
    private JPanel JPanel1;
    private JPanel JPanel2;
    private JPanel JPanel3;
    private JPanel JPanel4;
    private JLabel logowanieKlientaLabel;
    private JButton sendButton;
    private JButton cancelButton;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel noAccountLabel;
    private String user = "admin", password = "admin";

    public Logowanie() {
        setContentPane(this.JPanel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 500, height = 200;
        setSize(width, height);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userLogin = passwordField.getText();
                String userPassword = new String(passwordField.getPassword());
                if (userLogin.equals(user) && (userPassword.equals(password))) {
                    dispose();
                    new Kolejka().setVisible(true);
                    System.out.println("Zalogowano do systemu");
                } else {
                    System.out.println("Wprowadzono błędne dane. Spróbuj ponownie.");
                    passwordField.setText("");
                    passwordField.setText("");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rejestracja().setVisible(true);
                dispose();
            }
        });
    }
}