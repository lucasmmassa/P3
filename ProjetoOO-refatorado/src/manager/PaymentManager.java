package manager;

import employees.*;

public class PaymentManager {

    public void payroll() {
        EmployeesManager.emptyRedo();
        EmployeesManager.copyRegister();
        for(Employee current : EmployeesManager.register){
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
        if(current.agenda.getWeekDay() == EmployeesManager.calendar.weekday) {
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

        if(current.agenda.getWeekDay() == EmployeesManager.calendar.weekday) {
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
        if(current.agenda.isLastDay() &&
                EmployeesManager.calendar.day == (EmployeesManager.calendar.daysPerMonth[EmployeesManager.calendar.month] - 1) &&
                EmployeesManager.calendar.weekday == 7){
            return true;
        }
        else if(current.agenda.isLastDay() &&
                (EmployeesManager.calendar.day == EmployeesManager.calendar.daysPerMonth[EmployeesManager.calendar.month])){
            return true;
        }
        else if(EmployeesManager.calendar.day == (current.agenda.getMonthDay() - 1) && EmployeesManager.calendar.day == 7){
            return true;
        }
        else if(EmployeesManager.calendar.day == current.agenda.getMonthDay()){
            return true;
        }
        return false;
    }
}
