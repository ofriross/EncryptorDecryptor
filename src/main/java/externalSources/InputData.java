package externalSources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class InputData {
    private final String directoryPath;
    private final String basicEncryptionAlgorithmName;
    private final ArrayList<String> complexEncryptionAlgorithmsNames;

    public InputData(String directoryPath, String basicEncryptionAlgorithmName, ArrayList<String> complexEncryptionAlgorithmsNames) {
        this.directoryPath = directoryPath;
        this.basicEncryptionAlgorithmName = basicEncryptionAlgorithmName;
        this.complexEncryptionAlgorithmsNames = complexEncryptionAlgorithmsNames;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public String getBasicEncryptionAlgorithmName() {
        return basicEncryptionAlgorithmName;
    }

    public ArrayList<String> getComplexEncryptionAlgorithmsNames() {
        return complexEncryptionAlgorithmsNames;
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
