package fileManaging;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOperations {
    public static String readFile(String fileName) throws FileNotFoundException {
        StringBuilder data = new StringBuilder();
        try {
            FileReader fr = new FileReader(fileName);
            int currentChar;
            while ((currentChar = fr.read()) != -1)
                data.append((char) currentChar);
        } catch (IOException exception) {
            throw new FileNotFoundException("An error occurred while trying to read the FILE '" + fileName + "'.");
        }
        return data.toString();
    }

    /*public static String readFile(String inputFilePath) throws FileNotFoundException {
        Scanner s = null;
        String dataFromFile = "";
        try {
            s = new Scanner(new BufferedReader(new FileReader(inputFilePath)));
            while (s.hasNext()) {
                dataFromFile = s.next();
                char[] myChar = dataFromFile.toCharArray();
                // do something
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return
    }*/

    public static void writeFile(String fileName, String data) throws IOException {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            throw new IOException("The FILE '" + fileName + "' wasn't found");
        }
    }

    public static String createFolder(String directoryPath, String directoryName) {
        String folderName = Path.of(directoryPath, directoryName).toString();
        File fileToCreate = new File(folderName);
        if (!fileToCreate.exists()) {
            fileToCreate.mkdirs();
        }
        return folderName;
    }

    public static ArrayList<String> getTxtFilesNamesFromFolder(String directoryPath) throws IOException {
        ArrayList<String> folderContent = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null)
            throw new IOException("The FOLDER '" + directoryPath + "' is empty or does not exist");
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                if (!file.getName().equals("KEY.txt"))
                    folderContent.add(file.getName());
            }
        }
        return folderContent;
    }
}
