package encryptionAlgorithms.basicEncryptions;

import enums.EActionEncryptOrDecrypt;
import keys.Key;

public class ShiftUpEncryption extends BasicEncryption {

    @Override
    public Key initKey() {
        return super.initSingleKey("Shift Up");
    }

    @Override
    public int computeChar(int currentChar, int key, EActionEncryptOrDecrypt eActionEncryptOrDecrypt) {
        if (eActionEncryptOrDecrypt == EActionEncryptOrDecrypt.decrypt)
            key = -key;
        return currentChar + key;
    }
}
