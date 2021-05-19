package general;

import com.google.inject.Guice;
import com.google.inject.Injector;
import encryptionAlgorithms.EncryptionInjector;
import encryptionAlgorithms.IEncryptionAlgorithm;
import encryptionAlgorithms.complexEncryptions.DoubleEncryption;
import encryptionAlgorithms.complexEncryptions.EncryptionAlgorithm;
import externalSources.InputData;
import externalSources.JsonHandler;
import externalSources.XmlHandler;
import multiThreading.ASyncDirectoryProcessor;
import multiThreading.SyncDirectoryProcessor;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        new JsonHandler().createInputFile(args[0]);
        new XmlHandler().createInputFile(args[0]);

        InputData inputDataFromXml = getInputDataFromXml(args[0]);
        if (inputDataFromXml == null)
            return;

        InputData inputDataFromJson = getInputDataFromJson(args[0]);
        if (inputDataFromJson == null)
            return;

        String directory = getDirectoryPath(inputDataFromJson);
        if (directory == null)
            return;

        IEncryptionAlgorithm encryptionAlgorithm = getIEncryptionAlgorithm(inputDataFromJson);
        if (encryptionAlgorithm == null)
            return;

        /*Injector injector = Guice.createInjector(new EncryptionInjector());

        encryptionAlgorithm = injector.getInstance(DoubleEncryption.class);*/

        new ASyncDirectoryProcessor().encryptAndDecryptFolder(Optional.of(10), directory, encryptionAlgorithm);
        System.out.println();
        new SyncDirectoryProcessor().encryptAndDecryptFolder(Optional.empty(), directory, encryptionAlgorithm);
    }

    private static String getDirectoryPath(InputData inputData) {
        try {
            return inputData.getDirectoryPath();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static InputData getInputDataFromXml(String name) {
        try {
            return new XmlHandler().getInputData(Path.of("debuggingFiles", name + ".xml").toString());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static InputData getInputDataFromJson(String name) {
        try {
            return new JsonHandler().getInputData(Path.of("debuggingFiles", name + ".json").toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static IEncryptionAlgorithm getIEncryptionAlgorithm(InputData inputData) {
        String basicEncryptionAlgorithmName = inputData.getBasicEncryptionAlgorithmName();
        ArrayList<String> complexEncryptionAlgorithmsNames = inputData.getComplexEncryptionAlgorithmsNames();

        //noinspection rawtypes
        Class encryptionAlgorithmClass = null;
        try {
            encryptionAlgorithmClass = Class.forName("encryptionAlgorithms.basicEncryptions." + basicEncryptionAlgorithmName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        IEncryptionAlgorithm encryptionAlgorithm = null;
        try {
            encryptionAlgorithm = (IEncryptionAlgorithm) encryptionAlgorithmClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for (String complexEncryptionAlgorithmName : complexEncryptionAlgorithmsNames) {
            try {
                encryptionAlgorithmClass = Class.forName("encryptionAlgorithms.complexEncryptions." + complexEncryptionAlgorithmName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Class<?> encryptionClassArgument = Class.forName("encryptionAlgorithms.IEncryptionAlgorithm");
                encryptionAlgorithm = (EncryptionAlgorithm) encryptionAlgorithmClass.getDeclaredConstructor(encryptionClassArgument).newInstance(encryptionAlgorithm);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return encryptionAlgorithm;
    }
}
