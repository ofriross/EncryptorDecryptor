package encryptionAlgorithms.complexEncryptions;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import keys.Key;

import javax.inject.Inject;
import java.util.ArrayList;

public abstract class EncryptionAlgorithm implements IEncryptionAlgorithm {
    protected Key key;
    protected IEncryptionAlgorithm encryptionAlgorithm;

    @Override
    public String performDecryption(String data, ArrayList<Integer> keys) {
        return encryptionAlgorithm.performDecryption(data, keys);
    }

    public EncryptionAlgorithm(IEncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    @Override
    public int getKeyStrength() {
        return key.getKeyStrength();
    }

    @Override
    public String getType() {
        return key.getType();
    }

    @Inject
    public void setEncryptionAlgorithm(IEncryptionAlgorithm encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }
}
