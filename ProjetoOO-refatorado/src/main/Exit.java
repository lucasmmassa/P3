package main;

public class Exit implements MainCommand{
    public void execute() {
        Main.input.close();
        Main.run = false;
    }
}
