package dates;

import java.util.Scanner;
import utils.ExceptionsHandler;

public class Date{
    static Scanner input = new Scanner(System.in);
    ExceptionsHandler handler = new ExceptionsHandler();
    public int day;
    public int month;
    public int year;
    public int[] daysPerMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public int weekday;

    public Date(){
        System.out.println("Please type the current date.");
        System.out.println("Year:");
        year = handler.integerInput();
        if(bissextYear()){
            this.daysPerMonth[2] = 29;
        }
        else{
            this.daysPerMonth[2] = 28;
        }
        System.out.println("Month:");
        month = handler.integerInput();
        while(month < 1 || month > 12){
            System.out.println("Please type a valid month.");
            month = handler.integerInput();
        }
        System.out.println("Day:");
        day = handler.integerInput();
        while (day < 1 || day > daysPerMonth[month]){
            System.out.println("Please type a valid day.");
            day = handler.integerInput();
        }
        System.out.println("\nPlease type the current weekday:");
        System.out.println("1-Sunday 2-Monday 3-Tuesday 4-Wednesday 5-Thursday 6-Friday 7-Saturday");
        weekday = handler.integerInput();
        while(weekday < 1 || weekday > 7){
            System.out.println("Please type a valid weekday.");
            weekday = handler.integerInput();
        }
    }

    public boolean dateManager() {
        this.weekday ++;
        if(weekday > 7){
            weekday = 1;
        }
        this.day ++;
        if(this.day > daysPerMonth[this.month]){
            this.day = 1;
            this.month++;
        }
        if(this.month > 12){
            this.month = 1;
            this.year++;
            if(bissextYear()){
                this.daysPerMonth[2] = 29;
            }
            else{
                this.daysPerMonth[2] = 28;
            }
            return true;  //returns true if there is a new year.
        }
        return false;
    }

    public boolean bissextYear() {
        if(this.year % 400 == 0 || (this.year % 4 == 0 && this.year % 100 != 0)){
            return true;
        }
        return false;
    }
}
