package main;
import manager.ClosingManager;

public class Close implements MainCommand{
    ClosingManager manager = new ClosingManager();
    public void execute(){
        manager.close();
    }
}
