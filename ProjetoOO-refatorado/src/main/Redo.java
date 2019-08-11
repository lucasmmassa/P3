package main;

public class Redo implements MainCommand {
    public void execute() {
        Main.manager.redo();
    }
}
