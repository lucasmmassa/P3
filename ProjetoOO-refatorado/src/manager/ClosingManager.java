package manager;
import main.Main;

public class ClosingManager {
    public void close() {
        if (!Main.rolled) {
            System.out.println("The payroll was not checked today!");
            System.out.println("Do you want to check it?");
            System.out.println("1-yes 0-no");
            int choice = Main.handler.integerInput();
            while (choice < 0 || choice > 1) {
                System.out.println("Please choose one of the options above.");
                choice = Main.handler.integerInput();
            }
            if (choice == 1) {
                PaymentManager paymentManager = new PaymentManager();
                paymentManager.payroll();
            }
        }
        System.out.println("\nEnd of the daily activities.\n");
        Main.rolled = false;
        int monthBeforeUpdate = Main.manager.calendar.month;
        boolean newYear = Main.manager.calendar.dateManager();
        if (newYear) {
            Main.manager.restartCounter();
        }
        if (monthBeforeUpdate != Main.manager.calendar.month) {
            ServiceTaxManager serviceTaxManager = new ServiceTaxManager();
            serviceTaxManager.resetServiceTax();
        }
    }
}
