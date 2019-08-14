package manager;

import employees.*;

public class SwitchManager {
    EmployeesData data = EmployeesData.getSingleInstance();

    public void typeSwitch(Employee original){
        System.out.println("Switching worker type.");
        data.emptyRedo();
        data.copyRegister();
        System.out.println("Please type the new worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = data.handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = data.handler.integerInput();
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

    public void switchToHourly(Employee original){
        if(original instanceof Hourly){
            System.out.println("The employee is already a hourly one.");
        }
        else{
            Hourly switched = new Hourly(original);
            data.register.remove(original);
            data.register.add(switched);
        }
    }

    public void switchToSalaried(Employee original){
        if(original instanceof Salaried){
            System.out.println("This employee is already a salaried one.");
        }
        else{
            Salaried switched = new Salaried(original);
            data.register.remove(original);
            data.register.add(switched);
        }
    }

    public void switchToCommissioned(Employee original){
        if(original instanceof Commissioned){
            System.out.println("This employee is already a commissioned one.");
        }
        else{
            Commissioned switched = new Commissioned(original);
            System.out.println("Please type the employee's commission:");
            double newCommission = data.handler.doubleInput();
            while(newCommission < 0 || newCommission >1){
                System.out.println("Please type a valid number.");
                newCommission = data.handler.doubleInput();
            }
            switched.setCommission(newCommission);
            data.register.remove(original);
            data.register.add(switched);
        }
    }
}
