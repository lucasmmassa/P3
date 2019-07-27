package manager;

import employees.Employee;

public class RemoveManager {

    public void removeEmployee(){
        EmployeesManager.emptyRedo();
        EmployeesManager.copyRegister();
        String removed = EmployeesManager.nameInput();

        Employee auxiliar = EmployeesManager.findEmployee(removed);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        EmployeesManager.register.remove(auxiliar);
        System.out.println("The employee has been removed.\n");
        EmployeesManager.employeesCounter--;
    }
}
