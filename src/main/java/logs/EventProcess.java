package logs;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EInputType;
import enums.EProgress;

import java.nio.file.Path;
import java.util.Optional;

public class EventProcess {
    public static String makeEncryptionLogMessage(Optional<String> data, EActionEncryptOrDecrypt actionEncryptOrDecrypt,
                                                  EInputType inputType, EProgress progress, IEncryptionAlgorithm encryptionAlgorithm,
                                                  long time, String inputSourcePath, String outputSourcePath) {
        String encryptOrDecrypt = actionEncryptOrDecrypt.toString();
        String fileOrFolder = inputType.toString();
        String message = "";
        if (progress == EProgress.start) {
            message = "The " + encryptOrDecrypt + "ion for " + fileOrFolder + " " + Path.of(System.getProperty("user.dir"), inputSourcePath).toString() +
                    " with algorithm " + encryptionAlgorithm.getType() + " started in time: " + time + "(milliseconds).";
        } else {
            long startTime = EncryptionLogger.findEncryptionLogEventArgs(new HashMapKey(encryptionAlgorithm, inputSourcePath, outputSourcePath));
            return "The " + encryptOrDecrypt + "ion for " + fileOrFolder + " " + Path.of(System.getProperty("user.dir"), inputSourcePath).toString() +
                    " with algorithm " + encryptionAlgorithm.getType() + " ended in time: " + time + "(milliseconds). It took" +
                    " in total " + (time - startTime) + " (milliseconds).";
        }
        message += "The " + encryptOrDecrypt + "ed " + fileOrFolder + " is located in " + Path.of(System.getProperty("user.dir"), outputSourcePath).toString();
        return message;
    }
}
