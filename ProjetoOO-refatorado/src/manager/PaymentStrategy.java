package manager;

import employees.Employee;

public interface PaymentStrategy {
    public double pay(Employee current);
}
