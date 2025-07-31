import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;

public class TestAppController {
    private TestAppModel model;
    private TestAppView view;

    public TestAppController(TestAppModel model, TestAppView view) {
        this.model = model;
        this.view = view;

        // Register view as observer
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                view.setCount(model.getCount());
            }
        });

        // Wire buttons
        view.addIncrementListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.increment();
            }
        });
        view.addDecrementListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.decrement();
            }
        });
    }

    public void init() {
        view.setVisible(true);
    }
}