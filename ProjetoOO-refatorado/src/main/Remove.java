package main;
import manager.RemoveManager;
public class Remove implements MainCommand{
    RemoveManager manager = new RemoveManager();
    public void execute(){
        System.out.println("\nRemoving an employee.\n");
        manager.removeEmployee();
    }
}
