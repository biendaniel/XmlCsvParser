package controller;

public interface Command {

    void execute();

    String getLabel();
}
