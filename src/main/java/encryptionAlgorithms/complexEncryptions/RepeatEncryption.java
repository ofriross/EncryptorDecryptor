package encryptionAlgorithms.complexEncryptions;

import encryptionAlgorithms.IEncryptionAlgorithm;
import keys.Key;
import keys.RepeatKey;

import java.util.ArrayList;
import java.util.Random;

public class RepeatEncryption extends EncryptionAlgorithm {
    private final int timesToRepeat;

    public RepeatEncryption(IEncryptionAlgorithm encryptionAlgorithm, int timesToRepeat) {
        super(encryptionAlgorithm);
        this.timesToRepeat = timesToRepeat;
    }

    public RepeatEncryption(IEncryptionAlgorithm encryptionAlgorithm) {
        super(encryptionAlgorithm);
        Random r = new Random();
        timesToRepeat = r.nextInt(10) + 1;
    }

    @Override
    public Key initKey() {
        return key = new RepeatKey(timesToRepeat, encryptionAlgorithm.initKey());
    }

    @Override
    public <T extends Key> String performEncryption(String data, T key) {
        String encryption = encryptionAlgorithm.performEncryption(data, ((RepeatKey) key).getRepeatedKey());
        for (int i = 0; i < timesToRepeat - 1; i++) {
            encryption = encryptionAlgorithm.performEncryption(encryption, ((RepeatKey) key).getRepeatedKey());
        }
        return encryption;
    }
}
