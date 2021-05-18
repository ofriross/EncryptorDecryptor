package fileManaging;

public class FileNameAndContent {
    private final String fileName;
    private String fileContent;

    public FileNameAndContent(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileNameAndContent that = (FileNameAndContent) o;
        return fileName.equals(that.fileName) && fileContent.equals(that.fileContent);
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
