package encryptionAlgorithms.basicEncryptions;

import enums.EActionEncryptOrDecrypt;
import keys.Key;

public class XorEncryption extends BasicEncryption {

    @Override
    public Key initKey() {
        return super.initSingleKey("Xor");
    }

    @Override
    public int computeChar(int currentChar, int key, EActionEncryptOrDecrypt eActionEncryptOrDecrypt) {
        String binaryKey = Integer.toBinaryString(key);
        /** make key and currentChar into a 8 digit binary number, with 0's from left to fill 8 digits */
        binaryKey = String.format("%8s", binaryKey).replaceAll(" ", "0");  // 8-bit Integer
        String binaryCurrentChar = Integer.toBinaryString(currentChar);
        binaryCurrentChar = String.format("%8s", binaryCurrentChar).replaceAll(" ", "0");  // 8-bit Integer
        StringBuilder currentCharString = new StringBuilder(binaryCurrentChar);
        for (int binIndex = 0; binIndex < 8; binIndex++) {
            if (binaryCurrentChar.charAt(binIndex) == binaryKey.charAt(binIndex))
                currentCharString.setCharAt(binIndex, '0');
            else
                currentCharString.setCharAt(binIndex, '1');
        }
        return Integer.parseInt(currentCharString.toString(), 2);
    }
}
