package main;
import manager.SaleResultManager;

public class Sale implements MainCommand{
    SaleResultManager manager = new SaleResultManager();
    public void execute(){
        System.out.println("Inputing sale result.");
        manager.saleResult();
    }
}
