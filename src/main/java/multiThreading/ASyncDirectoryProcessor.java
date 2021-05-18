/*package MultiThreading;

import FileManaging.FileEncryptor;
import encryptionAlgorithms.IEncryptionAlgorithm;

public class SyncDirectoryProcessor implements IDirectoryProcessor {
    private final EncryptionDecryptionThread encryptionDecryptionThread;

    public SyncDirectoryProcessor(String directory, IEncryptionAlgorithm encryptionAlgorithm) {
        FolderEncryptionMonitor folderEncryptionMonitor = new FolderEncryptionMonitor(directory, encryptionAlgorithm);
        FileEncryptor fileEncryptor = new FileEncryptor(encryptionAlgorithm);
        encryptionDecryptionThread = new EncryptionDecryptionThread(folderEncryptionMonitor, fileEncryptor, directory);
    }


    public void encryptAndDecryptFolder() {
        try {
            encryptionDecryptionThread.start();
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }
    }
}*/
package multiThreading;

import encryptionAlgorithms.IEncryptionAlgorithm;

import java.util.Optional;

public class ASyncDirectoryProcessor implements IDirectoryProcessor {
    public void encryptAndDecryptFolder(Optional<Integer> numberOfThreads, String directoryPath, IEncryptionAlgorithm encryptionAlgorithm) {
        IDirectoryProcessor.encryptAndDecryptFolderDoer(numberOfThreads.get(), directoryPath, encryptionAlgorithm);
    }
}

