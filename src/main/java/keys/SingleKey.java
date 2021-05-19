package keys;

import enums.EBasicEncryptionType;
import general.Constants;

import java.util.Random;

public class SingleKey extends Key {
    private final EBasicEncryptionType encryptionType;
    private final int value;

    public SingleKey(EBasicEncryptionType encryptionType) {
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
        return encryptionType.getTypeName();
    }
}
