package encryptionAlgorithms.basicEncryptions;

import encryptionAlgorithms.IEncryptionAlgorithm;
import general.Constants;
import keys.Key;
import keys.SingleKey;
import enums.EActionEncryptOrDecrypt;

import java.util.ArrayList;
//TODO: combine abstract and interface if needed
public abstract class BasicEncryption implements IEncryptionAlgorithm {
    protected SingleKey singleKey;

    public String getType() {
        return singleKey.getType();
    }

    public <T extends Key> String performEncryption(String data, T key) {
        int keyValue = ((SingleKey) key).getValue();
        return encryptDecrypt(data, keyValue, this, EActionEncryptOrDecrypt.encrypt);
    }

    public String performDecryption(String data, ArrayList<Integer> keys) {
        String decryptedData = null;
        for (Integer key : keys)
            decryptedData = encryptDecrypt(decryptedData != null ? decryptedData : data, key, this, EActionEncryptOrDecrypt.decrypt);
        return decryptedData;
    }

    public Key initKey(String encryptionType) {
        this.singleKey = new SingleKey(encryptionType);
        return singleKey;
    }

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

    public abstract int computeChar(int currentChar, int key, EActionEncryptOrDecrypt EActionEncryptOrDecrypt);

    public int getKeyStrength() {
        return singleKey.getKeyStrength();
    }
}
