import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelWyboru extends JFrame {
    private JPanel JPanelK;
    private JButton wejdźButton;
    private JButton wejdźButton2;
    private JLabel wyświetlKolejkęLabel;
    private JLabel panelAdministratoraLabel;

    public PanelWyboru() {
        setContentPane(this.JPanelK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        int width = 500, height = 200;
        setSize(width, height);
        this.setLocationRelativeTo(null);

        wejdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelKolejki panelKolejki = new PanelKolejki();
                panelKolejki.setVisible(true);
            }
        });

        wejdźButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logowanie logowanie = new Logowanie();
                dispose();
            }
        });
    }
}
