package EncriptionsTests;

import encryptionAlgorithms.basicEncryptions.ShiftMultiplyEncryption;
import enums.EActionEncryptOrDecrypt;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShiftMultiplyEncryptionTest {

    @Test
    public void computeCharEncryptBasicCase() {
        int actualResult = (new ShiftMultiplyEncryption()).computeChar(40, 3, EActionEncryptOrDecrypt.ENCRYPT);
        int expectedResult = 40 * 3;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void computeCharRoundUpKey() {
        int actualResult = (new ShiftMultiplyEncryption()).computeChar(40, 2, EActionEncryptOrDecrypt.ENCRYPT);
        int expectedResult = 40 * (2 + 1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void computeCharDecryptBasicCase() {
        int actualResult = (new ShiftMultiplyEncryption()).computeChar(120, 3, EActionEncryptOrDecrypt.DECRYPT);
        assertEquals(40, actualResult);
    }

    @Test
    public void computeCharDecryptRoundTrip() {
        int actualResult = (new ShiftMultiplyEncryption()).computeChar(184, 10, EActionEncryptOrDecrypt.DECRYPT);
        assertEquals(40, actualResult);
    }
}