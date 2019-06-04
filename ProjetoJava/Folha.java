import java.util.Scanner;

public class Folha{
    static Scanner input = new Scanner(System.in);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int numberOfEmployees = 0;                                    //This are the variables used to save the info from the employees.
    static int places = 100;                                             //Basically, the data is saved in arrays.
    static boolean busyPlaces[] = new boolean[places];
    static String names[] = new String[places];
    static String adress[] = new String[places];
    static float salaries[] = new float[places];
    static float hourSalaries[] = new float[places];
    static float commissions[] = new float[places];
    static boolean syndicated[] = new boolean[places];
    static short paymentMethod[] = new short[places];
    static int syndicateRegister[] = new int[places];
    static short workerType[] = new short[places];
    static float unionFee[] = new float[places];
    static float toReceive[] = new float[places];
    static boolean defaultSalaried[] = new boolean[places];
    static int paymentAgenda[] = new int[places];  // 1->weekly 2->each two weeks 3->monthly.
    static int payday[] = new int[places];  //month day for salaried and week day for the others.
    static boolean paymentCounter[] = new boolean[places];
    static boolean payToday[] = new boolean[places];
    static boolean penultimateMonthDay;
    static boolean lastMonthDay;
    
    static int undoCounter = 0;
    static boolean undoBusyPlaces[][] = new boolean[1000][places];
    static String undoNames[][] = new String[1000][places];
    static String undoAdress[][] = new String[1000][places];
    static float undoSalaries[][] = new float[1000][places];
    static float undoHourSalaries[][] = new float[1000][places];
    static float undoCommissions[][] = new float[1000][places];
    static boolean undoSyndicated[][] = new boolean[1000][places];
    static short undoPaymentMethod[][] = new short[1000][places];                 //Arrays used in the undo proccess.
    static int undoSyndicateRegister[][] = new int[1000][places];
    static short undoWorkerType[][] = new short[1000][places];
    static float undoUnionFee[][] = new float[1000][places];
    static float undoToReceive[][] = new float[1000][places];
    static boolean undoDefaultSalaried[][] = new boolean[1000][places];
    static int undoPaymentAgenda[][] = new int[1000][places];  
    static int undoPayday[][] = new int[1000][places]; 
    static boolean undoPaymentCounter[][] = new boolean[1000][places];
    static boolean undoPayToday[][] = new boolean[1000][places];

    static int redoCounter = 0;
    static boolean redoBusyPlaces[][] = new boolean[50][places];
    static String redoNames[][] = new String[50][places];
    static String redoAdress[][] = new String[50][places];
    static float redoSalaries[][] = new float[50][places];
    static float redoHourSalaries[][] = new float[50][places];
    static float redoCommissions[][] = new float[50][places];
    static boolean redoSyndicated[][] = new boolean[50][places];
    static short redoPaymentMethod[][] = new short[50][places];
    static int redoSyndicateRegister[][] = new int[50][places];                //Arrays used in the redo proccess.
    static short redoWorkerType[][] = new short[50][places];
    static float redoUnionFee[][] = new float[50][places];
    static float redoToReceive[][] = new float[50][places];
    static boolean redoDefaultSalaried[][] = new boolean[50][places];
    static int redoPaymentAgenda[][] = new int[50][places];  
    static int redoPayday[][] = new int[50][places]; 
    static boolean redoPaymentCounter[][] = new boolean[50][places];
    static boolean redoPayToday[][] = new boolean[50][places];
//-----------------------------------------------------------------------------------------------------------------------------------//

    static int date[] = new int[4];
    public static void main(String[] args) {
        setDate();
        mainMenu();
        input.close();
    }

