package manager;

import employees.*;
import java.util.Scanner;

public class AgendaManager {
    Scanner input = new Scanner(System.in);
    EmployeesData data = EmployeesData.getSingleInstance();

    public void newAgenda() {
        String edited = data.nameInput();
        Employee auxiliar = data.findEmployee(edited);

        if(auxiliar == null){
            System.out.println("Sorry, there is no employee with the name typed.");
            return;
        }

        System.out.println("Please type the new agenda:");
        String agenda = input.nextLine();

        agenda = agenda.toLowerCase();

        if(agenda.contains("semanal 1")){
            String[] info = agenda.split(" ");
            newWeekly(auxiliar,info[2]);
        }
        else if(agenda.contains("semanal 2")){
            String[] info = agenda.split(" ");
            newEachTwoWeeks(auxiliar,info[2]);
        }
        else if(agenda.contains("mensal")){
            String[] info = agenda.split(" ");
            newMonthly(auxiliar,info[1]);
        }
    }

    private void newWeekly(Employee current, String info) {
        current.agenda.setLastDay(false);
        current.agenda.setMonthly(false);
        current.agenda.setEachTwoWeeks(false);
        current.agenda.setWeekly(true);

        switchInfo(current,info);
    }

    private void newEachTwoWeeks(Employee current, String info) {
        current.agenda.setLastDay(false);
        current.agenda.setMonthly(false);
        current.agenda.setEachTwoWeeks(true);
        current.agenda.setWeekly(false);
        current.agenda.setWeekToBePaid(false);

        switchInfo(current,info);
    }

    private void newMonthly(Employee current, String info) {
        if(info.equals("$")){
            current.agenda.setLastDay(true);
            current.agenda.setMonthly(true);
            current.agenda.setEachTwoWeeks(false);
            current.agenda.setWeekly(false);
        }
        else{
            int day = data.handler.toInteger(info);
            if(1 <= day && day <= 28){
                current.agenda.setMonthDay(day);
                current.agenda.setLastDay(false);
                current.agenda.setMonthly(true);
                current.agenda.setEachTwoWeeks(false);
                current.agenda.setWeekly(false);
            }
        }
    }

    private void switchInfo(Employee current, String info){
        switch (info){
            case "segunda":
                current.agenda.setWeekDay(2);
                break;
            case "terca":
                current.agenda.setWeekDay(3);
                break;
            case "quarta":
                current.agenda.setWeekDay(4);
                break;
            case "quinta":
                current.agenda.setWeekDay(5);
                break;
            case "sexta":
                current.agenda.setWeekDay(6);
                break;
            case "sabado":
                current.agenda.setWeekDay(7);
                break;
        }
    }
}
