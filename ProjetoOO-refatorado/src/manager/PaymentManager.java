package manager;

import employees.*;

public class PaymentManager {
    double value;

    public void payroll() {
        EmployeesData.emptyRedo();
        EmployeesData.copyRegister();
        for(Employee current : EmployeesData.register){
            if(current.agenda.isWeekly()){
                value = weekPayment(current);
            }
            else if(current.agenda.isEachTwoWeeks()){
                if(current.agenda.isWeekToBePaid()){
                    value = twoWeekPayment(current);
                }
                else{
                    current.agenda.setWeekToBePaid(true);
                    value = 0;
                }
            }
            else if(current.agenda.isMonthly()){
                value = monthPayment(current);
            }
            if(value > 0) displayPayment(current,value);
        }
    }

    public double weekPayment(Employee current) {
        Context context = new Context(new WeekPayment());
        return context.execute(current);
    }

    public double twoWeekPayment(Employee current) {
        Context context = new Context(new TwoWeekPayment());
        return context.execute(current);
    }

    public double monthPayment(Employee current) {
        Context context = new Context(new MonthlyPayment());
        return context.execute(current);
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
}
