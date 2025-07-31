// btn - JButton
// chk - JCheckBox
// clr - JColorChooser
// cmb - JComboBox
// ico - JDesktopIcon
// edt - JEditorPane
// fch - JFileChooser
// ifr - JInternalFrame
// lbl - JLabel
// lyp - JLayeredPane
// lst - JList
// mnu - JMenuBar
// mni - JMenuItem
// opt - JOptionPane
// pnl - JPanel
// pmn - JPopupMenu
// prg - JProgressBar
// rad - JRadioButton
// rot - JRootPane
// scb - JScollBar
// scr - JScrollPane
// spr - JSeparator
// sld - JSlider
// spn - JSpinner
// spl - JSplitPane
// tab - JTabbedPaneJTable
// tbl - JTable
// tbh - JTableHeader
// txa - JTextArea
// txt - JText
// txf - JTextField

package core;


import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;






/**
 * The view constructs and exposes UI components.
 */
public class BackendView extends JPanel {
    public static final String PLACEHOLDER = "Paste URL here";

    // Contant in Java, static == "since the program starts" && final == "permanent for the program lifetime"
    public static final String TITLE = "MusicDL Backend Program";
    public static final int SCREEN__WIDTH = 680;
    public static final int SCREEN_HEIGHT = 480;

    private JLabel lblLogo;
    private JLabel lblTitle;
    private JTextField txfUrl;
    public JTextField getTxfUrl() { return this.txfUrl; }
    private JButton btnGo;
    public JButton getBtnGo() { return this.btnGo; }
    private JLabel lblTerms;
    public JLabel getLblTerms() { return this.lblTerms; }

    public BackendView() {
        // Use a single GridLayout: 3 rows, 1 column
        setLayout(new GridLayout(3, 1, 0, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top row: logo + title (horizontal)
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        ImageIcon raw = new ImageIcon(getClass().getResource("/logo.png"));
        Image img = raw.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(img));
        lblTitle = new JLabel("Title");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 24f));
        top.add(lblLogo);
        top.add(lblTitle);
        add(top);

        // Middle row: URL field + GO button (horizontal)
        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        txfUrl = new JTextField(25);
        txfUrl.setForeground(Color.GRAY);
        txfUrl.setText(PLACEHOLDER);
        txfUrl.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txfUrl.getText().equals(PLACEHOLDER)) {
                    txfUrl.setText("");
                    txfUrl.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txfUrl.getText().isEmpty()) {
                    txfUrl.setText(PLACEHOLDER);
                    txfUrl.setForeground(Color.GRAY);
                }
            }
        });
        btnGo = new JButton("GO");
        middle.add(txfUrl);
        middle.add(btnGo);
        add(middle);

        // Bottom row: terms label (horizontal center)
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lblTerms = new JLabel("By using our service you are accepting our Terms of Use.");
        bottom.add(lblTerms);
        add(bottom);
    }
}