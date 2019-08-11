package main;
import manager.EditManager;

public class Edit implements MainCommand{
    EditManager manager = new EditManager();
    public void execute(){
        System.out.println("\nEditing info.\n");
        manager.editEmployee();
    }
}
