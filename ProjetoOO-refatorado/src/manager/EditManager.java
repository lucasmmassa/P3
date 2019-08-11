package manager;

import employees.*;
import java.util.Scanner;

public class EditManager {

    public void editEmployee(){
        Scanner input = new Scanner(System.in);
        EmployeesData.emptyRedo();
        EmployeesData.copyRegister();
        String edited = EmployeesData.nameInput();
        Employee auxiliar = EmployeesData.findEmployee(edited);
        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Select the info you want to edit:");
        System.out.println("1 -> Name.");
        System.out.println("2 -> Adress.");
        System.out.println("3 -> Register number.");
        System.out.println("4 -> Salary.");
        System.out.println("5 -> Commission.");
        System.out.println("6 -> Syndicated.");
        System.out.println("7 -> Syndicate register number.");
        System.out.println("8 -> Worker type.");
        System.out.println("9 -> Union fee.");
        System.out.println("10 -> Payment method.");

        int choice = EmployeesData.handler.integerInput();
        while(choice < 1 || choice > 10){
            System.out.println("Please choose one of the options above.");
            choice = EmployeesData.handler.integerInput();
        }

        switch(choice){

            case 1:
                System.out.println("Please type the employee's new name:");
                String newName = input.nextLine();
                while(newName.equals("")){
                    System.out.println("Please type a valid name");
                    newName = input.nextLine();
                }
                auxiliar.setName(newName);
                System.out.println("");
                break;

            case 2:
                System.out.println("Please type the emplyee's new adress:");
                String newAdress = input.nextLine();
                while(newAdress.equals("")){
                    System.out.println("Please type a valid adress");
                    newAdress = input.nextLine();
                }
                auxiliar.setAdress(newAdress);
                System.out.println("");
                break;

            case 3:
                System.out.println("Please type the employee's new register number:");
                int newRegister = EmployeesData.handler.integerInput();
                while(newRegister < 0){
                    System.out.println("Please type a valid number.");
                    newRegister = EmployeesData.handler.integerInput();
                }
                auxiliar.setRegisterNumber(newRegister);
                System.out.println("");
                break;

            case 4:
                System.out.println("Please enter the employee's new salary:");
                double newSalary = EmployeesData.handler.doubleInput();
                while(newSalary < 998){
                    System.out.println("Please type a value that is equal or bigger than the minimum wage.");
                    newSalary = EmployeesData.handler.doubleInput();
                }
                auxiliar.setSalary(newSalary);
                if(auxiliar instanceof Hourly){
                    ((Hourly)auxiliar).setHourSalary(auxiliar.getSalary()/220.0);
                }
                System.out.println("");
                break;

            case 5:
                if(auxiliar instanceof Commissioned){
                    System.out.println("Please type the new commission: (value between 0 and 1)");
                    double commission = EmployeesData.handler.doubleInput();
                    while(commission < 0 || commission > 1){
                        System.out.println("Please type a valid number.");
                        commission = EmployeesData.handler.doubleInput();
                    }
                    ((Commissioned)auxiliar).setCommission(commission);
                }
                else{
                    System.out.println("Sorry, this emplyoee is not a commissioned one.\n");
                }
                break;

            case 6:
                boolean before = auxiliar.getSyndicated();
                System.out.println("Please tell if the employee is syndicated now:");
                auxiliar.setSyndicated();
                System.out.println("");
                if(!before && auxiliar.getSyndicated()){
                    System.out.println("Please type the new syndicate register rumber:");
                    int newSyndicate = EmployeesData.handler.integerInput();
                    while(newSyndicate < 0){
                        System.out.println("Please type a valid number.");
                        newSyndicate = EmployeesData.handler.integerInput();
                    }
                    auxiliar.setSyndicateRegister(newSyndicate);
                    System.out.println("");
                }
                break;

            case 7:
                if(auxiliar.getSyndicated()){
                    System.out.println("Please type the new syndicate register rumber:");
                    int newSyndicate = EmployeesData.handler.integerInput();
                    while(newSyndicate < 0){
                        System.out.println("Please type a valid number.");
                        newSyndicate = EmployeesData.handler.integerInput();
                    }
                    auxiliar.setSyndicateRegister(newSyndicate);
                    System.out.println("");
                }
                else{
                    System.out.println("Sorry, this employee is not syndicated.");
                }
                break;

            case 8:
                SwitchManager.typeSwitch(auxiliar);
                break;

            case 9:
                if(auxiliar.getSyndicated()){
                    System.out.println("Please type the new union fee: (value between 0 and 1)");
                    double newFee = EmployeesData.handler.doubleInput();
                    while(newFee < 0 || newFee > 1){
                        System.out.println("Please type a valid number.");
                        newFee = EmployeesData.handler.doubleInput();
                    }
                    System.out.println("");
                }
                else{
                    System.out.println("Sorry, this employee is not syndicated.");
                }
                break;

            case 10:
                System.out.println("Please choose a new payment method:");
                auxiliar.setPaymentMethod();
                System.out.println("");
                break;
        }
    }

}
