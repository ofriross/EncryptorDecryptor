package keys;

public class DoubleKey extends Key {
    private Key double1;

    private Key double2;

    public DoubleKey(Key double1, Key double2) {
        this.double1 = double1;
        this.double2 = double2;
    }

    public Key getDouble1() {
        return double1;
    }

    public Key getDouble2() {
        return double2;
    }

    public String toString() {
        return double1 + "," + double2;
    }

    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    public int getKeyStrength() {
        return double1.getKeyStrength() + double2.getKeyStrength();
    }

    public String getType() {
        return "Double of " + double1.getType();
    }

    public void getNextKey() {
        double1.getNextKey();
    }
}
