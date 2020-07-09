package controller;

import userInterface.View;

public class ReloadCommand implements Command {
    View view;
    private static final String OPTION_NAME = "Podałeś błędną opcję.";

    public ReloadCommand(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
// reload
    }

    @Override
    public String getLabel() {
        return OPTION_NAME;
    }
}
