package employees;

import main.Sale;

public class Salaried extends Employee implements Prototype{

    public Salaried(){
        this.toReceive = 0;
        standardSalariedAgenda();

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
    }

    public Salaried(Employee original){
        standardSalariedAgenda();
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
    }

    public Salaried(Salaried original, int flag){// flag has no especific function, used only to change the constructor's signature.
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
        this.agenda = original.agenda.clone();
    }

    public void standardSalariedAgenda(){
        this.agenda.setMonthly(true);
        this.agenda.setLastDay(true);
    }

    public Employee clone(){
        Employee clone = new Salaried(this,0);
        return clone;
    }
}