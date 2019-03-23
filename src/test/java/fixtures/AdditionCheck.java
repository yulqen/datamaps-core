package fixtures;

public class AdditionCheck {

    private int first;
    private int second;

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int addition() {
        return this.first + this.second;
    }
}
