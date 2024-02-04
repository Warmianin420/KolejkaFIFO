import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rejestracja extends  JFrame {
    private JPanel JPanelR;
    private JLabel loginLabel;
    private JLabel imieLabel;
    private JLabel nazwiskoLabel;
    private JLabel passwordLabel;
    private JLabel repeatPasswordLabel;
    private JTextField loginField;
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JButton sendButton;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField adressField;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel adressLabel;
    private JButton cancelButton;
    private JButton backButton;
    private JLabel registerLabel;

    public Rejestracja() {
        super("Zarejestruj się do aplikacji");
        setContentPane(this.JPanelR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
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
    }
    public void register() {
        String login = loginField.getText();
        String name = imieField.getText();
        String surname = nazwiskoField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = adressField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(repeatPasswordField.getPassword());

        if (login.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Uzupełnij wszystkie pola!",
                    "Spróbuj ponownie",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}

