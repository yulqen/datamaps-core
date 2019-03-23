package fixtures;

public class MultiplyCheck {

    private int first;
    private int second;

    public void setFirst(int first) {
        this.first = first;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int multiply() {
        return this.first * this.second;
    }
}
