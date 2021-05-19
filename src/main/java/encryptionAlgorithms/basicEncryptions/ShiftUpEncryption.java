package encryptionAlgorithms.basicEncryptions;

import enums.EActionEncryptOrDecrypt;
import enums.EBasicEncryptionType;
import keys.Key;

public class ShiftUpEncryption extends BasicEncryption {

    @Override
    public Key initKey() {
        return super.initSingleKey(EBasicEncryptionType.SHIFT_UP);
    }

    @Override
    public int computeChar(int currentChar, int key, EActionEncryptOrDecrypt eActionEncryptOrDecrypt) {
        if (eActionEncryptOrDecrypt == EActionEncryptOrDecrypt.DECRYPT)
            key = -key;
        return currentChar + key;
    }
}
