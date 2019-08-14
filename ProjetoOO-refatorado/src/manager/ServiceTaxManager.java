package manager;

import employees.*;

public class ServiceTaxManager {
    EmployeesData data = EmployeesData.getSingleInstance();

    public void serviceTax() {
        data.emptyRedo();
        data.copyRegister();
        String edited = data.nameInput();
        Employee auxiliar = data.findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Please type the service tax: (value between 0 and 1)");
        double tax = data.handler.doubleInput();
        while(tax < 0 || tax > 1){
            System.out.println("Please type a valid number.");
            tax = data.handler.doubleInput();
        }

        auxiliar.setServiceTax(tax);
    }

    public void resetServiceTax() {
        for(Employee current : data.register){
            current.setServiceTax(0);
        }
    }

}
