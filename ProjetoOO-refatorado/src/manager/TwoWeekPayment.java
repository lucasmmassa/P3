package manager;

import employees.Commissioned;
import employees.Employee;
import employees.Hourly;
import employees.Salaried;

public class TwoWeekPayment implements PaymentStrategy{
    double value;

    public double pay(Employee current) {
        if(current.agenda.getWeekDay() == EmployeesData.calendar.weekday) {
            if (current instanceof Hourly) {
                value = current.getToReceive();
                value -= value*(current.getUnionFee() + current.getServiceTax());
                current.setToReceive(0);
            }
            else if (current instanceof Salaried) {
                value = current.getToReceive()/2;
                value -= value*(current.getUnionFee() + current.getServiceTax());
            }
            else if (current instanceof Commissioned) {
                value = current.getToReceive()/2;
                value -= value*(current.getUnionFee() + current.getServiceTax());
                value += ((Commissioned) current).saleResults * ((Commissioned) current).getCommission();
                ((Commissioned) current).saleResults = 0;
            }
            return value;
        }
        return 0;
    }
}
