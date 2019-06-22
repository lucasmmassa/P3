package manager;

import java.util.Scanner;
import employees.*;
import java.util.List;
import java.util.ArrayList;
import dates.*;
import utils.ExceptionsHandler;
import utils.UndoRedo;

public class EmployeesManager implements EmployeesManagerInterface{
    ExceptionsHandler handler = new ExceptionsHandler();
    public Date calendar;
    static Scanner input = new Scanner(System.in);
    static String trash;
    private UndoRedo undoRedo;

    private int employeesAnnualCounter;
    private int employeesCounter;

    private List<Employee> register;

    public EmployeesManager(){
        employeesAnnualCounter = 0;
        employeesCounter = 0;
        register = new ArrayList<Employee>();
        calendar = new Date();
        undoRedo = new UndoRedo();
    }

    public void addEmployee(){
        emptyRedo();
        copyRegister();
        System.out.println("Please choose the worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = handler.integerInput();
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
        this.employeesAnnualCounter ++;
        this.employeesCounter ++;
    }

    public void addHourly(){
        int registerNumber = (calendar.year*1000)+employeesAnnualCounter;
        Employee newOne = new Hourly();
        newOne.setRegisterNumber(registerNumber);
        register.add(newOne);
    }

    public void addSalaried(){
        int registerNumber = (calendar.year*1000)+employeesAnnualCounter;
        Employee newOne = new Salaried();
        newOne.setRegisterNumber(registerNumber);
        register.add(newOne);
    }

    public void addCommissioned(){
        int registerNumber = (calendar.year*1000)+employeesAnnualCounter;
        Employee newOne = new Commissioned();
        newOne.setRegisterNumber(registerNumber);
        register.add(newOne);
    }

    public void removeEmployee(){
        boolean found = false;
        emptyRedo();
        copyRegister();
        System.out.println("Please type the employee's name:");
        String removed = input.nextLine();
       
        for(Employee current : register){
            if(removed.equals(current.getName())){
                found = true;
                register.remove(current);
                System.out.println("The employee has been removed.\n");
                this.employeesCounter--;
                return;
            }
        }

        if(!found){
            System.out.println("Sorry, there is no employee with the name typed.");
        }
    }

    public void editEmployee(){
        emptyRedo();
        copyRegister();
        System.out.println("Please type the name of the employee whose info you want to edit:");
        String edited = input.nextLine();
        Employee auxiliar = findEmployee(edited);
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

        int choice = handler.integerInput();
        while(choice < 1 || choice > 10){
            System.out.println("Please choose one of the options above.");
            choice = handler.integerInput();
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
                int newRegister = handler.integerInput();
                while(newRegister < 0){
                    System.out.println("Please type a valid number.");
                    newRegister = handler.integerInput();
                }
                auxiliar.setRegisterNumber(newRegister);
                System.out.println("");
                break;

            case 4:
                System.out.println("Please enter the employee's new salary:");
                double newSalary = handler.doubleInput();
                while(newSalary < 998){
                    System.out.println("Please type a value that is equal or bigger than the minimum wage.");
                    newSalary = handler.doubleInput();
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
                    double commission = handler.doubleInput();
                    while(commission < 0 || commission > 1){
                        System.out.println("Please type a valid number.");
                        commission = handler.doubleInput();
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
                    int newSyndicate = handler.integerInput();
                    while(newSyndicate < 0){
                        System.out.println("Please type a valid number.");
                        newSyndicate = handler.integerInput();
                    }
                    auxiliar.setSyndicateRegister(newSyndicate);
                    System.out.println(""); 
                }
                break;
            
            case 7:
                if(auxiliar.getSyndicated()){
                    System.out.println("Please type the new syndicate register rumber:");
                    int newSyndicate = handler.integerInput();
                    while(newSyndicate < 0){
                        System.out.println("Please type a valid number.");
                        newSyndicate = handler.integerInput();
                    }
                    auxiliar.setSyndicateRegister(newSyndicate);
                    System.out.println("");
                }
                else{
                    System.out.println("Sorry, this employee is not syndicated.");
                }
                break;

            case 8:
                typeSwitch(auxiliar);
                break;

            case 9:
                if(auxiliar.getSyndicated()){
                    System.out.println("Please type the new union fee: (value between 0 and 1)");
                    double newFee = handler.doubleInput();
                    while(newFee < 0 || newFee > 1){
                        System.out.println("Please type a valid number.");
                        newFee = handler.doubleInput();
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

    public void showList(){
        for(Employee current : register){
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

    public void typeSwitch(Employee original){
        emptyRedo();
        copyRegister();
        System.out.println("Please type the new worker type:");
        System.out.println("1-Hourly 2-Salaried 3-Commissioned");
        int type = handler.integerInput();
        while(type < 1 || type > 3){
            System.out.println("Please choose one of the options above.");
            type = handler.integerInput();
        }

        switch(type){
            case 1:
                switchToHourly(original);
                break;
            case 2:
                switchToSalaried(original);
                break;
            case 3:
                switchToCommissioned(original);
                break;
        }
    }

    public void switchToHourly(Employee original){
        if(original instanceof Hourly){
            System.out.println("The employee is already a hourly one.");
        }
        else{
            Hourly switched = new Hourly(original);
            register.remove(original);
            register.add(switched);
        }
    }

    public void switchToSalaried(Employee original){
        if(original instanceof Salaried){
            System.out.println("This employee is already a salaried one.");
        }
        else{
            Salaried switched = new Salaried(original);
            register.remove(original);
            register.add(switched);
        }
    }

    public void switchToCommissioned(Employee original){
        if(original instanceof Commissioned){
            System.out.println("This employee is already a commissioned one.");
        }
        else{
            Commissioned switched = new Commissioned(original);
            System.out.println("Please type the employee's commission:");
            double newCommission = handler.doubleInput();
            while(newCommission < 0 || newCommission >1){
                System.out.println("Please type a valid number.");
                newCommission = handler.doubleInput();
            }
            switched.setCommission(newCommission);
            register.remove(original);
            register.add(switched);
        }
    }

    public Employee findEmployee(String name){
        for(Employee current : register){
            if(name.equals(current.getName())){
                return current;
            }
        }
        return null;
    }

    public int getEmployeesAnnualCounter() {
        return employeesAnnualCounter;
    }

    public void setEmployeesAnnualCounter(int employeesAnnualCounter) {
        this.employeesAnnualCounter = employeesAnnualCounter;
    }

    public int getEmployeesCounter() {
        return employeesCounter;
    }

    public void setEmployeesCounter(int employeesCounter) {
        this.employeesCounter = employeesCounter;
    }

    public void timeCard() {
        emptyRedo();
        copyRegister();
        System.out.println("Please type the name of the employee whose info you want to edit:");
        String edited = input.nextLine();
        Employee auxiliar = findEmployee(edited);
        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }
        int weekDay = this.calendar.weekday;

        if(weekDay == 1){
            System.out.println("It is not allowed to work on sundays.");
            return;
        }

        if(!(auxiliar instanceof Hourly)){
            System.out.println("Sorry, this employee is not a hourly one.");
            return;
        }

        ((Hourly)auxiliar).timecardInput(weekDay);
    }

    public void saleResult() {
        emptyRedo();
        copyRegister();
        System.out.println("Please type the name of the employee whose info you want to edit:");
        String edited = input.nextLine();
        Employee auxiliar = findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        if(!(auxiliar instanceof Commissioned)){
            System.out.println("Sorry, this employee is not a commissioned one.");
            return;
        }

        ((Commissioned)auxiliar).inputSaleResult();
    }

    public void serviceTax() {
        emptyRedo();
        copyRegister();
        System.out.println("Please type the name of the employee whose info you want to edit:");
        String edited = input.nextLine();
        Employee auxiliar = findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Please type the service tax: (value between 0 and 1)");
        double tax = handler.doubleInput();
        while(tax < 0 || tax > 1){
            System.out.println("Please type a valid number.");
            tax = handler.doubleInput();
        }

        auxiliar.setServiceTax(tax);
    }

    public void resetServiceTax() {
        for(Employee current : register){
            current.setServiceTax(0);
        }
    }

    public void payroll() {
        emptyRedo();
        copyRegister();
        for(Employee current : register){
            if(current.agenda.isWeekly()){
                weekPayment(current);
            }
            else if(current.agenda.isEachTwoWeeks()){
                if(current.agenda.isWeekToBePaid()){
                    twoWeekPayment(current);
                }
                else{
                    current.agenda.setWeekToBePaid(true);
                }
            }
            else if(current.agenda.isMonthly()){
                monthPayment(current);
            }
        }
    }

    public void weekPayment(Employee current) {
        if(current.agenda.getWeekDay() == calendar.weekday) {
            double value;
            if (current instanceof Hourly) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
                current.setToReceive(0);
            }
            else if (current instanceof Salaried) {
                value = current.getToReceive()/4;
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
            }
            else if (current instanceof Commissioned) {
                value = current.getToReceive()/4;
                value -= value*(current.getUnionFee() + current.getServiceTax());
                value += ((Commissioned) current).saleResults * ((Commissioned) current).getCommission();
                ((Commissioned) current).saleResults = 0;
                displayPayment(current,value);
            }
        }
    }

    public void twoWeekPayment(Employee current) {

        if(current.agenda.getWeekDay() == calendar.weekday) {
            double value;
            if (current instanceof Hourly) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
                current.setToReceive(0);
            }
            else if (current instanceof Salaried) {
                value = current.getToReceive()/2;
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
            }
            else if (current instanceof Commissioned) {
                value = current.getToReceive()/2;
                value -= value*(current.getUnionFee() + current.getServiceTax());
                value += ((Commissioned) current).saleResults * ((Commissioned) current).getCommission();
                ((Commissioned) current).saleResults = 0;
                displayPayment(current,value);
            }
        }
    }

    public void monthPayment(Employee current) {
        boolean toPay = isToPay(current);
        if(toPay) {
            double value;
            if (current instanceof Hourly) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
                current.setToReceive(0);
            }
            else if (current instanceof Salaried) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                displayPayment(current,value);
            }
            else if (current instanceof Commissioned) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                value += ((Commissioned) current).saleResults * ((Commissioned) current).getCommission();
                ((Commissioned) current).saleResults = 0;
                displayPayment(current,value);
            }
        }
    }

    public void displayPayment(Employee current, double value) {
        int method = current.getPaymentMethod();
        switch (method){
            case 1:
                System.out.println(current.getName() + " - " + value + " - Check by mail.");
                break;
            case 2:
                System.out.println(current.getName() + " - " + value + " - Check in hands.");
                break;
            case 3:
                System.out.println(current.getName() + " - " + value + " - Bank account.");
                break;
        }
    }

    public boolean isToPay(Employee current) {
        if(current.agenda.isLastDay() && calendar.day == (calendar.daysPerMonth[calendar.month] - 1) && calendar.weekday == 7){
            return true;
        }
        else if(current.agenda.isLastDay() && (calendar.day == calendar.daysPerMonth[calendar.month])){
            return true;
        }
        else if(calendar.day == (current.agenda.getMonthDay() - 1) && calendar.day == 7){
            return true;
        }
        else if(calendar.day == current.agenda.getMonthDay()){
            return true;
        }
        return false;
    }

    public void undo(){
        if(undoRedo.undoStack.empty()){
            return;
        }
        undoRedo.redoStack.push(register);
        register = undoRedo.undoStack.pop();
    }

    public void redo(){
        if(undoRedo.redoStack.empty()){
            return;
        }
        copyRegister();
        register = undoRedo.redoStack.pop();
    }

    public void emptyRedo(){
        List<Employee> auxiliar = new ArrayList<Employee>();
        while(!undoRedo.redoStack.empty()){
            auxiliar = undoRedo.redoStack.pop();
        }
    }

    public void copyRegister(){

        List<Employee> copy = new ArrayList<Employee>();

        for(Employee current : register){
            if(current instanceof  Hourly){
                Employee auxiliar = new Hourly(current);
                ((Hourly)auxiliar).timecard.setArrivalHour(((Hourly)current).timecard.getArrivalHour());
                ((Hourly)auxiliar).timecard.setArrivalMinute(((Hourly)current).timecard.getArrivalMinute());
                ((Hourly)auxiliar).timecard.setExitHour(((Hourly)current).timecard.getExitHour());
                ((Hourly)auxiliar).timecard.setExitMinute(((Hourly)current).timecard.getExitMinute());
                auxiliar.agenda.setWeekly(current.agenda.isWeekly());
                auxiliar.agenda.setEachTwoWeeks(current.agenda.isEachTwoWeeks());
                auxiliar.agenda.setMonthly(current.agenda.isMonthly());
                auxiliar.agenda.setLastDay(current.agenda.isLastDay());
                auxiliar.agenda.setWeekToBePaid(current.agenda.isWeekToBePaid());
                auxiliar.agenda.setWeekDay(current.agenda.getWeekDay());
                auxiliar.agenda.setMonthDay(current.agenda.getMonthDay());

                copy.add(auxiliar);
            }
            else if(current instanceof  Salaried){
                Employee auxiliar = new Salaried(current);
                auxiliar.agenda.setWeekly(current.agenda.isWeekly());
                auxiliar.agenda.setEachTwoWeeks(current.agenda.isEachTwoWeeks());
                auxiliar.agenda.setMonthly(current.agenda.isMonthly());
                auxiliar.agenda.setLastDay(current.agenda.isLastDay());
                auxiliar.agenda.setWeekToBePaid(current.agenda.isWeekToBePaid());
                auxiliar.agenda.setWeekDay(current.agenda.getWeekDay());
                auxiliar.agenda.setMonthDay(current.agenda.getMonthDay());

                copy.add(auxiliar);
            }
            else if(current instanceof Commissioned){
                Employee auxiliar = new Commissioned(current);
                ((Commissioned)auxiliar).setCommission(((Commissioned)current).getCommission());
                ((Commissioned) auxiliar).saleResults = ((Commissioned) current).saleResults;
                auxiliar.agenda.setWeekly(current.agenda.isWeekly());
                auxiliar.agenda.setEachTwoWeeks(current.agenda.isEachTwoWeeks());
                auxiliar.agenda.setMonthly(current.agenda.isMonthly());
                auxiliar.agenda.setLastDay(current.agenda.isLastDay());
                auxiliar.agenda.setWeekToBePaid(current.agenda.isWeekToBePaid());
                auxiliar.agenda.setWeekDay(current.agenda.getWeekDay());
                auxiliar.agenda.setMonthDay(current.agenda.getMonthDay());

                copy.add(auxiliar);
            }
        }
        undoRedo.undoStack.push(copy);
    }

    public void newAgenda() {
        System.out.println("Please type the name of the employee whose info you want to edit:");
        String edited = input.nextLine();
        Employee auxiliar = findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Please type the new agenda:");
        String agenda = input.nextLine();

        agenda = agenda.toLowerCase();

        if(agenda.contains("semanal 1")){
            String[] info = agenda.split(" ");
            newWeekly(auxiliar,info[2]);
        }
        else if(agenda.contains("semana 2")){
            String[] info = agenda.split(" ");
            newEachTwoWeeks(auxiliar,info[2]);
        }
        else if(agenda.contains("mensal")){
            String[] info = agenda.split(" ");
            newMonthly(auxiliar,info[1]);
        }
    }

    public void newWeekly(Employee current, String info) {
        switch (info){
            case "segunda":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(2);
                break;
            case "terca":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(3);
                break;
            case "quarta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(4);
                break;
            case "quinta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(5);
                break;
            case "sexta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(6);
                break;
            case "sabado":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(true);
                current.agenda.setWeekDay(7);
                break;
        }
    }

    public void newEachTwoWeeks(Employee current, String info) {
        switch (info){
            case "segunda":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(2);
                current.agenda.setWeekToBePaid(false);
                break;
            case "terca":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(3);
                current.agenda.setWeekToBePaid(false);
                break;
            case "quarta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(4);
                current.agenda.setWeekToBePaid(false);
                break;
            case "quinta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(5);
                current.agenda.setWeekToBePaid(false);
                break;
            case "sexta":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(6);
                current.agenda.setWeekToBePaid(false);
                break;
            case "sabado":
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(false);
                current.agenda.setEachTwoWeeks(true);
                current.agenda.setWeekly(false);
                current.agenda.setWeekDay(7);
                current.agenda.setWeekToBePaid(false);
                break;
        }
    }

    public void newMonthly(Employee current, String info) {
        if(info == "$"){
            current.agenda.setLastDay(true);
            current.agenda.setMonthly(true);
            current.agenda.setEachTwoWeeks(false);
            current.agenda.setWeekly(false);
        }
        else{
            int day = handler.toInteger(info);
            if(1 <= day && day <= 28){
                current.agenda.setMonthDay(day);
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(true);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(false);
            }
        }
    }
}