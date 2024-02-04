import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kolejka extends JFrame {
    private JPanel JPanelK;
    private JTextField towarField;
    private JLabel towarLabel;
    private JLabel iloscField;
    private JTextField textField1;
    private JButton sendButton;
    private JButton cancelButton;

    public Kolejka() {
        super("Kolejka FIFO");
        setContentPane(this.JPanelK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        int width = 500, height = 200;
        setSize(width, height);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

