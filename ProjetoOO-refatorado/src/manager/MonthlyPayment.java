package manager;

import employees.Commissioned;
import employees.Employee;
import employees.Hourly;
import employees.Salaried;

public class MonthlyPayment implements PaymentStrategy {
    double value;

    public double pay(Employee current) {
        boolean toPay = isToPay(current);
        if(toPay) {
            if (current instanceof Hourly) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                current.setToReceive(0);
            }
            else if (current instanceof Salaried) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
            }
            else if (current instanceof Commissioned) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                value += ((Commissioned) current).saleResults * ((Commissioned) current).getCommission();
                ((Commissioned) current).saleResults = 0;
            }
            return value;
        }
        return 0;
    }

    public boolean isToPay(Employee current) {
        if(current.agenda.isLastDay() &&
                EmployeesData.calendar.day == (EmployeesData.calendar.daysPerMonth[EmployeesData.calendar.month] - 1) &&
                EmployeesData.calendar.weekday == 7){
            return true;
        }
        else if(current.agenda.isLastDay() &&
                (EmployeesData.calendar.day == EmployeesData.calendar.daysPerMonth[EmployeesData.calendar.month])){
            return true;
        }
        else if(EmployeesData.calendar.day == (current.agenda.getMonthDay() - 1) && EmployeesData.calendar.day == 7){
            return true;
        }
        else if(EmployeesData.calendar.day == current.agenda.getMonthDay()){
            return true;
        }
        return false;
    }
}
