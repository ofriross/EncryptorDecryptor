package encryptionAlgorithms.complexEncryptions;

import encryptionAlgorithms.IEncryptionAlgorithm;
import encryptionAlgorithms.basicEncryptions.ShiftUpEncryption;
import keys.DoubleKey;
import keys.Key;

import javax.inject.Inject;
import java.util.ArrayList;

public class DoubleEncryption extends EncryptionAlgorithm {
    public DoubleEncryption(IEncryptionAlgorithm encryptionAlgorithm) {
        super(encryptionAlgorithm);
        this.encryptionAlgorithm = encryptionAlgorithm;
        System.out.println("hello there");
    }
    /*@Override
    public Key initKey() {
        return key = new DoubleKey(encryptionAlgorithm.initKey(), encryptionAlgorithm.initKey());
    }*/

    public DoubleEncryption(boolean b) {
        super(new ShiftUpEncryption());
        System.out.println(b);
    }

    @Override
    public Key initKey() {
        return key = new DoubleKey(encryptionAlgorithm.initKey(), encryptionAlgorithm.initKey());
    }

    @Override
    public <T extends Key> String performEncryption(String data, T key) {
        String firstEncryption = encryptionAlgorithm.performEncryption(data, ((DoubleKey) key).getDouble1());
        return encryptionAlgorithm.performEncryption(firstEncryption, ((DoubleKey) key).getDouble2());
    }

    @Override
    public String performDecryption(String data, ArrayList<Integer> keys) {
        return encryptionAlgorithm.performDecryption(data, keys);
    }

    @Inject
    public void setEncryptionAlgorithm(IEncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }
}
