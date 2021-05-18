package externalSources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EncryptionDecryptionInfo {
    private final String encryptionAlgorithmType;
    private final String inputDirectoryPath;
    private final String encryptionFolderPath;
    private final String decryptionFolderPath;
    private final long timeStarted;
    private final long timeEnded;
    private final long totalTime;

    public EncryptionDecryptionInfo(String encryptionAlgorithmType,
                                    String inputFilePath, String encryptionFolderPath,
                                    String decryptionFolderPath, long timeStarted, long timeEnded) {
        this.encryptionAlgorithmType = encryptionAlgorithmType;
        this.inputDirectoryPath = inputFilePath;
        this.encryptionFolderPath = encryptionFolderPath;
        this.decryptionFolderPath = decryptionFolderPath;
        this.timeStarted = timeStarted;
        this.timeEnded = timeEnded;
        totalTime = timeEnded - timeStarted;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public long getTimeEnded() {
        return timeEnded;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public String getInputDirectoryPath() {
        return inputDirectoryPath;
    }
}
