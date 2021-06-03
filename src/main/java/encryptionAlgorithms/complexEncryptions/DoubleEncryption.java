package encryptionAlgorithms.complexEncryptions;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import encryptionAlgorithms.basicEncryptions.ShiftUpEncryption;
import keys.DoubleKey;
import keys.Key;

import javax.inject.Inject;

public class DoubleEncryption extends EncryptionAlgorithm {
    public DoubleEncryption(IEncryptionAlgorithm encryptionAlgorithm) {
        super(encryptionAlgorithm);
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

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

    @Inject
    public void setEncryptionAlgorithm(IEncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }
}
