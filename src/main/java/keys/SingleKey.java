package keys;

import general.Constants;

import java.util.Random;

public class SingleKey extends Key {
    // TODO: 18/05/2021 Make enum
    private final String encryptionType;
    private int value;

    public SingleKey(String encryptionType) {
        this.encryptionType = encryptionType;
        value = new Random().nextInt(Constants.MAX_ASCII_VALUE) + 1;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int getKeyStrength() {
        int keyValue = value;
        int strength = 1;
        while (keyValue / 10 >= 10) {
            strength++;
            keyValue /= 10;
        }
        return strength;
    }

    @Override
    public String getType() {
        return encryptionType;
    }

    @Override
    public void getNextKey() {
        value++;
        if (value == Constants.MAX_ASCII_VALUE + 1)
            value = 0;
    }
}
