package main;
import manager.AgendaManager;

public class Agenda implements MainCommand{
    AgendaManager manager = new AgendaManager();
    public void execute() {
        System.out.println("Adding new payment agenda.\n");
        manager.newAgenda();
    }
}
