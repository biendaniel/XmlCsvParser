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
        //given
        String fileName = "test.txt";
        //when
        FileReader fileReader = FileUtils.checkFileType(fileName);
        //then
        assertTrue(fileReader instanceof CsvReader);
    }

    @Test
    void shouldThrowException() {
        //given
        String fileName = "test.csvv";
        //when
        //then
        assertThrows(NotSupportedFileExtension.class, () -> FileUtils.checkFileType(fileName));
    }

    @Test
    void shouldReturnXmlReader() throws NotSupportedFileExtension {
        //given
        String fileName = "test.xml";
        //when
        FileReader fileReader = FileUtils.checkFileType(fileName);
        //then
        assertTrue(fileReader instanceof XmlReader);
    }
}
