package utils;

import exception.NotSupportedFileExtension;
import fileReader.CsvReader;
import fileReader.FileReader;
import fileReader.XmlReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void shouldReturnCsvReader() throws NotSupportedFileExtension {
        FileReader fileReader = FileUtils.checkFileType("test.csv");
    assertTrue(fileReader instanceof CsvReader);
    }

    @Test
    void shouldReturnCsvReader_txtExtension() throws NotSupportedFileExtension {
        FileReader fileReader = FileUtils.checkFileType("test.txt");
        assertTrue(fileReader instanceof CsvReader);
    }

    @Test
    void shouldThrowException() {
        assertThrows(NotSupportedFileExtension.class, () -> FileUtils.checkFileType("test.csvv"));
    }

    @Test
    void shouldReturnXmlReader() throws NotSupportedFileExtension {
        FileReader fileReader = FileUtils.checkFileType("test.xml");
        assertTrue(fileReader instanceof XmlReader);
    }
}
