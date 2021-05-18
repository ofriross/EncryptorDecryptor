package encryptionAlgorithms.complexEncryptions;

import encryptionAlgorithms.IEncryptionAlgorithm;
import keys.Key;

import javax.inject.Inject;

public abstract class EncryptionAlgorithm implements IEncryptionAlgorithm {
    public static Class<?> TYPE;

    static {
        try {
            TYPE = Class.forName("encryptionAlgorithms.IEncryptionAlgorithm");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Key key;
    protected IEncryptionAlgorithm encryptionAlgorithm;

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
        System.out.println("heyo out");
        this.encryptionAlgorithm = encryptionAlgorithm;
    }
}
