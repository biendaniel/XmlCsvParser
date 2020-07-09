package controller;

import userInterface.View;
import utils.Dictionary;

public class SetDirectoryPathCommand implements Command {
    private View view;
    private static final String LABEL = "Ustaw ścieżkę do katalogu.";

    public SetDirectoryPathCommand(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        String directoryPath = view.readString("Podaj pełną ścieżkę katalogu: ");
        if(validateDirectoryPath(directoryPath)) {
            Dictionary.getInstance().changeValue(Dictionary.DIRECTORY, directoryPath);
        }
        else {
            view.error("Podaj prawidłową ścieżkę do pliku - np: C:/users/user/Desktop");
        }
    }

    private boolean validateDirectoryPath(String directoryPath) {
        String regex = "^[a-zA-Z]:\\\\(((?![<>:\"/\\\\|?*]).)+((?<![ .])\\\\)?)*$";
        return directoryPath.matches(regex);
    }

    @Override
    public String getLabel() {
        return LABEL;
    }
}
