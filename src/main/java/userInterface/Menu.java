package userInterface;

import controller.Command;
import controller.ExitCommand;
import controller.ReloadCommand;

import java.util.List;

public class Menu {
    private View view;

    private final String SELECT_OPTION = "Wybierz opcję: ";
    private final String SEPARATOR = ". ";
    public static String WRONG_OPTION = "Wybrałeś niewłaściwą opcję.";

    private final String CANNOT_PRINT_DIRECTORY_PATH = "Nie można wyświetlić ścieżki do katalogu głównego.";
    public Menu(View view) {
        this.view = view;
    }

    public void show(List<Command> commands) {
        view.display("MENU");
        for(Command command : commands) {
            String message = commands.indexOf(command) + SEPARATOR + command.getLabel();
            view.display(message);
        }
    }

    public Command getChoice(List<Command> commands) {
        int choice = view.readInt(SELECT_OPTION);
        try {
            return commands.get(choice);
        } catch(IndexOutOfBoundsException e) {
            System.err.println(WRONG_OPTION);
            return new ReloadCommand(view);
        }
    }
}
