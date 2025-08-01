/**
INSTRUCTIONS: 
    This is a Java Swing application that demonstrates the Factory Design 
    Pattern within an MVC architecture.
    
    The application provides a simple UI where users can switch between 
    different views (List View, Detail View, Form View).
    The Factory Pattern is used to instantiate the view objects, ensuring 
    separation of concerns and flexibility.
    
    Factory Design Principle:
     - Encapsulates object creation, providing a centralized method to create and manage View instances.
     - Improves maintainability by decoupling object instantiation from client classes (Controller).
     - Enhances scalability, making it easy to add new views without modifying the existing code structure.

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              SwingApp*.java
     java           -cp out     SwingApp
     rm -rf out/
     












 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;






// Model representing form data
class FormModel {
    private static FormModel instance;

    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


    // Private constructor to prevent instantiation
    private FormModel() {}
    // Public method to provide access to the single instance (Singleton Pattern)
    public static FormModel getInstance() {
        if (instance == null) {
            synchronized (FormModel.class) { // Double-Checked Locking: Ensures thread safety while minimizing synchronization overhead
                if (instance == null) {
                    instance = new FormModel();
                }
            }
        }
        return instance;
    }
}








// View interface with a method for the root panel with UI components and a title constant getter.
interface View {
    JPanel getRoot(); // Returns the root JPanel containing UI elements
    String getTitle();
}
/**
 * `interface`: weak relationship or inheritance, just for common behavior to `implements`.
* `class`: strong relationship or inheritance, for blueprinting most of the parts to `extends`.
*/



// --- Views ---
// List View: displays a simple list of items.
class ViewList implements View {
    public static final String TITLE = "List View";
    @Override
    public String getTitle() {
        return TITLE;
    }

    private JPanel rootPanel;
    @Override
    public JPanel getRoot() {
        return rootPanel;
    }

    public ViewList() {
        // Layout (HTML like) Operation
        String[] data = {"Item 1", "Item 2", "Item 3"};
        JList<String> list = new JList<>(data);

        // Styling (CSS like) Operation
        // ....
        // ....

        // root components for Controller Logic (like system-objects in JavaScript)
        rootPanel = new JPanel(new BorderLayout());

        // Build the ViewNav (UI components). [Top]
        ViewNav viewNav = ViewFactory.getView("NAV");
        new NavController(viewNav);

        rootPanel.add(viewNav.getRoot(), BorderLayout.NORTH);
        rootPanel.add(new JScrollPane(list), BorderLayout.CENTER);
    }
}

// Detail View: shows submitted form data from the model.
class ViewDetail implements View {
    public static final String TITLE = "Detail View";
    @Override
    public String getTitle() {
        return TITLE;
    }

    private JPanel rootPanel;
    @Override
    public JPanel getRoot() {
        return rootPanel;
    }

    public ViewDetail() {
        // Controller Logic (like JavaScript)
        FormModel model = FormModel.getInstance();
        StringBuilder text = new StringBuilder("<html><h1>Detail View</h1>");
        if (model.getName() != null && model.getEmail() != null) {
            text.append("<p>Name: ").append(model.getName()).append("</p>");
            text.append("<p>Email: ").append(model.getEmail()).append("</p>");
        } else {
            text.append("<p>No form data submitted.</p>");
        }
        text.append("</html>");
        System.out.println(text);

        // Layout (HTML like) Operation
        JLabel label = new JLabel(text.toString());

        // Styling (CSS like) Operation
        // ....
        // ....

        // root components for Controller Logic (like system-objects in JavaScript)
        rootPanel = new JPanel(new BorderLayout());

        // Build the ViewNav (UI components). [Top]
        ViewNav viewNav = ViewFactory.getView("NAV");
        new NavController(viewNav);

        rootPanel.add(viewNav.getRoot(), BorderLayout.NORTH);
        rootPanel.add(label, BorderLayout.CENTER);
    }
}


// Form View: provides text fields for user input and a submit button.
// Note that the view only builds the UI; its controller logic is in FormController.
class ViewForm implements View {
    public static final String TITLE = "Form View";
    @Override
    public String getTitle() {
        return TITLE;
    }

    private JPanel rootPanel;
    @Override
    public JPanel getRoot() {
        return rootPanel;
    }

    private JPanel formPanel;

    private JTextField nameField;
    public JTextField getNameField() { return nameField; } // getter-only (immutable)

    private JTextField emailField;
    public JTextField getEmailField() { return emailField; }

    private JButton submitButton;
    public JButton getSubmitButton() { return submitButton; }

