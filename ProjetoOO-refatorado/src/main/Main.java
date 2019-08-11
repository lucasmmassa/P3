package main;

import java.util.Scanner;
import manager.*;
import utils.ExceptionsHandler;

public class Main{
    static Scanner input = new Scanner(System.in);
    static String trash;
    public static boolean rolled;
    public static ExceptionsHandler handler = new ExceptionsHandler();
    public static EmployeesData manager;
    public static  boolean run;
    public static Actions actions;

    public static void main(String[] args) {
        actions = new Actions();
        rolled = false;
        run = true;
        manager = new EmployeesData();

        while(run){
            System.out.println("$-------PAYROLL SYSTEM-------$        " + manager.calendar.day + "/" + manager.calendar.month + "/" + manager.calendar.year);
            System.out.println("");
            System.out.println("//////// MAIN MENU ////////");
            System.out.println("");
            System.out.println("This system provides the following functionalities:");
            System.out.println("Add -> Register Employee.");
            System.out.println("Remove -> Remove Employee.");
            System.out.println("Edit -> Edit Info.");
            System.out.println("Show -> Show employee's list.");
            System.out.println("Close -> Closing-Time.");
            System.out.println("Timecard -> Timecard input.");
            System.out.println("Sale -> Sale result input.");
            System.out.println("Service -> Service tax input.");
            System.out.println("Undo -> Undo.");
            System.out.println("Redo -> Redo.");
            System.out.println("Agenda -> New payment agenda.");
            System.out.println("Payroll -> Payroll.");
            System.out.println("Exit -> Exit");
            System.out.println("Please choose one of the options above by typing the corresponding name (String before ->):");

            MainCommand command = handler.getClassByName();

            actions.takeAction(command);
        }

    }
}