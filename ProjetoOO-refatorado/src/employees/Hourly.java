package employees;

import utils.*;

public class Hourly extends Employee implements Prototype{

    public Timecard timecard = new Timecard();
    private double hourSalary;

    public Hourly(){
        getConstructionInfo();
    }

    @Override
    void getSpecificInfo() {
        standardHourlyAgenda();
        hourSalary = salary/220.0;
    }

    public Hourly(Employee original){
        standardHourlyAgenda();
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

    public Hourly(Hourly original, int flag){// flag has no especific function, used only to change the constructor's signature.
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
        this.timecard = original.timecard.clone();
        this.hourSalary = original.hourSalary;
        this.agenda = original.agenda.clone();
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

    public Employee clone(){
        Employee clone = new Hourly(this, 0);
        return clone;
    }
}