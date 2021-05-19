package encryptionAlgorithms.basicEncryptions;

import enums.EActionEncryptOrDecrypt;
import enums.EBasicEncryptionType;
import general.Constants;
import keys.Key;

public class ShiftMultiplyEncryption extends BasicEncryption {
    @Override
    public Key initKey() {
        return super.initSingleKey(EBasicEncryptionType.SHIFT_MULTIPLY);
    }

    @Override
    public int computeChar(int currentChar, int key, EActionEncryptOrDecrypt eActionEncryptOrDecrypt) {
        if (key % 2 == 0)
            key += 1;
        int computedChar = 0;
        if (eActionEncryptOrDecrypt == EActionEncryptOrDecrypt.ENCRYPT)
            computedChar = currentChar * key;
        else
            for (int i = 0; i <= Constants.MAX_ASCII_VALUE; i++)
                if ((i * key) % (Constants.MAX_ASCII_VALUE + 1) == currentChar)
                    computedChar = i;
        return computedChar;
    }
}