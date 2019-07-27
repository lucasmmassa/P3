package employees;

import utils.*;

public class Hourly extends Employee{

    public Timecard timecard = new Timecard();
    private double hourSalary;

    public Hourly(){
        this.defaultPayday = true;
        this.toReceive = 0;
        standardHourlyAgenda();

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

        hourSalary = salary/220.0;

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

    public Hourly(Employee original){
        standardHourlyAgenda();
        this.defaultPayday = original.getDefaultValue();
        this.name = original.getName();
        this.adress = original.getAdress();
        this.salary = original.getSalary();
        this.toReceive = 0;
        this.hourSalary = salary/220.0;
        this.registerNumber = original.getRegisterNumber();
        this.paymentMethod = original.getPaymentMethod();
        this.syndicated = original.getSyndicated();
        if(this.syndicated){
            this.syndicateRegister = original.getSyndicateRegister();
            this.unionFee = original.getUnionFee();
            this.serviceTax = original.getServiceTax();
        }
    }

    public void calculateDailyWage(){

        double period = this.timecard.dailyWorkingPeriod();

        if(period > 8){
            double extra = period - 8;

            this.toReceive += (8*hourSalary) + (extra*1.5*hourSalary);
        }
        else{
            this.toReceive += period*hourSalary;
        }

    }

    public void calculateDailyWageSaturday(){

        double period = this.timecard.dailyWorkingPeriod();

        if(period > 4){
            double extra = period - 4;

            this.toReceive += (4*hourSalary) + (extra*1.5*hourSalary);
        }
        else{
            this.toReceive += period*hourSalary;
        }

    }

    public void standardHourlyAgenda(){
        this.agenda.setWeekly(true);
        this.agenda.setWeekDay(6);
    }

    public void timecardInput(int weekDay) {
        System.out.println("Please select the time to input:");
        System.out.println("1-arrival 0-exit");
        int choice = handler.integerInput();
        while(choice < 0 || choice > 1){
            System.out.println("Please choose one of the options above.");
            choice = handler.integerInput();
        }
        if(choice == 1){
            this.timecard.arrivalTime();
        }
        else if(choice == 0){
            this.timecard.exitTime();
            if(weekDay == 7){
                calculateDailyWageSaturday();
            }
            else{
                calculateDailyWage();
            }
        }
    }

    public void setHourSalary(double hourSalary) {
        this.hourSalary = hourSalary;
    }
}