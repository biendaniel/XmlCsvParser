package controller;

import userInterface.View;

public class ExitCommand implements Command{

    private static final String OPTION_NAME = "Wyjdź.";
    private static final int SUCCESSFUL = 0;

    @Override
    public void execute() {
        System.exit(SUCCESSFUL);
    }

    @Override
    public String getLabel() {
        return OPTION_NAME;
    }
}
