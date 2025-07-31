import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestAppView extends JFrame {
    private JButton incButton = new JButton("+");
    private JButton decButton = new JButton("-");
    private JLabel countLabel = new JLabel("0", SwingConstants.CENTER);

    public TestAppView() {
        setTitle("MVC TestApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 150);
        setLayout(new BorderLayout());

        add(countLabel, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(decButton);
        panel.add(incButton);
        add(panel, BorderLayout.SOUTH);
    }

    public void setCount(int count) {
        countLabel.setText(String.valueOf(count));
    }

    public void addIncrementListener(ActionListener al) {
        incButton.addActionListener(al);
    }

    public void addDecrementListener(ActionListener al) {
        decButton.addActionListener(al);
    }
}
