package main;
import manager.ServiceTaxManager;

public class Service implements MainCommand{
    ServiceTaxManager manager = new ServiceTaxManager();
    public void execute() {
        System.out.println("Inputing service tax.");
        manager.serviceTax();
    }
}