    static void mainMenu(){
        boolean run = true;
        short menuOption;

        while(run){            
            System.out.println("$-------PAYROLL SYSTEM-------$        " + date[0] + "/" + date[1] + "/" + date[2]);
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
            System.out.println("0 -> Exit");
            System.out.println("Please choose one of the options above by typing the corresponding number:");

            menuOption = input.nextShort();
            String trash = input.nextLine();

            while(menuOption < 0 || menuOption > 11){
                System.out.println("Invalid choice. Please type again:");
                menuOption = input.nextShort();
                trash = input.nextLine();
            }
            
            switch(menuOption){
                
                case 1:
                    System.out.println("\nRegistering an employee.");
                    redoCounter = 0;
                    saveInUndo();
                    addEmployee();
                    break;

                case 2:
                    System.out.println("\nRemoving an employee.\n");
                    redoCounter = 0;
                    saveInUndo();
                    removeEmployee();
                    break;
                    
                case 3:
                    System.out.println("\nEditing info.\n");
                    redoCounter = 0;
                    saveInUndo();
                    editEmployee();
                    break;

                case 4:
                    System.out.println("List of employees:");
                    showList();
                    break;

                case 5:
                    System.out.println("\nEnd of the daily activities.\n");
                    System.out.println("List of payments:");
                    redoCounter = 0;
                    payroll();
                    dateManager();
                    break;

                case 6:
                    System.out.println("Inputing timecard info.");
                    redoCounter = 0;
                    saveInUndo();
                    timeCard();
                    break;

                case 7:
                    System.out.println("Inputing sale result.");
                    redoCounter = 0;
                    saveInUndo();
                    saleResult();
                    break;

                case 8:
                    System.out.println("Inputing service tax.");
                    redoCounter = 0;
                    saveInUndo();
                    serviceTax();
                    break;

                case 9:
                    saveInRedo();
                    undo();
                    break;
                
                case 10:
                    redo();
                    break;

                case 11:
                    System.out.println("Adding new payment agenda.\n");
                    redoCounter = 0;
                    newAgenda();
                    break;

                case 0:
                    run = false;
                    input.close();
                    break;
            }
        }
    }

    static int findFreePlace(){

        for(int i = 0; i < busyPlaces.length; i++){
            if(!busyPlaces[i]){
                return i;
            }
        }
        return -1;
    }

    static int findEmployee(String name){

        for(int i = 0; i<numberOfEmployees; i++){
            if(names[i].equals(name)){
                return i;
            }
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////// FUNCTIONS THAT MANAGE THE OPERATIONS
    static void addEmployee(){

        int placeIndex;

        if(numberOfEmployees == 0){
            placeIndex = 0;
        }
        else if(numberOfEmployees == 100){
            System.out.println("Sorry, the system is full.");
            return;
        }
        else{
            placeIndex = findFreePlace();
            if(placeIndex == -1){
                System.out.println("ERROR");
                return;
            }
        }

        setName(placeIndex);
        setAdress(placeIndex);
        setSalary(placeIndex);
        setWorkerType(placeIndex);
        setPaymentMethod(placeIndex);

        if(workerType[placeIndex] == 3){
            setCommission(placeIndex);
        }

        setSyndicated(placeIndex);

        if(syndicated[placeIndex]){
            setSyndicateRegister(placeIndex);
            setUnionFee(placeIndex);
        }

        busyPlaces[placeIndex] = true;
        numberOfEmployees ++;
        System.out.println("\nThe employee has been added.");
        System.out.println("His/her register number is " + placeIndex + "\n");
    }

    static void removeEmployee(){
        if(numberOfEmployees == 0){
            System.out.println("Sorry, the list is empty.\n");
            return;
        }

        System.out.println("\nPlease type the employee's name:\n");
        
        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
        }
        else{
            toReceive[index] = 0;
            busyPlaces[index] = false;
            numberOfEmployees --;
            System.out.println("The employee has been removed.\n");
        }        
    }

    static void editEmployee(){
        System.out.println("\nPlease type the employee's name:\n");
        
        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
        }
        else{
            System.out.println("Select the info you want to edit:");
            System.out.println("1 -> Name.");
            System.out.println("2 -> Adress.");
            System.out.println("3 -> Payment method.");
            System.out.println("4 -> Salary.");
            System.out.println("5 -> Commission.");
            System.out.println("6 -> Syndicated.");
            System.out.println("7 -> Syndicate register number.");
            System.out.println("8 -> Worker type.");
            System.out.println("9 -> Union fee.");
            
            short option = input.nextShort();
            String trash = input.nextLine();

            while(option < 0 || option > 9){
                System.out.println("Invalid choice. Please type again:");
                option = input.nextShort();
                trash = input.nextLine();
            }

            switch(option){
                case 1:
                    setName(index);
                    break;

                case 2:
                    setAdress(index);
                    break;

                case 3:
                    setPaymentMethod(index);
                    break;

                case 4:
                    setSalary(index);
                    break;

                case 5:
                    if(workerType[index] == 3){
                        setCommission(index);
                    }
                    else{
                        System.out.println("Sorry, this employee is not a commissioned one.\n");
                    }
                    break;

                case 6:
                    setSyndicated(index);
                    if(syndicated[index]){
                        setSyndicateRegister(index);
                    }
                    break;

                case 7:
                    if(syndicated[index]){
                        setSyndicateRegister(index);
                    }
                    else{
                        System.out.println("Sorry, this employee is not a syndicated one.\n");
                    }
                    break;

                case 8:
                    setWorkerType(index);
                    setSalary(index);
                    if(workerType[index] == 3){
                        setCommission(index);
                    }
                    break;
                
                case 9:
                    if(syndicated[index]){
                        setUnionFee(index);
                    }
                    else{
                        System.out.println("Sorry, this employee is not a syndicated one.\n");
                    }
            }

            System.out.println("The info has been edited.\n");
        }
    }

