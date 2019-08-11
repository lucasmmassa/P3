package employees;

import utils.*;
import java.util.Scanner;
import java.util.Stack;

public class Employee{

    Scanner input  = new Scanner(System.in);
    ExceptionsHandler handler = new ExceptionsHandler();
    static String trash;
    public Agenda agenda = new Agenda();
    protected double toReceive;
    protected String name;
    protected String adress;
    protected int registerNumber;
    protected double salary;
    protected boolean syndicated;
    protected int paymentMethod;
    protected int syndicateRegister;
    protected double unionFee;
    protected double serviceTax;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }   

    public void setAdress(String adress){
        this.adress = adress;
    }
    public String getAdress(){
        return this.adress;
    }

    public void setRegisterNumber(int registerNumber){
        this.registerNumber = registerNumber;
    }
    public int getRegisterNumber(){
        return this.registerNumber;
    }

    public void setSalary(double salary){
        while(salary<=0){
            System.out.println("Invalid salary. Please type again.");
            salary = input.nextDouble();
        }
        this.salary = salary;
    }
    public double getSalary(){
        return this.salary;
    }
    
    public void setSyndicated(){
        System.out.println("Use the following options:");
        System.out.println("1->Yes 0->No");
        
        int auxiliar = handler.integerInput();

        while(auxiliar < 0 || 1 < auxiliar){
            System.out.println("Invalid choice. Please type again.");
            auxiliar = handler.integerInput();
        }

        if(auxiliar == 1){
            this.syndicated = true;
        }
        else{
            this.syndicated = false;
        }
    }
    public boolean getSyndicated(){
        return this.syndicated;
    }

    public void setSyndicateRegister(int syndicateRegister){
        while(syndicateRegister < 0){
            System.out.println("Invalid number. Please type again.");
            syndicateRegister = input.nextInt();
        }
        this.syndicateRegister = syndicateRegister;
    }

    public int getSyndicateRegister(){
        return this.syndicateRegister;
    }

    public void setPaymentMethod(){
        System.out.println("Use the following options:");
        System.out.println("1->Check by Mail 2->Check in hands 3->Bank account");

        int auxiliar = handler.integerInput();

        while(auxiliar < 1 || 3 < auxiliar){
            System.out.println("Invalid choice. Please type again.");
            auxiliar = handler.integerInput();
        }
        this.paymentMethod = auxiliar;
    }
    public int getPaymentMethod(){
        return this.paymentMethod;
    }

    public void setUnionFee(double unionFee){
        this.unionFee = unionFee;
    }
    public double getUnionFee(){
        return this.unionFee;
    }

    public void setToReceive(double value){
        this.toReceive = value;
    }

    public double getToReceive(){
        return this.toReceive;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }


}