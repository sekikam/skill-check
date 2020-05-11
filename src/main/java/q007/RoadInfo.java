package q007;

public class RoadInfo {
    int x;
    int y;
    String value;
    boolean isPassed;

    public int getX() {
        return x;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public int getY() {
        return y;
    }

    public String getValue() {
        return value;
    }

    public RoadInfo(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.isPassed = false;

    }
}
