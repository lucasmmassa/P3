package manager;

import employees.Commissioned;
import employees.Employee;

public class SaleResultManager {

    public void saleResult() {
        EmployeesManager.emptyRedo();
        EmployeesManager.copyRegister();
        String edited = EmployeesManager.nameInput();
        Employee auxiliar = EmployeesManager.findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        if(!(auxiliar instanceof Commissioned)){
            System.out.println("Sorry, this employee is not a commissioned one.");
            return;
        }

        ((Commissioned)auxiliar).inputSaleResult();
    }

}
