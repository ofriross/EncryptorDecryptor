package multiThreading;

import encryptionAlgorithms.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EEventType;
import enums.EInputType;
import enums.EProgress;
import externalSources.JsonHandler;
import fileManaging.FileEncryptor;
import fileManaging.FileOperations;
import general.Constants;
import logs.EncryptionLogger;
import externalSources.EncryptionDecryptionInfo;
import externalSources.XmlHandler;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface IDirectoryProcessor {
    void encryptAndDecryptFolder(Optional<Integer> numberOfThreads, String directoryPath, IEncryptionAlgorithm encryptionAlgorithm);

    static void encryptAndDecryptFolderDoer(int numberOfThreads, String directoryPath, IEncryptionAlgorithm encryptionAlgorithm) {
        long startTime = new Date().getTime();

        FileOperations.createFolder(directoryPath, Constants.ENCRYPT_FOLDER_NAME);
        FileOperations.createFolder(directoryPath, Constants.DECRYPT_FOLDER_NAME);

        ArrayList<String> allFilesNames;
        try {
            allFilesNames = FileOperations.getTxtFilesNamesFromFolder(directoryPath);
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        FileEncryptor fileEncryptor = new FileEncryptor(encryptionAlgorithm);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                directoryPath, Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.encrypt, EInputType.folder, EProgress.start, EEventType.process);
        for (String allFilesName : allFilesNames) {
            Runnable encryptionDecryptionThread = new EncryptionDecryptionThread(fileEncryptor, directoryPath, allFilesName, EActionEncryptOrDecrypt.encrypt);
            executor.execute(encryptionDecryptionThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                directoryPath, Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.encrypt, EInputType.folder, EProgress.end, EEventType.process);
        System.out.println("The files got encrypted");

        executor = Executors.newFixedThreadPool(numberOfThreads);
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.decrypt, EInputType.folder, EProgress.start, EEventType.process);
        for (String allFilesName : allFilesNames) {
            Runnable encryptionDecryptionThread = new EncryptionDecryptionThread(fileEncryptor, directoryPath, allFilesName, EActionEncryptOrDecrypt.decrypt);
            executor.execute(encryptionDecryptionThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.decrypt, EInputType.folder, EProgress.end, EEventType.process);
        System.out.println("The files got decrypted");

        long endTime = new Date().getTime();
        try {
            EncryptionDecryptionInfo encryptionDecryptionInfo = new EncryptionDecryptionInfo(encryptionAlgorithm.getType(),
                    directoryPath,
                    Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                    Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                    startTime, endTime);
            new XmlHandler().writeOutputData(encryptionDecryptionInfo,"output data");
            new JsonHandler().writeOutputData(encryptionDecryptionInfo,"output data");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("The encryption and the decryption took in total " + (endTime - startTime) + "(milliseconds) with the ASynced Processor.");
    }
}
