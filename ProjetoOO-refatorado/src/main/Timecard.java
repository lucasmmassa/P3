package main;
import manager.TimeCardManager;

public class Timecard implements MainCommand{
    TimeCardManager manager = new TimeCardManager();
    public void execute(){
       System.out.println("Inputing timecard info.");
       manager.timeCard();
    }
}