    public ViewForm() {
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        nameField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(emailField, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);

        
        // root components for Controller Logic (like system-objects in JavaScript)
        rootPanel = new JPanel(new BorderLayout());

        // Build the ViewNav (UI components). [Top]
        ViewNav viewNav = ViewFactory.getView("NAV");
        new NavController(viewNav);

        rootPanel.add(viewNav.getRoot(), BorderLayout.NORTH);
        rootPanel.add(formPanel, BorderLayout.CENTER);
    }
}
// --- Controllers for individual views ---

// Controller for FormView that handles submit button actions.
class FormController {
    private ViewForm view;

    public FormController(ViewForm view) {
        this.view = view;
        init();
    }

    private void init() {
        view.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormModel model = FormModel.getInstance();
                // Update the model with data from the view
                model.setName(view.getNameField().getText());
                model.setEmail(view.getEmailField().getText());
                JOptionPane.showMessageDialog(view.getRoot(), "Form submitted!");
            }
        });
    }
}




// --- ViewNav ---

// This class is responsible for assembling the main UI components (navigation and content).
// It does not extend JFrame; instead, it builds a root panel to be added to the stage.

class ViewNav implements View {
    public static final String TITLE = "N/A";
    @Override
    public String getTitle() {
        return TITLE;
    }

    private JPanel rootPanel;
    @Override
    public JPanel getRoot() {
        return rootPanel;
    }

    // Expose navigation buttons.
    private JButton btnList;
    public JButton getListButton() { return btnList; }

    private JButton btnDetail;
    public JButton getDetailButton() { return btnDetail; }

    private JButton btnForm;
    public JButton getFormButton() { return btnForm; }

    

    public ViewNav() {
    // Create navigation panel. (Top)
    btnList = new JButton("List View");
    btnDetail = new JButton("Detail View");
    btnForm = new JButton("Form View");

    rootPanel = new JPanel();
    rootPanel.add(btnList);
    rootPanel.add(btnDetail);
    rootPanel.add(btnForm);
    }
}

// --- NavController ---
// Handles navigation events and view switching.
class NavController {
    private ViewNav viewNav;

    public NavController(ViewNav viewNav) {
        this.viewNav = viewNav;
        init();
    }

    // Attach action listeners to navigation buttons.
    private void init() {
        viewNav.getListButton().addActionListener(e -> SwingAppWithFactory.setView("LIST"));
        viewNav.getDetailButton().addActionListener(e -> SwingAppWithFactory.setView("DETAIL"));
        viewNav.getFormButton().addActionListener(e -> SwingAppWithFactory.setView("FORM"));
    }
}


// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------
// ----------------------------- Factory Pattern -----------------------------
// ---------------------------------------------------------------------------
// ---------------------------------------------------------------------------

// ViewFactory class for creating view objects
// Factory for creating views. It accepts the model for views that need it.
class ViewFactory {
    // public static View getView(String viewType, FormModel model) {
    //     String product = viewType.toUpperCase();
    //     if (product.equals("LIST")) return new ViewList();
    //     if (product.equals("DETAIL")) return new ViewDetail(model);
    //     if (product.equals("FORM")) return new ViewForm();
    //     if (product.equals("NAV")) return new ViewNav();
    //     throw new IllegalArgumentException("Unknown view type: " + viewType);
    // }
    
    @SuppressWarnings("unchecked")
    public static <T extends View> T getView(String viewType) {
        String product = viewType.toUpperCase();
        if (product.equals("LIST")) return (T) new ViewList();
        if (product.equals("DETAIL")) return (T) new ViewDetail();
        if (product.equals("FORM")) return (T) new ViewForm();
        if (product.equals("NAV")) return (T) new ViewNav();
        throw new IllegalArgumentException("Unknown view type: " + viewType);
    }
}




































// --- Main Application Entry Point ---

// Note: This class must follow the given convention with a static JFrame stage.
public class SwingAppWithFactory {
    private static JFrame stage;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingAppWithFactory::start);
    }

    public static void start() {
        // Create the main JFrame.
        stage = new JFrame();
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage.setSize(400, 300);
        stage.setVisible(true);

        // Set a default view.
        SwingAppWithFactory.setView("form");
    }

    // Switch the content view based on viewType.
    public static void setView(String viewType) {
        View view = ViewFactory.getView(viewType);

        // For views requiring dedicated controller logic, attach controllers.
        if (view instanceof ViewForm) { new FormController((ViewForm) view); }

        // Update the content panel and the stage title.
        stage.setContentPane(view.getRoot()); // replace the entire content pane of the stage
        stage.setTitle("Swing MVC - " + view.getTitle());
        stage.revalidate();
        stage.repaint();
    }
}