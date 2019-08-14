package employees;

import main.Sale;

public class Salaried extends Employee implements Prototype{

    public Salaried(){
        getConstructionInfo();
    }

    @Override
    void getSpecificInfo() {
        standardSalariedAgenda();
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