package main;

public class Undo implements MainCommand{
    public void execute() {
        Main.manager.undo();
    }
}
