package main;
import manager.PaymentManager;
import java.util.Scanner;

public class Payroll implements MainCommand {
    PaymentManager manager = new PaymentManager();
    public void execute() {
        System.out.println("List if payments:\n");
        if(!Main.rolled) {
            manager.payroll();
            Main.rolled = true;
        }
        System.out.println("\nPress enter to continue.");
        Scanner input = new Scanner(System.in);
        String trash = input.nextLine();
    }
}
