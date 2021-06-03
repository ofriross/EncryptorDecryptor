package logs;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EInputType;
import enums.EProgress;

import java.nio.file.Path;
import java.util.Optional;

public class EventException {

    public static String makeEncryptionLogMessage(Optional<String> exception, EActionEncryptOrDecrypt actionEncryptOrDecrypt,
                                                  EInputType inputType, EProgress progress, IEncryptionAlgorithm encryptionAlgorithm,
                                                  long time, String inputSourcePath, String outputSourcePath) {
        String encryptOrDecrypt = actionEncryptOrDecrypt.toString();
        String fileOrFolder = inputType.toString();
        return "The " + encryptOrDecrypt + "ion for " + fileOrFolder + " '" + Path.of(System.getProperty("user.dir"), inputSourcePath).toString()
                + "' with algorithm " + encryptionAlgorithm.getType() + " failed due to " + exception.get()
                + " in time: " + time + "(milliseconds).";
    }
}
