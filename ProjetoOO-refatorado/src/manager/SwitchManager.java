package manager;

import employees.*;

public class SwitchManager {

    public static void typeSwitch(Employee original){
        System.out.println("Switching worker type.");
        EmployeesManager.emptyRedo();
        EmployeesManager.copyRegister();
        System.out.println("Please type the new worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = EmployeesManager.handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = EmployeesManager.handler.integerInput();
        }

        switch(type){
            case 1:
                switchToHourly(original);
                break;
            case 2:
                switchToSalaried(original);
                break;
            case 3:
                switchToCommissioned(original);
                break;
        }
    }

    public static void switchToHourly(Employee original){
        if(original instanceof Hourly){
            System.out.println("The employee is already a hourly one.");
        }
        else{
            Hourly switched = new Hourly(original);
            EmployeesManager.register.remove(original);
            EmployeesManager.register.add(switched);
        }
    }

    public static void switchToSalaried(Employee original){
        if(original instanceof Salaried){
            System.out.println("This employee is already a salaried one.");
        }
        else{
            Salaried switched = new Salaried(original);
            EmployeesManager.register.remove(original);
            EmployeesManager.register.add(switched);
        }
    }

    public static void switchToCommissioned(Employee original){
        if(original instanceof Commissioned){
            System.out.println("This employee is already a commissioned one.");
        }
        else{
            Commissioned switched = new Commissioned(original);
            System.out.println("Please type the employee's commission:");
            double newCommission = EmployeesManager.handler.doubleInput();
            while(newCommission < 0 || newCommission >1){
                System.out.println("Please type a valid number.");
                newCommission = EmployeesManager.handler.doubleInput();
            }
            switched.setCommission(newCommission);
            EmployeesManager.register.remove(original);
            EmployeesManager.register.add(switched);
        }
    }
}
