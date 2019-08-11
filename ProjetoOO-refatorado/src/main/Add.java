package main;
import manager.AddManager;

public class Add implements MainCommand{
    AddManager manager = new AddManager();
    public void execute(){
        System.out.println("\nRegistering an employee.");
        manager.addEmployee();
    }
}
