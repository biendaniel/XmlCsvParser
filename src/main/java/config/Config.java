package config;

import controller.*;
import service.CustomerService;
import userInterface.ConsoleView;
import userInterface.Menu;
import userInterface.View;

import java.util.ArrayList;
import java.util.List;

public class Config {
    View view = new ConsoleView();
    public List<Command> initMenuOption() {

        List<Command> commands = new ArrayList<>();
        commands.add(new LoadGlobalFileCommand(view));
        commands.add(new LoadDirectoryFileCommand(view));
        commands.add(new SetDirectoryPathCommand(view));
        commands.add(new ShowAllCustomersCommand(view, new CustomerService()));
        commands.add(new ExitCommand());
        return commands;
    }

    public Menu initializeMenu() {
        return new Menu(view);
    }
}
