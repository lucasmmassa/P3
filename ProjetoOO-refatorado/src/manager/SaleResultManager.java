package manager;

import employees.Commissioned;
import employees.Employee;

public class SaleResultManager {
    EmployeesData data = EmployeesData.getSingleInstance();

    public void saleResult() {
        data.emptyRedo();
        data.copyRegister();
        String edited = data.nameInput();
        Employee auxiliar = data.findEmployee(edited);

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
