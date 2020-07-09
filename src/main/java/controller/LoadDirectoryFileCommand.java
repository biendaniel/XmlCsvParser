package controller;

import exception.NotDictionaryElementExist;
import exception.NotSupportedFileExtension;
import fileReader.FileReader;
import userInterface.View;
import utils.Dictionary;
import utils.FileUtils;

public class LoadDirectoryFileCommand implements Command {
    private final View view;
    private static final String LABEL = "Wczytaj plik z głównego katalogu.";
    private static final String DIRECTORY_PATH_LABEL = "Ścieżka do katalogu głównego - ";

    public LoadDirectoryFileCommand(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            view.display(DIRECTORY_PATH_LABEL + Dictionary.getInstance().getValue(Dictionary.DIRECTORY));
            String fileName = view.readString("Podaj nazwę pliku: ");
            FileReader reader = FileUtils.checkFileType(fileName);
            reader.read(Dictionary.getInstance().getValue(Dictionary.DIRECTORY) + fileName);
        } catch (NotDictionaryElementExist e) {
            view.error("Nie udało się pobrać ścieżki do katalogu.");
        } catch (NotSupportedFileExtension e) {
            view.error("Nieobsługiwany typ pliku.");
        }
    }

    @Override
    public String getLabel() {
        return LABEL;
    }
}
