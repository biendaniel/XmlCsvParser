package fileReader;

import service.XmlFileService;
import utils.FileLineCounter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class XmlReader implements FileReader {
    @Override
    public void read(String fileName) {
        XmlFileService service = new XmlFileService();
        try (FileInputStream inputStream = new FileInputStream(fileName);
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            FileLineCounter counter = new FileLineCounter();
            while (scanner.hasNextLine()) {
                counter.incrementCurrentLine();
                if (service.isOpenTag(scanner.nextLine(), XmlFileService.Tags.PERSON)) {
                    service.execute(scanner, counter);
                }
            }
        } catch (IOException e) {
            System.err.println("Taki plik nie istnieje");
        }
    }
}
