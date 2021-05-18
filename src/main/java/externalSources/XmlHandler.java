package externalSources;

import com.thoughtworks.xstream.XStream;
import fileManaging.FileOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class XmlHandler extends IExternalFileSourceHandler {
    XStream xStream = new XStream();

    public XmlHandler() {
        Class<?>[] classes = new Class[]{InputData.class, EncryptionDecryptionInfo.class};
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);
        xStream.alias("outputData", EncryptionDecryptionInfo.class);
        xStream.alias("inputData", InputData.class);
        xStream.aliasField("encryptOrDecrypt", EncryptionDecryptionInfo.class, "actionEncryptOrDecrypt");
    }

    @Override
    public InputData getInputData(String xmlFilePath) throws FileNotFoundException {
        String xml = FileOperations.readFile(xmlFilePath);
        return (InputData) xStream.fromXML(xml);
    }

    @Override
    public void writeOutputData(EncryptionDecryptionInfo encryptionDecryptionInfo, String outputFileName) throws FileNotFoundException {
        String xml = xStream.toXML(encryptionDecryptionInfo);
        try {
            FileOperations.writeFile(Path.of(encryptionDecryptionInfo.getInputDirectoryPath(), outputFileName + ".xml").toString(), xml);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void createInputFile(String inputFileName) {
        ArrayList<String> complexEncryptionAlgorithmsNames = new ArrayList<>();
        complexEncryptionAlgorithmsNames.add("DoubleEncryption");
        complexEncryptionAlgorithmsNames.add("RepeatEncryption");
        InputData inputData = new InputData("debuggingFiles",
                "ShiftMultiplyEncryption", complexEncryptionAlgorithmsNames);
        String xml = xStream.toXML(inputData);
        try {
            FileOperations.writeFile(Path.of(inputData.getDirectoryPath(), inputFileName + ".xml").toString(), xml);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
