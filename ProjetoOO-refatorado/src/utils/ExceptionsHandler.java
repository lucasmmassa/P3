package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionsHandler {

    Scanner input = new Scanner(System.in);

    public int integerInput(){
        while (true){
            try{
                return input.nextInt();
            }
            catch (InputMismatchException ime){
                input.next();
                System.out.println("Input mismatch.");
                System.out.println("Please type an integer.");
            }
        }
    }

    public double doubleInput(){
        while (true){
            try{
                return input.nextDouble();
            }
            catch (InputMismatchException ime){
                input.next();
                System.out.println("Input mismatch.");
                System.out.println("Please type an floating point number.");
            }
        }
    }

    public int toInteger(String info){
        while(true){
            try{
                return Integer.parseInt(info);
            }
            catch (NumberFormatException nfe){
                System.out.println("Number format exception.");
                System.out.println("The string could not be converted to an integer.");
                System.out.println("Invalid agenda.");
                return -1;
            }
        }
    }
}
