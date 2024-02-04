import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logowanie extends JFrame {
    private JPanel JPanel1;
    private JPanel JPanel2;
    private JPanel JPanel3;
    private JPanel JPanel4;
    private JLabel logowanieLabel;
    private JButton sendButton;
    private JButton cancelButton;
    private JLabel loginLabel;
    private JTextField loginField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private String user = "admin", password = "admin";

    public Logowanie() {
        setContentPane(this.JPanel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 500, height = 200;
        setSize(width, height);
        this.setLocationRelativeTo(null);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                            String userLogin = loginField.getText();
                            String userPassword = new String(passwordField.getPassword());

                            if (!userLogin.isEmpty() && !userPassword.isEmpty()) {
                                if (userLogin.equals(user) && userPassword.equals(password)) {
                                    dispose();
                                    new Kolejka().setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(Logowanie.this,
                                            "Wprowadzono błędne dane, spróbuj ponownie!", "Błąd",
                                            JOptionPane.ERROR_MESSAGE);

                                    loginField.setText("");
                                    passwordField.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(Logowanie.this,
                            "Pola nie mogą być puste!", "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}