    static void showList(){
        if(numberOfEmployees == 0){
            System.out.println("Sorry, there are no employees.\n");
            return;
        }

        for(int i = 0; i < places; i++){
            if(busyPlaces[i]){
                switch(paymentMethod[i]){
                    case 1:
                        if(syndicated[i]){
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Check by Mail" + " - " + "Syndicated\n");
                        }
                        else{
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Check by Mail" + " - " + "Not syndicated\n");
                        }
                        break;
                    
                    case 2:
                        if(syndicated[i]){
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Check in hands" + " - " + "Syndicated\n");
                        }
                        else{
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Check in hands" + " - " + "Not syndicated\n");
                        }
                        break;

                    case 3:
                        if(syndicated[i]){
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Bank account" + " - " + "Syndicated\n");
                        }
                        else{
                            System.out.print("Register:" + i + " - " + names[i] + " - " + adress[i] + " - " + "Bank account" + " - " + "Not syndicated\n");
                        }
                        break;
                }
            }
        }
        System.out.println("\nType enter to cotinue.");
        String trash = input.nextLine();
    }

    static void dateManager(){
        date[0] ++;
        date[3] ++;

        if(date[3] == 8){
            date[3] = 1;
        }

        switch(date[1]){

                case 1: //January
                case 3: //March
                case 5: //May            <- Months that have 31 days.
                case 7: //July
                case 8: //August
                case 10: //October
                    penultimateMonthDay = false;
                    lastMonthDay = false;
                    if(date[0] == 32){
                        date[0] = 1;      //End of the month.
                        date[1] ++;
                    }
                    if(date[0] == 31){
                        lastMonthDay = true;
                    }
                    if(date[0] == 30){
                        penultimateMonthDay = true;
                    }
                    break;
                
                case 4: //April
                case 6: //June         <- Months that have 30 days.
                case 9: //September
                case 11: //November
                    penultimateMonthDay = false;
                    lastMonthDay = false;
                    if(date[0] == 31){
                        date[0] = 1;     //End of the month.
                        date[1] ++;
                    }
                    if(date[0] == 30){
                        lastMonthDay = true;
                    }
                    if(date[0] == 29){
                        penultimateMonthDay = true;
                    }
                    break;

                case 2: //February
                    penultimateMonthDay = false;
                    lastMonthDay = false;
                    if(date[0] == 29){
                        date[0] = 1;
                        date[1] ++;
                    }
                    if(date[0] == 28){
                        lastMonthDay = true;
                    }
                    if(date[0] == 27){
                        penultimateMonthDay = true;
                    }
                    break;
                
                case 12: //December
                    penultimateMonthDay = false;
                    lastMonthDay = false;
                    if(date[0] == 32){
                        date[0] = 1;       //End of the year.
                        date [1] = 1;
                        date[2] ++;
                    }
                    if(date[0] == 31){
                        lastMonthDay = true;
                    }
                    if(date[0] == 30){
                        penultimateMonthDay = true;
                    }
                    break;
            
        }
    }

