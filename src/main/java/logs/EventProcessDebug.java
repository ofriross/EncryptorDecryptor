package logs;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EInputType;
import enums.EProgress;

import java.nio.file.Path;
import java.util.Optional;

public class EventProcessDebug {
    public static String makeEncryptionLogMessage(Optional<String> data, EActionEncryptOrDecrypt actionEncryptOrDecrypt,
                                                  EInputType inputType, EProgress progress, IEncryptionAlgorithm encryptionAlgorithm,
                                                  long time, String inputSourcePath, String outPutSourcePath) {
        String encryptDecrypt = actionEncryptOrDecrypt.toString();
        String dataString = data.get();
        if (progress == EProgress.start)
            return "The " + encryptDecrypt + "ion for FILE " + Path.of(System.getProperty("user.dir"), inputSourcePath).toString() + " with algorithm " +
                    encryptionAlgorithm.getType() + ", received the DATA '" + dataString + "' in time: " +
                    time + "(milliseconds).";
        return "The " + encryptDecrypt + "ion for FILE " + Path.of(System.getProperty("user.dir"), inputSourcePath).toString() + " with algorithm " +
                encryptionAlgorithm.getType() + ", will write the " + encryptDecrypt + "ed DATA:'" +
                dataString + "' to FILE " + Path.of(System.getProperty("user.dir"), outPutSourcePath).toString() + " in time: " + time + "(milliseconds).";
    }
}
