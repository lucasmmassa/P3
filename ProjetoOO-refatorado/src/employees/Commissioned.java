package employees;

import utils.*;

public class Commissioned extends Employee implements Prototype{

    private double commission;
    public double saleResults;
    

    public Commissioned(){
        this.toReceive = 0;
        standardCommissionedAgenda();
        this.saleResults = 0;

        System.out.println("Please type the employee's name:");
        String nameInputer = input.nextLine();
        while(nameInputer.equals("")){
            System.out.println("Please type a valid name.");
            nameInputer = input.nextLine();
        }
        setName(nameInputer);
        System.out.println("");

        System.out.println("Please type the emplyoee's adress:");
        String adressInputer = input.nextLine();
        while(adressInputer.equals("")){
            System.out.println("Please type a valid adress.");
            adressInputer = input.nextLine();
        }
        setAdress(adressInputer);

        System.out.println("Please enter the employee's salary:");
        double salaryInputer = handler.doubleInput();
        while(salaryInputer < 998){
            System.out.println("Please type a value that is equal or bigger than the minimum wage.");
            salaryInputer = handler.doubleInput();
        }
        setSalary(salaryInputer);
        System.out.println("");

        this.toReceive = this.salary;

        System.out.println("Please tell if the employee is syndicated:");
        setSyndicated();
        System.out.println("");

        if(this.syndicated){
            System.out.println("Please type the syndicate register rumber:");
            int registerInputer = handler.integerInput();
            while(registerInputer < 0){
                System.out.println("Please type a valid number.");
                registerInputer = handler.integerInput();
            }
            setSyndicateRegister(registerInputer);
            System.out.println("");

            System.out.println("Please type the union fee: (value between 0 and 1)");
            double feeInputer = handler.doubleInput();
            while(feeInputer < 0 || feeInputer >1){
                System.out.println("Please type a valid fee.");
                feeInputer = handler.doubleInput();
            }
            setUnionFee(feeInputer);
            System.out.println("");
        }

        System.out.println("Please choose a payment method:");
        setPaymentMethod();
        System.out.println("");

        System.out.println("Please type the employee's commission: (value between 0 and 1)");
        double commissionInputer = handler.doubleInput();
        while(commissionInputer < 0 || commissionInputer > 1){
            System.out.println("Please type a valid commission.");
            commissionInputer = handler.doubleInput();
        }
        setCommission(commissionInputer);
    }

    public Commissioned(Employee original){
        standardCommissionedAgenda();
        this.name = original.getName();
        this.adress = original.getAdress();
        this.salary = original.getSalary();
        this.toReceive = this.salary;
        this.registerNumber = original.getRegisterNumber();
        this.paymentMethod = original.getPaymentMethod();
        this.syndicated = original.getSyndicated();
        if(this.syndicated){
            this.syndicateRegister = original.getSyndicateRegister();
            this.unionFee = original.getUnionFee();
            this.serviceTax = original.getServiceTax();
        }

        this.saleResults = 0;
    }

    public Commissioned(Commissioned original, int flag){ // flag has no especific function, used only to change the constructor's signature.
        this.name = original.name;
        this.adress = original.adress;
        this.salary = original.salary;
        this.toReceive = original.toReceive;
        this.registerNumber = original.registerNumber;
        this.paymentMethod = original.paymentMethod;
        this.syndicated = original.syndicated;
        this.syndicateRegister = original.syndicateRegister;
        this.unionFee = original.unionFee;
        this.serviceTax = original.serviceTax;
        this.saleResults = original.saleResults;
        this.commission = original.commission;
        this.agenda = original.agenda.clone();
    }

    public void setCommission(double commission){
        this.commission = commission;
    }

    public double getCommission(){
        return this.commission;
    }

    public void inputSaleResult(){
        System.out.println("Please type the sale result:");
        double result = handler.doubleInput();
        while(result < 0){
            System.out.println("Please type a valid number.");
            result = handler.doubleInput();
        }
        this.saleResults += result;
    }

    public void standardCommissionedAgenda(){
        this.agenda.setEachTwoWeeks(true);
        this.agenda.setWeekDay(6);
    }

    public Employee clone(){
        Employee clone = new Commissioned(this,0);
        return clone;
    }
}