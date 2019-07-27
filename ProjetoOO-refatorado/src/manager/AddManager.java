package manager;

import employees.*;

public class AddManager{

    public void addEmployee(){
        EmployeesManager.emptyRedo();
        EmployeesManager.copyRegister();
        System.out.println("Please choose the worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = EmployeesManager.handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = EmployeesManager.handler.integerInput();
        }
        switch(type){
            case 1:
                addHourly();
                break;
            case 2:
                addSalaried();
                break;
            case 3:
                addCommissioned();
                break;
        }
        EmployeesManager.employeesAnnualCounter ++;
        EmployeesManager.employeesCounter ++;
    }

    public void addHourly(){
        int registerNumber = (EmployeesManager.calendar.year*1000)+EmployeesManager.employeesAnnualCounter;
        Employee newOne = new Hourly();
        newOne.setRegisterNumber(registerNumber);
        EmployeesManager.register.add(newOne);
    }

    public void addSalaried(){
        int registerNumber = (EmployeesManager.calendar.year*1000)+EmployeesManager.employeesAnnualCounter;
        Employee newOne = new Salaried();
        newOne.setRegisterNumber(registerNumber);
        EmployeesManager.register.add(newOne);
    }

    public void addCommissioned(){
        int registerNumber = (EmployeesManager.calendar.year*1000)+EmployeesManager.employeesAnnualCounter;
        Employee newOne = new Commissioned();
        newOne.setRegisterNumber(registerNumber);
        EmployeesManager.register.add(newOne);
    }

}
