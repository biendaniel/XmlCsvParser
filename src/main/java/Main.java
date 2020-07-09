import config.Config;
import controller.Command;
import userInterface.Menu;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        Menu menu = config.initializeMenu();
        List<Command> commands = config.initMenuOption();

        while(true) {
            menu.show(commands);
            Command command = menu.getChoice(commands);
            command.execute();
        }
    }
}
