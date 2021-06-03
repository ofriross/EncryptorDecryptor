package encryptionAlgorithms.basicEncryptions;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EBasicEncryptionType;
import general.Constants;
import keys.Key;
import keys.SingleKey;

import java.util.ArrayList;

public abstract class BasicEncryption implements IEncryptionAlgorithm {
    protected SingleKey singleKey;

    private String encryptDecrypt(String data, int key, EActionEncryptOrDecrypt action) {
        StringBuilder encryption = new StringBuilder(data);
        ArrayList<Integer> encryptionChars = new ArrayList<>();
        for (int index = 0; index < data.length(); index++) {
            encryptionChars.add(computeChar(data.charAt(index), key, action));
//            currentChar = getNumberToRange(currentChar, Constants.MAX_ASCII_VALUE + 1);
//            encryption.setCharAt(index, (char) currentChar);
        }
        encryptionChars.forEach((n) -> {
            getNumberToRange(n, Constants.MAX_ASCII_VALUE + 1);
        });
        int i = 0;
        encryptionChars.forEach((n) -> {
            encryption.setCharAt(i, (char) n.intValue());
        });
        System.out.println("------------------------------"+encryption);
        return encryption.toString();
    }

    private int getNumberToRange(int number, int maxRange) {
        number %= maxRange;
        if (number < 0)
            number += maxRange;
        return number;
    }

    public String getType() {
        return singleKey.getType();
    }

    @Override
    public <T extends Key> String performEncryption(String data, T key) {
        int keyValue = ((SingleKey) key).getValue();
        return encryptDecrypt(data, keyValue, EActionEncryptOrDecrypt.ENCRYPT);
    }

    @Override
    public String performDecryption(String data, ArrayList<Integer> keys) {
        String decryptedData = null;
        for (Integer key : keys) {
            decryptedData = encryptDecrypt(decryptedData != null ? decryptedData : data, key, EActionEncryptOrDecrypt.DECRYPT);
        }
        return decryptedData;
    }

    public Key initSingleKey(EBasicEncryptionType encryptionType) {
        this.singleKey = new SingleKey(encryptionType);
        return singleKey;
    }

    public abstract int computeChar(int currentChar, int key, EActionEncryptOrDecrypt EActionEncryptOrDecrypt);

    @Override
    public int getKeyStrength() {
        return singleKey.getKeyStrength();
    }
}
