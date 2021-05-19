package EncriptionsTests;

import encryptionAlgorithms.basicEncryptions.ShiftUpEncryption;
import enums.EActionEncryptOrDecrypt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShiftUpEncryptionTest {

    @Test
    public void computeCharEncrypt() {
        int actualResult = (new ShiftUpEncryption()).computeChar(40, 3, EActionEncryptOrDecrypt.ENCRYPT);
        assertEquals(43, actualResult);
    }

    @Test
    public void computeCharDecrypt() {
        int actualResult = (new ShiftUpEncryption()).computeChar(40, 3, EActionEncryptOrDecrypt.DECRYPT);
        assertEquals(37, actualResult);
    }
}
