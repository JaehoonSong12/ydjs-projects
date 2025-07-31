/**
INSTRUCTIONS: 
    This is a Java Swing application that 

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              TestApp*.java
     java           -cp out     TestApp
     rm -rf out/
     












 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// executable-shell (public <--> filename.java)
public class TestApp {
    static void run() {
        SwingUtilities.invokeLater(() -> {
            TestAppModel model = new TestAppModel();
            TestAppView view = new TestAppView();
            TestAppController controller = new TestAppController(model, view);
            controller.init();
        });
        return;
    }

    static void run2() {
        Main guiApp = new Main();
        return;
    }

    
    // entry point
    public static void main(String[] args) {
        // TestApp.run();
        // TestApp.run2();
        TestApp.example();
        return;
    }

    static void example() {
        A a = new A();
        a.print();
    }
}



class A {
    String s;

    A(String newS) {
        s = newS;
    }

    public void print() {
        System.out.print(s);
    }
}



class Main {
    private int count = 0;
    private JLabel countLabel;

    public Main() { // constructor
        JFrame frame = new JFrame("Simple TestApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 260);
        frame.setLayout(new BorderLayout());
        /**
         * Abstraction: someone else this it for us (no need to know)
         */

        countLabel = new JLabel("0", SwingConstants.CENTER);
        frame.add(countLabel, BorderLayout.CENTER);

        JButton incButton = new JButton("+");
        JButton decButton = new JButton("-");
        JPanel panel = new JPanel();
        panel.add(decButton);
        panel.add(incButton);
        frame.add(panel, BorderLayout.SOUTH);

        incButton.addActionListener(e -> updateCount(1));
        decButton.addActionListener(e -> updateCount(-1));

        frame.setVisible(true);
    }

    private void updateCount(int delta) {
        count += delta;
        countLabel.setText(String.valueOf(count));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}



