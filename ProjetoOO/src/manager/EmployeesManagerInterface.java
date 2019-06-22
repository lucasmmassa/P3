package manager;

import employees.Employee;

public interface EmployeesManagerInterface{

    void addEmployee();

    void addHourly();

    void addSalaried();

    void addCommissioned();

    void removeEmployee();

    void editEmployee();

    void showList();

    void typeSwitch(Employee original);

    void switchToHourly(Employee original);

    void switchToSalaried(Employee original);

    void switchToCommissioned(Employee original);

    void timeCard();

    void saleResult();

    void serviceTax();

    void resetServiceTax();

    void payroll();

    void weekPayment(Employee current);

    void twoWeekPayment(Employee current);

    void monthPayment(Employee current);

    void displayPayment(Employee current, double value);

    boolean isToPay(Employee current);

    void undo();

    void redo();

    void emptyRedo();

    void copyRegister();

    void newAgenda();

    void newWeekly(Employee current, String info);

    void newEachTwoWeeks(Employee current, String info);

    void newMonthly(Employee current, String info);
}