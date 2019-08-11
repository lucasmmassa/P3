package manager;

import employees.Employee;

public class RemoveManager {

    public void removeEmployee(){
        EmployeesData.emptyRedo();
        EmployeesData.copyRegister();
        String removed = EmployeesData.nameInput();

        Employee auxiliar = EmployeesData.findEmployee(removed);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        EmployeesData.register.remove(auxiliar);
        System.out.println("The employee has been removed.\n");
        EmployeesData.employeesCounter--;
    }
}
