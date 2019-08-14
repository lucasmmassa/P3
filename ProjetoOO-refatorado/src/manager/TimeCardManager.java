package manager;

import employees.Employee;
import employees.Hourly;

public class TimeCardManager {
    EmployeesData data = EmployeesData.getSingleInstance();

    public void timeCard() {
        data.emptyRedo();
        data.copyRegister();
        String edited = data.nameInput();
        Employee auxiliar = data.findEmployee(edited);
        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }
        int weekDay = data.calendar.weekday;

        if(weekDay == 1){
            System.out.println("It is not allowed to work on sundays.");
            return;
        }

        if(!(auxiliar instanceof Hourly)){
            System.out.println("Sorry, this employee is not a hourly one.");
            return;
        }

        ((Hourly)auxiliar).timecardInput(weekDay);
    }

}
