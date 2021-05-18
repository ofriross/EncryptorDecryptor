package encryptionAlgorithms.basicEncryptions;

import encryptionAlgorithms.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import general.Constants;
import keys.Key;
import keys.SingleKey;

import java.util.ArrayList;

public abstract class BasicEncryption implements IEncryptionAlgorithm {
    protected SingleKey singleKey;

    private static String encryptDecrypt(String data, int key, BasicEncryption basicEncryption, EActionEncryptOrDecrypt action) {
        StringBuilder encryption = new StringBuilder(data);
        for (int index = 0; index < data.length(); index++) {
            int currentChar = basicEncryption.computeChar(data.charAt(index), key, action);
            while (currentChar < 0)
                currentChar += (Constants.MAX_ASCII_VALUE + 1);
            currentChar %= (Constants.MAX_ASCII_VALUE + 1);
            encryption.setCharAt(index, (char) currentChar);
        }
        return encryption.toString();
    }

    public String getType() {
        return singleKey.getType();
    }

    @Override
    public <T extends Key> String performEncryption(String data, T key) {
        int keyValue = ((SingleKey) key).getValue();
        return encryptDecrypt(data, keyValue, this, EActionEncryptOrDecrypt.encrypt);
    }

    @Override
    public String performDecryption(String data, ArrayList<Integer> keys) {
        String decryptedData = null;
        for (Integer key : keys) {
            decryptedData = encryptDecrypt(decryptedData != null ? decryptedData : data, key, this, EActionEncryptOrDecrypt.decrypt);
        }
        return decryptedData;
    }

    public Key initSingleKey(String encryptionType) {
        this.singleKey = new SingleKey(encryptionType);
        return singleKey;
    }

    public abstract int computeChar(int currentChar, int key, EActionEncryptOrDecrypt EActionEncryptOrDecrypt);

    @Override
    public int getKeyStrength() {
        return singleKey.getKeyStrength();
    }
}
