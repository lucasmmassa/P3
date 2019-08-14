package manager;

import employees.Employee;

public class RemoveManager {
    EmployeesData data  = EmployeesData.getSingleInstance();

    public void removeEmployee(){
        data.emptyRedo();
        data.copyRegister();
        String removed = data.nameInput();

        Employee auxiliar = data.findEmployee(removed);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        data.register.remove(auxiliar);
        System.out.println("The employee has been removed.\n");
        data.employeesCounter--;
    }
}
