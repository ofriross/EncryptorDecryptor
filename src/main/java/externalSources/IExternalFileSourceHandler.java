package externalSources;

import enums.EActionEncryptOrDecrypt;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class IExternalFileSourceHandler {
    public abstract InputData getInputData(String xmlFilePath) throws Exception;

    public abstract void writeOutputData(EncryptionDecryptionInfo encryptionDecryptionInfo,String outputFileName) throws IOException;
}
