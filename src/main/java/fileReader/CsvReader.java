package fileReader;

import service.CsvFileService;
import service.FileService;
import utils.FileLineCounter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvReader implements FileReader {
    @Override
    public void read(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(fileName);
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            FileService service = new CsvFileService();
            FileLineCounter counter = new FileLineCounter();
            while (scanner.hasNextLine()) {
                counter.incrementCurrentLine();
                service.execute(parseFileLine(scanner.nextLine()), counter);
            }
            System.out.println("---- Pozytywnie wczytano " + counter.getPositiveLoadLine() + " z " + counter.getCurrentLine() + " wierszy. ----");
        } catch (IOException e) {
            System.err.println("Taki plik nie istnieje");
        }
    }

    private List<String> parseFileLine(String fileLine) {
        return Arrays.asList(fileLine.split(","));
    }
}
