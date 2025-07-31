import java.util.Observable;

public class TestAppModel extends Observable {
    private int count;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
        setChanged();
        notifyObservers();
    }

    public void decrement() {
        count--;
        setChanged();
        notifyObservers();
    }
}
