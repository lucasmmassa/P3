package manager;

import employees.*;

public class AddManager{
    EmployeesData data = EmployeesData.getSingleInstance();

    public void addEmployee(){
        data.emptyRedo();
        data.copyRegister();
        System.out.println("Please choose the worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = data.handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = data.handler.integerInput();
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
        data.employeesAnnualCounter ++;
        data.employeesCounter ++;
    }

    public void addHourly(){
        int registerNumber = (data.calendar.year*1000)+ data.employeesAnnualCounter;
        Employee newOne = new Hourly();
        newOne.setRegisterNumber(registerNumber);
        data.register.add(newOne);
    }

    public void addSalaried(){
        int registerNumber = (data.calendar.year*1000)+ data.employeesAnnualCounter;
        Employee newOne = new Salaried();
        newOne.setRegisterNumber(registerNumber);
        data.register.add(newOne);
    }

    public void addCommissioned(){
        int registerNumber = (data.calendar.year*1000)+ data.employeesAnnualCounter;
        Employee newOne = new Commissioned();
        newOne.setRegisterNumber(registerNumber);
        data.register.add(newOne);
    }

}
