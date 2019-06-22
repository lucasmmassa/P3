package main;

import java.util.Scanner;
import manager.*;
import utils.ExceptionsHandler;

public class Main{
    static Scanner input = new Scanner(System.in);
    static String trash;
    static boolean rolled;
    static ExceptionsHandler handler = new ExceptionsHandler();
    public static void main(String[] args) {
        rolled = false;
        boolean run = true;
        int menuOption;
        EmployeesManager manager = new EmployeesManager();

        while(run){
            System.out.println("$-------PAYROLL SYSTEM-------$        " + manager.calendar.day + "/" + manager.calendar.month + "/" + manager.calendar.year);
            System.out.println("");
            System.out.println("//////// MAIN MENU ////////");
            System.out.println("");
            System.out.println("This system provides the following functionalities:");
            System.out.println("1 -> Register Employee.");
            System.out.println("2 -> Remove Employee.");
            System.out.println("3 -> Edit Info.");
            System.out.println("4 -> Show employee's list.");
            System.out.println("5 -> Closing-Time.");
            System.out.println("6 -> Timecard input.");
            System.out.println("7 -> Sale result input.");
            System.out.println("8 -> Service tax input.");
            System.out.println("9 -> Undo.");
            System.out.println("10 -> Redo.");
            System.out.println("11 -> New payment agenda.");
            System.out.println("12 -> Payroll.");
            System.out.println("0 -> Exit");
            System.out.println("Please choose one of the options above by typing the corresponding number:");

            menuOption = handler.integerInput();

            while(menuOption < 0 || menuOption > 12){
                System.out.println("Please choose one of the options above.");
                menuOption = handler.integerInput();
            }
            
            switch(menuOption){
                
                case 1:
                    System.out.println("\nRegistering an employee.");
                    manager.addEmployee();
                    break;

                case 2:
                    System.out.println("\nRemoving an employee.\n");
                    manager.removeEmployee();
                    break;
                    
                case 3:
                    System.out.println("\nEditing info.\n");
                    manager.editEmployee();
                    break;

                case 4:
                    System.out.println("List of employees:\n");
                    manager.showList();
                    System.out.println("Type enter to continue.");
                    trash = input.nextLine();
                    break;

                case 5:
                    if(!rolled){
                        System.out.println("The payroll was not checked today!");
                        System.out.println("Do you want to check it?");
                        System.out.println("1-yes 0-no");
                        int choice = handler.integerInput();
                        while(choice < 0 || choice > 1){
                            System.out.println("Please choose one of the options above.");
                            choice = handler.integerInput();
                        }
                        if(choice == 1){
                            manager.payroll();
                        }
                    }
                    System.out.println("\nEnd of the daily activities.\n");
                    rolled = false;
                    int monthBeforeUpdate = manager.calendar.month;
                    boolean newYear = manager.calendar.dateManager();
                    if(newYear){
                        manager.setEmployeesAnnualCounter(0);
                    }
                    if(monthBeforeUpdate != manager.calendar.month){
                        manager.resetServiceTax();
                    }
                    break;

                case 6:
                    System.out.println("Inputing timecard info.");
                    manager.timeCard();
                    break;

                case 7:
                    System.out.println("Inputing sale result.");
                    manager.saleResult();
                    break;

                case 8:
                    System.out.println("Inputing service tax.");
                    manager.serviceTax();
                    break;

                case 9:
                    manager.undo();
                    break;
                
                case 10:
                    manager.redo();
                    break;

                case 11:
                    System.out.println("Adding new payment agenda.\n");
                    manager.newAgenda();
                    break;

                case 12:
                    System.out.println("List if payments:\n");
                    if(!rolled) {
                        manager.payroll();
                        rolled = true;
                    }
                    System.out.println("\nPress enter to continue.");
                    trash = input.nextLine();
                    break;

                case 0:
                    run = false;
                    input.close();
                    break;
            }
        }

    }
}