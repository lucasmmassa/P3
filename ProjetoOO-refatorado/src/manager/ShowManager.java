package manager;

import employees.*;

public class ShowManager {

    public void showList(){
        for(Employee current : EmployeesManager.register){
            System.out.println("Name: " + current.getName() + ".");
            System.out.println("Adress: " + current.getAdress() + ".");
            System.out.println("Register number: " + current.getRegisterNumber() + ".");
            System.out.println("Mensal salary: " + current.getSalary() + ".");

            if(current instanceof Hourly){
                System.out.println("Worker type: Hourly.");
            }
            else if(current instanceof Salaried){
                System.out.println("Worker type: Salaried.");
            }
            else if(current instanceof Commissioned){
                System.out.println("Worker type: Commissioned.");
                System.out.println("Commission: " + ((Commissioned)current).getCommission());
            }

            switch(current.getPaymentMethod()){
                case 1:
                    System.out.println("Payment method: Check by mail.");
                    break;
                case 2:
                    System.out.println("Payment method: Check in hands.");
                    break;
                case 3:
                    System.out.println("Payment method: Bank account.");
                    break;
            }

            if(current.getSyndicated()){
                System.out.println("The employee is syndicated.");
                System.out.println("Syndicate register: " + current.getSyndicateRegister() + ".");
                System.out.println("Uniion fee: " + current.getUnionFee() + ".\n");
            }
            else{
                System.out.println("The employee is not syndicated.\n");
            }
        }
    }

}
