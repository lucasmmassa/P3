package main;
import manager.ShowManager;
import java.util.Scanner;

public class Show implements MainCommand{
    ShowManager manager = new ShowManager();
    public void execute(){
        System.out.println("List of employees:\n");
        manager.showList();
        System.out.println("Type enter to continue.");
        Scanner input = new Scanner(System.in);
        String trash = input.nextLine();
    }
}
