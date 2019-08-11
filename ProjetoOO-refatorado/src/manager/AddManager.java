package manager;

import employees.*;

public class AddManager{

    public void addEmployee(){
        EmployeesData.emptyRedo();
        EmployeesData.copyRegister();
        System.out.println("Please choose the worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = EmployeesData.handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = EmployeesData.handler.integerInput();
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
        EmployeesData.employeesAnnualCounter ++;
        EmployeesData.employeesCounter ++;
    }

    public void addHourly(){
        int registerNumber = (EmployeesData.calendar.year*1000)+ EmployeesData.employeesAnnualCounter;
        Employee newOne = new Hourly();
        newOne.setRegisterNumber(registerNumber);
        EmployeesData.register.add(newOne);
    }

    public void addSalaried(){
        int registerNumber = (EmployeesData.calendar.year*1000)+ EmployeesData.employeesAnnualCounter;
        Employee newOne = new Salaried();
        newOne.setRegisterNumber(registerNumber);
        EmployeesData.register.add(newOne);
    }

    public void addCommissioned(){
        int registerNumber = (EmployeesData.calendar.year*1000)+ EmployeesData.employeesAnnualCounter;
        Employee newOne = new Commissioned();
        newOne.setRegisterNumber(registerNumber);
        EmployeesData.register.add(newOne);
    }

}
