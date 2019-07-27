package manager;

import java.util.Scanner;
import employees.*;
import java.util.List;
import java.util.ArrayList;
import dates.*;
import utils.ExceptionsHandler;
import utils.UndoRedo;

public class EmployeesManager{
    static ExceptionsHandler handler = new ExceptionsHandler();
    public static Date calendar;
    static Scanner input = new Scanner(System.in);
    static UndoRedo undoRedo;

    static int employeesAnnualCounter;
    static int employeesCounter;

    public static List<Employee> register;

    public EmployeesManager(){
        employeesAnnualCounter = 0;
        employeesCounter = 0;
        register = new ArrayList<Employee>();
        calendar = new Date();
        undoRedo = new UndoRedo();
    }

    public static Employee findEmployee(String name){
        for(Employee current : register){
            if(name.equals(current.getName())){
                return current;
            }
        }
        return null;
    }

    public static String nameInput(){
        System.out.println("Please type the employee's name:");
        return input.nextLine();
    }

    public static void restartCounter(){
        employeesAnnualCounter = 0;
    }

    public static void undo(){
        if(undoRedo.undoStack.empty() || undoRedo.annualCounterUndo.empty() || undoRedo.counterUndo.empty()){
            return;
        }
        undoRedo.redoStack.push(register);
        undoRedo.counterRedo.push(employeesCounter);
        undoRedo.annualCounterRedo.push(employeesAnnualCounter);
        register = undoRedo.undoStack.pop();
        employeesAnnualCounter = undoRedo.annualCounterUndo.pop();
        employeesCounter = undoRedo.counterUndo.pop();
    }

    public static void redo(){
        if(undoRedo.redoStack.empty() || undoRedo.annualCounterRedo.empty() || undoRedo.counterRedo.empty()){
            return;
        }
        copyRegister();
        register = undoRedo.redoStack.pop();
        employeesAnnualCounter = undoRedo.annualCounterRedo.pop();
        employeesCounter = undoRedo.counterRedo.pop();
    }

    public static void emptyRedo(){
        List<Employee> auxiliar = new ArrayList<Employee>();
        int trash;
        while(!undoRedo.redoStack.empty()){
            auxiliar = undoRedo.redoStack.pop();
        }
        while(!undoRedo.annualCounterRedo.empty()){
            trash = undoRedo.annualCounterRedo.pop();
        }
        while(!undoRedo.counterRedo.empty()){
            trash = undoRedo.counterRedo.pop();
        }
    }

    public static void copyRegister(){

        List<Employee> copy = new ArrayList<Employee>();

        for(Employee current : register){
            if(current instanceof  Hourly){
                Employee auxiliar = new Hourly(current);
                ((Hourly)auxiliar).timecard.setArrivalHour(((Hourly)current).timecard.getArrivalHour());
                ((Hourly)auxiliar).timecard.setArrivalMinute(((Hourly)current).timecard.getArrivalMinute());
                ((Hourly)auxiliar).timecard.setExitHour(((Hourly)current).timecard.getExitHour());
                ((Hourly)auxiliar).timecard.setExitMinute(((Hourly)current).timecard.getExitMinute());
                setCommonInfo(auxiliar,current);
                copy.add(auxiliar);
            }
            else if(current instanceof  Salaried){
                Employee auxiliar = new Salaried(current);
                setCommonInfo(auxiliar,current);
                copy.add(auxiliar);
            }
            else if(current instanceof Commissioned){
                Employee auxiliar = new Commissioned(current);
                ((Commissioned)auxiliar).setCommission(((Commissioned)current).getCommission());
                ((Commissioned) auxiliar).saleResults = ((Commissioned) current).saleResults;
                setCommonInfo(auxiliar,current);
                copy.add(auxiliar);
            }
        }

        undoRedo.undoStack.push(copy);
        undoRedo.counterUndo.push(employeesCounter);
        undoRedo.annualCounterUndo.push(employeesAnnualCounter);
    }

    private static void setCommonInfo(Employee auxiliar, Employee current){
        auxiliar.agenda.setWeekly(current.agenda.isWeekly());
        auxiliar.agenda.setEachTwoWeeks(current.agenda.isEachTwoWeeks());
        auxiliar.agenda.setMonthly(current.agenda.isMonthly());
        auxiliar.agenda.setLastDay(current.agenda.isLastDay());
        auxiliar.agenda.setWeekToBePaid(current.agenda.isWeekToBePaid());
        auxiliar.agenda.setWeekDay(current.agenda.getWeekDay());
        auxiliar.agenda.setMonthDay(current.agenda.getMonthDay());
    }
}