    static void timeCard(){
        System.out.println("Please type the employee's name:");

        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
        }
        else if(workerType[index] != 1){
            System.out.println("Sorry, this employee is not a hourly.\n");
        }
        else if(date[3] == 1){
            System.out.println("Sorry, no one works on a Sunday.");
        }
        else{
            System.out.println("Please type the arrival time:");
            System.out.println("Hour:");
            float arraivalHour = input.nextFloat();
            System.out.println("Minutes:");
            float arrivalMinutes = input.nextFloat();

            System.out.println("\nPlease type the arrival time:");
            System.out.println("Hour:");
            float leavingHour = input.nextFloat();
            System.out.println("Minutes:");
            float LeavingMinutes = input.nextFloat();

            float workPeriod = 60*(leavingHour - arraivalHour) + (LeavingMinutes - arrivalMinutes);

            workPeriod /= 60;

            toReceive[index] += 8*hourSalaries[index];

            if(workPeriod > 8){
                workPeriod -= 8;
                toReceive[index] += (1.5 * workPeriod * hourSalaries[index]);
            }
        }
    }

    static void saleResult(){
        System.out.println("Please type the employee's name:");

        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
        }
        else if(workerType[index] != 3){
            System.out.println("Sorry, this employee is not a commissioned.\n");
        }
        else if(date[3] == 1){
            System.out.println("Sorry, no one works on a Sunday.");
        }
        else{
            System.out.println("Please type the sale result:");
            float result = input.nextFloat();

            toReceive[index] += commissions[index]*result;
        }
    }

    static void serviceTax(){
        System.out.println("Please type the employee's name:");

        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
        }
        else{
            System.out.println("Please type the service tax");

            float tax = input.nextFloat();

            toReceive[index]  = (1-tax)*toReceive[index];
        }
    }

    static void newAgenda(){
        System.out.println("Please type the employee's name:");

        String searchName = input.nextLine();

        int index = findEmployee(searchName);

        if(index == -1){
            System.out.println("Sorry, there is no employee with the name typed.\n");
            return;
        }

        System.out.println("\nType the new payment agenda for this employee:");
        String newAgenda = input.nextLine();

        if(newAgenda.contains("mensal")){
            if(workerType[index] != 2){
                System.out.println("Sorry, this employee is not a salaried one.");
                return;
            }
            System.out.println("Will the new payday be the month's last?(true / false)");
            boolean option = input.hasNextBoolean();

            if(option){
                defaultSalaried[index] = true;
            }
            else{
                defaultSalaried[index] = false;
                System.out.println("Please type the new payday:");
                payday[index] = input.nextInt();
            }
        }
        else if(newAgenda.contains("semanal 1")){
            if(workerType[index] != 1){
                System.out.println("Sorry, this employee is not a hourly one.");
                return;
            }
            System.out.println("Please type the new payday (2->Monday 3->Tuesday 4->Wednesday 5->Thursday 6->Friday 7->Saturday):");
            payday[index] = input.nextInt();
        }
        else if(newAgenda.contains("semanal 2")){
            if(workerType[index] != 3){
                System.out.println("Sorry, this employee is not a commissioned one.");
                return;
            }
            System.out.println("Please type the new payday (2->Monday 3->Tuesday 4->Wednesday 5->Thursday 6->Friday 7->Saturday):");
            payday[index] = input.nextInt();
        }
    }
    //------------------------------------------------------------------------------------------------------------------------------//

    ///////////////////////////////////////////////////////////////////////////////////////////// FUNCTIONS THAT READ THE INFO FROM THE USER
    static void setName(int index){
        System.out.println("\nPlease type the employee's name:");
        names[index] = input.nextLine();
    }

    static void setAdress(int index){
        System.out.println("\nPlease type the employee's adress:");
        adress[index] = input.nextLine();
    }

    static void setWorkerType(int index){
        System.out.println("\nPlease type the employee's worker type.");
        System.out.println("Use the following options:");
        System.out.println("1->Hourly 2->Salaried 3->Commissioned");

        short type = input.nextShort();

        while(type < 1 || 3 < type){
            System.out.println("Invalid option. Please type again:");
            System.out.println("1->Hourly 2->Salaried 3->Commissioned");
            type = input.nextShort();
        }

        workerType[index] = type;

        switch(workerType[index]){
            case 1:
                paymentAgenda[index] = 1;
                payday[index] = 6;
                toReceive[index] = 0;
                break;

            case 3:
                paymentAgenda[index] = 2;
                payday[index] = 6;
                toReceive[index] = (float)(salaries[index]/2.0);
                paymentCounter[index] = false;
                break;

            case 2:
                paymentAgenda[index] = 3;
                toReceive[index] = salaries[index];
                defaultSalaried[index] = true;
                break;
        }
    }

    static void setPaymentMethod(int index){
        System.out.println("\nPlease type the employee's payment method.");
        System.out.println("Use the following options:");
        System.out.println("1->Check by Mail 2->Check in hands 3->Bank account");

        short method = input.nextShort();

        while(method < 1 || 3 < method){
            System.out.println("Invalid choice. Please type again.");
            System.out.println("1->Check by Mail 2->Check in hands 3->Bank account");
            method = input.nextShort();
        }

        paymentMethod[index] = method;
    }

    static void setSalary(int index){
        System.out.println("\nPlease type the emplyee's salary");

        float salary = input.nextFloat();

        while(salary<=0){
            System.out.println("Invalid salary. Please type again.");
            salary = input.nextFloat();
        }

        salaries[index] = salary;
        hourSalaries[index] = (float)(salary/220.0);
    }

    static void setCommission(int index){
        System.out.println("\nPlease type the employee's commission (between 0 and 1):");

        float commission = input.nextFloat();

        while(commission < 0 || commission > 1){
            System.out.println("Invallid commiossion. Please type again.");
            commission = input.nextFloat();
        }

        commissions[index] = commission;
    }

    static void setSyndicated(int index){
        System.out.println("\nPlease tell if the employee is syndicated:");
        System.out.println("Use the following options:");
        System.out.println("1->Yes 0->No");
        
        short auxiliar = input.nextShort();

        while(auxiliar < 0 || 1 < auxiliar){
            System.out.println("Invalid choice. Please type again.");
            System.out.println("1->Yes 0->No");
            auxiliar = input.nextShort();
        }

        if(auxiliar == 1){
            syndicated[index] = true;
        }
        else{
            syndicated[index] = false;
        }
    }

    static void setSyndicateRegister(int index){
        System.out.println("\nPlease type the employee's syndicate register number:");

        int register = input.nextInt();

        while(register < 0){
            System.out.println("Invalid number. Please type again.");
            register = input.nextInt();
        }
        syndicateRegister[index] = register;
    }

    static void setUnionFee(int index){
        System.out.println("\nPlease type the employee's union fee (between 0 and 1):");

        float fee = input.nextFloat();

        while(fee < 0){
            System.out.println("Invalid entry. Please type again.");
            fee = input.nextFloat();
        }
        unionFee[index] = fee;
    }

    static void setDate(){
        System.out.println("Please type the actual date.\n");

        System.out.println("Day:");
        date[0] = input.nextInt();

        System.out.println("Month:");
        date[1] = input.nextInt();

        System.out.println("Year:");
        date[2] = input.nextInt();

        System.out.println("Week day: 1->Sunday 2->Monday 3->Tuesday 4->Wednesday 5->Thursday 6->Friday 7->Saturday");
        date[3] = input.nextInt();
    }
    //-----------------------------------------------------------------------------------------------------------------------//

    ////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCTION THAT MANAGES THE PAYMENT
    static void payroll(){
        for(int i = 0; i < places; i++){
            if(busyPlaces[i]){
                switch(paymentAgenda[i]){
                    case 1:
                        if(date[3] == payday[i]){
                            payToday[i] = true;
                        }
                        break;

                    case 2:
                        if(date[3] == payday[i]){
                            if(paymentCounter[i]){
                                payToday[i] = true;
                                paymentCounter[i] = false;
                            }
                            else{
                                paymentCounter[i] = true;
                            }
                        }
                        break;

                    case 3:
                        if(defaultSalaried[i]){
                            if(penultimateMonthDay && date[3] == 7){
                                payToday[i] = true;
                            }
                            else if(lastMonthDay && date[3] != 1){
                                payToday[i] = true;
                            }
                        }
                        else{
                            if(date[0] == payday[i] - 1 && date[3] == 7){
                                payToday[i] = true;
                            }
                            else if(date[0] == payday[i] && date[3] != 1){
                                payToday[i] = true;
                            }    
                        }
                        break;
                }
            }
        }

        boolean flag = false;

        for(int i = 0; i < places; i++){
            if(payToday[i]){
                flag = true;
                switch(paymentAgenda[i]){
                    case 1: 
                        printPayment(i);
                        toReceive[i] = 0;
                        break;
                    
                    case 2:
                        printPayment(i);
                        toReceive[i] = (float)(salaries[i]/2.0);
                        break;

                    case 3:
                        printPayment(i);
                        toReceive[i] = salaries[i];
                        break;
                }
                payToday[i] = false;
            }
        }

        if(flag){
            System.out.println("\nType enter to continue.");
            String trash = input.nextLine();
        }
    }

    static void printPayment(int index){
        switch(paymentMethod[index]){
            case 1:
                System.out.println("Employee:" + names[index] + " - R$" + toReceive[index] + " - Check by mail.");
                break;
            
            case 2:
                System.out.println("Employee:" + names[index] + " - R$" + toReceive[index] + " - Check in hands.");
                break;

            case 3:
                System.out.println("Employee:" + names[index] + " - R$" + toReceive[index] + " - Bank account.");
                break;
        }
    }

    static void saveInUndo(){
        for(int i = 0; i < places; i++){
            undoBusyPlaces[undoCounter][i] = busyPlaces[i];
            undoNames[undoCounter][i] = names[i];
            undoAdress[undoCounter][i] = adress[i];
            undoSalaries[undoCounter][i] = salaries[i];
            undoHourSalaries[undoCounter][i] = hourSalaries[i];
            undoCommissions[undoCounter][i] = commissions[i];
            undoSyndicated[undoCounter][i] = syndicated[i];
            undoPaymentMethod[undoCounter][i] = paymentMethod[i];
            undoSyndicateRegister[undoCounter][i] = syndicateRegister[i];
            undoWorkerType[undoCounter][i] = workerType[i];
            undoUnionFee[undoCounter][i] = unionFee[i];
            undoToReceive[undoCounter][i] = toReceive[i];
            undoDefaultSalaried[undoCounter][i] = defaultSalaried[i];
            undoPaymentAgenda[undoCounter][i] = paymentAgenda[i];  
            undoPayday[undoCounter][i] = payday[i]; 
            undoPaymentCounter[undoCounter][i] = paymentCounter[i];
            undoPayToday[undoCounter][i] = payToday[i];
        }
        undoCounter++;
    }

    static void saveInRedo(){
        for(int i = 0; i < places; i++){
            redoBusyPlaces[redoCounter][i] = busyPlaces[i];
            redoNames[redoCounter][i] = names[i];
            redoAdress[redoCounter][i] = adress[i];
            redoSalaries[redoCounter][i] = salaries[i];
            redoHourSalaries[redoCounter][i] = hourSalaries[i];
            redoCommissions[redoCounter][i] = commissions[i];
            redoSyndicated[redoCounter][i] = syndicated[i];
            redoPaymentMethod[redoCounter][i] = paymentMethod[i];
            redoSyndicateRegister[redoCounter][i] = syndicateRegister[i];
            redoWorkerType[redoCounter][i] = workerType[i];
            redoUnionFee[redoCounter][i] = unionFee[i];
            redoToReceive[redoCounter][i] = toReceive[i];
            redoDefaultSalaried[redoCounter][i] = defaultSalaried[i];
            redoPaymentAgenda[redoCounter][i] = paymentAgenda[i];  
            redoPayday[redoCounter][i] = payday[i]; 
            redoPaymentCounter[redoCounter][i] = paymentCounter[i];
            redoPayToday[redoCounter][i] = payToday[i];
        }
        redoCounter++;
    }

    static void undo(){
        if(undoCounter == 0){
            System.out.println("Sorry, this operation is not available.\n");
            return;
        }

        undoCounter--;

        for(int i = 0; i < places; i++){
            busyPlaces[i] = undoBusyPlaces[undoCounter][i];
            names[i] = undoNames[undoCounter][i];
            adress[i] = undoAdress[undoCounter][i];
            salaries[i] = undoSalaries[undoCounter][i];
            hourSalaries[i] = undoHourSalaries[undoCounter][i];
            commissions[i] = undoCommissions[undoCounter][i];
            syndicated[i] = undoSyndicated[undoCounter][i];
            paymentMethod[i] = undoPaymentMethod[undoCounter][i];
            syndicateRegister[i] = undoSyndicateRegister[undoCounter][i];
            workerType[i] = undoWorkerType[undoCounter][i];
            unionFee[i] = undoUnionFee[undoCounter][i];
            toReceive[i] = undoToReceive[undoCounter][i];
            defaultSalaried[i] = undoDefaultSalaried[undoCounter][i];
            paymentAgenda[i] = undoPaymentAgenda[undoCounter][i];  
            payday[i] = undoPayday[undoCounter][i]; 
            paymentCounter[i] = undoPaymentCounter[undoCounter][i];
            payToday[i] = undoPayToday[undoCounter][i];
        }
    }

    static void redo(){
        if(redoCounter == 0){
            System.out.println("Sorry, this operation is not available.\n");
        }

        saveInUndo();

        redoCounter--;

        for(int i = 0; i < places; i++){
            busyPlaces[i] = redoBusyPlaces[redoCounter][i];
            names[i] = redoNames[redoCounter][i];
            adress[i] = redoAdress[redoCounter][i];
            salaries[i] = redoSalaries[redoCounter][i];
            hourSalaries[i] = redoHourSalaries[redoCounter][i];
            commissions[i] = redoCommissions[redoCounter][i];
            syndicated[i] = redoSyndicated[redoCounter][i];
            paymentMethod[i] = redoPaymentMethod[redoCounter][i];
            syndicateRegister[i] = redoSyndicateRegister[redoCounter][i];
            workerType[i] = redoWorkerType[redoCounter][i];
            unionFee[i] = redoUnionFee[redoCounter][i];
            toReceive[i] = redoToReceive[redoCounter][i];
            defaultSalaried[i] = redoDefaultSalaried[redoCounter][i];
            paymentAgenda[i] = redoPaymentAgenda[redoCounter][i];  
            payday[i] = redoPayday[redoCounter][i]; 
            paymentCounter[i] = redoPaymentCounter[redoCounter][i];
            payToday[i] = redoPayToday[redoCounter][i];
        }
    }
}