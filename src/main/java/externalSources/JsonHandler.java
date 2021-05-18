package externalSources;

import fileManaging.FileOperations;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class JsonHandler extends IExternalFileSourceHandler {

    private String encryptionAlgorithmType;
    private String inputDirectoryPath;
    private String encryptionFolderPath;
    private String decryptionFolderPath;
    private long timeStarted;
    private long timeEnded;
    private long totalTime;

    public String tester(EncryptionDecryptionInfo encryptionDecryptionInfo) {
        return encryptionDecryptionInfo.toString();
    }

    public JsonHandler() {
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        String reader = FileOperations.readFile(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    @Override
    public InputData getInputData(String jsonFilePath) throws Exception {
        JSONObject jsonObject = (JSONObject) readJsonSimpleDemo(Path.of(jsonFilePath).toString());
        InputData inputData = new InputData(
                (String) jsonObject.get("directoryPath"),
                (String) jsonObject.get("basicEncryptionAlgorithmName"),
                (ArrayList<String>) jsonObject.get("complexEncryptionAlgorithmsNames"));
        return inputData;
    }

    @Override
    public void writeOutputData(EncryptionDecryptionInfo encryptionDecryptionInfo, String outputFileName) throws IOException {
        FileOperations.writeFile(Path.of(encryptionDecryptionInfo.getInputDirectoryPath(), outputFileName + ".json").toString(), encryptionDecryptionInfo.toString());
    }

    public void createInputFile(String outputFileName) throws IOException {
        ArrayList<String> complexEncryptionAlgorithmsNames = new ArrayList<>();
        complexEncryptionAlgorithmsNames.add("RepeatEncryption");
        complexEncryptionAlgorithmsNames.add("RepeatEncryption");
        InputData inputData = new InputData("debuggingFiles",
                "ShiftUpEncryption", complexEncryptionAlgorithmsNames);
        FileOperations.writeFile(Path.of(inputData.getDirectoryPath(), outputFileName + ".json").toString(), inputData.toString());
    }
}