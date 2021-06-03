package multiThreading;

import encryptionAlgorithms.rest.IEncryptionAlgorithm;
import enums.EActionEncryptOrDecrypt;
import enums.EEventType;
import enums.EInputType;
import enums.EProgress;
import externalSources.EncryptionDecryptionInfo;
import externalSources.JsonHandler;
import externalSources.XmlHandler;
import fileManaging.FileEncryptor;
import fileManaging.FileOperations;
import general.Constants;
import logs.EncryptionLogger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface IDirectoryProcessor {
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
                EActionEncryptOrDecrypt.ENCRYPT, EInputType.FOLDER, EProgress.start, EEventType.process);
        for (String allFilesName : allFilesNames) {
            Runnable encryptionDecryptionThread = new EncryptionDecryptionThread(fileEncryptor, directoryPath, allFilesName, EActionEncryptOrDecrypt.ENCRYPT);
            executor.execute(encryptionDecryptionThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                directoryPath, Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.ENCRYPT, EInputType.FOLDER, EProgress.end, EEventType.process);
        System.out.println("The files got encrypted");

        executor = Executors.newFixedThreadPool(numberOfThreads);
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.DECRYPT, EInputType.FOLDER, EProgress.start, EEventType.process);
        for (String allFilesName : allFilesNames) {
            Runnable encryptionDecryptionThread = new EncryptionDecryptionThread(fileEncryptor, directoryPath, allFilesName, EActionEncryptOrDecrypt.DECRYPT);
            executor.execute(encryptionDecryptionThread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
        EncryptionLogger.addLog(Optional.empty(), encryptionAlgorithm,
                Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                new Date().getTime(),
                EActionEncryptOrDecrypt.DECRYPT, EInputType.FOLDER, EProgress.end, EEventType.process);
        System.out.println("The files got decrypted");

        long endTime = new Date().getTime();
        try {
            EncryptionDecryptionInfo encryptionDecryptionInfo = new EncryptionDecryptionInfo(encryptionAlgorithm.getType(),
                    directoryPath,
                    Path.of(directoryPath, Constants.ENCRYPT_FOLDER_NAME).toString(),
                    Path.of(directoryPath, Constants.DECRYPT_FOLDER_NAME).toString(),
                    startTime, endTime);
            new XmlHandler().writeOutputData(encryptionDecryptionInfo, "output DATA");
            new JsonHandler().writeOutputData(encryptionDecryptionInfo, "output DATA");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("The encryption and the decryption took in total " + (endTime - startTime) + "(milliseconds) with the ASynced Processor.");
    }

    void encryptAndDecryptFolder(Optional<Integer> numberOfThreads, String directoryPath, IEncryptionAlgorithm encryptionAlgorithm);
}
