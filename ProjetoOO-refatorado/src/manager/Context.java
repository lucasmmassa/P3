package manager;

import employees.Employee;

public class Context {
    private PaymentStrategy strategy;

    public Context(PaymentStrategy strategy){
        this.strategy = strategy;
    }

    public double execute(Employee current){
        return strategy.pay(current);
    }
}
