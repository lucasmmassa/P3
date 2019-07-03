package utils;

import java.util.Scanner;
import utils.ExceptionsHandler;

public class Timecard{

    Scanner input = new Scanner(System.in);
    ExceptionsHandler handler = new ExceptionsHandler();
    private int arrivalHour;
    private int arrivalMinute;
    private int exitHour;
    private int exitMinute;

    public double dailyWorkingPeriod(){ //returns the amount of hours that were spent working.
        double amount;
        double hours;
        double minutes;

        hours = exitHour - arrivalHour;
        minutes = exitMinute - arrivalMinute;

        if(minutes < 0){
            minutes += 60;
            hours --;
        }

        amount = hours + (minutes/60.0);

        return amount;
    }

    public int getArrivalHour(){
        return arrivalHour;
    }

    public void setArrivalHour(int arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public int getArrivalMinute() {
        return arrivalMinute;
    }

    public void setArrivalMinute(int arrivalMinute) {
        this.arrivalMinute = arrivalMinute;
    }

    public int getExitHour() {
        return exitHour;
    }

    public void setExitHour(int exitHour) {
        this.exitHour = exitHour;
    }

    public int getExitMinute() {
        return exitMinute;
    }

    public void setExitMinute(int exitMinute) {
        this.exitMinute = exitMinute;
    }

    public void arrivalTime() {
        System.out.println("Arrival time.");
        System.out.println("Please type the hour:");
        int hour = handler.integerInput();
        while(hour < 0 || hour > 22){
            System.out.println("Please type a valid hour");
            hour = handler.integerInput();
        }
        this.arrivalHour = hour;
        System.out.println("Please type the minutes:");
        int minutes = handler.integerInput();
        while(minutes < 0 || minutes > 59){
            System.out.println("Please type a valid minute.");
            minutes = handler.integerInput();
        }
        this.arrivalMinute = minutes;
    }

    public void exitTime() {
        System.out.println("Exit time.");
        System.out.println("Please type the hour:");
        int hour = handler.integerInput();
        while(hour < 0 || hour > 22 || hour <= this.getArrivalHour()){
            System.out.println("Please type a valid hour");
            hour = handler.integerInput();
        }
        this.exitHour = hour;
        System.out.println("Please type the minutes:");
        int minutes = handler.integerInput();
        while(minutes < 0 || minutes > 59){
            System.out.println("Please type a valid minute.");
            minutes = handler.integerInput();
        }
        this.exitMinute = minutes;
    }
}
