package manager;

import employees.*;

public class ServiceTaxManager {
    public void serviceTax() {
        EmployeesData.emptyRedo();
        EmployeesData.copyRegister();
        String edited = EmployeesData.nameInput();
        Employee auxiliar = EmployeesData.findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Please type the service tax: (value between 0 and 1)");
        double tax = EmployeesData.handler.doubleInput();
        while(tax < 0 || tax > 1){
            System.out.println("Please type a valid number.");
            tax = EmployeesData.handler.doubleInput();
        }

        auxiliar.setServiceTax(tax);
    }

    public void resetServiceTax() {
        for(Employee current : EmployeesData.register){
            current.setServiceTax(0);
        }
    }

}
