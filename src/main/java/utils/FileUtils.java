package utils;

import exception.NotSupportedFileExtension;
import fileReader.CsvReader;
import fileReader.FileReader;
import fileReader.XmlReader;
import org.apache.commons.io.FilenameUtils;

public class FileUtils {

    private FileUtils() {
    }
    public static FileReader checkFileType(String fileName) throws NotSupportedFileExtension {
        String extension = FilenameUtils.getExtension(fileName);
        if(extension.equals("txt") || extension.equals("csv")) {
            return new CsvReader();
        }
        else if(extension.equals("xml")) {
            return new XmlReader();
        }
        else {
            throw new NotSupportedFileExtension();
        }
    }
}
