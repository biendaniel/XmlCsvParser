package controller;

import exception.NotSupportedFileExtension;
import fileReader.FileReader;
import userInterface.View;
import utils.FileUtils;


public class LoadGlobalFileCommand implements Command {
    private final View view;
    private static final String LABEL = "Wczytaj plik podając pełną ścięzkę.";

    public LoadGlobalFileCommand(View view) {
        this.view = view;
    }
    @Override
    public void execute() {
        String fileName = view.readString("Podaj nazwę pliku: ");
        try {
            FileReader reader = FileUtils.checkFileType(fileName);
            reader.read(fileName);
        } catch (NotSupportedFileExtension e) {
            view.error("Nieobsługiwany typ pliku.");
        }
    }

    @Override
    public String getLabel() {
        return LABEL;
    }

}
