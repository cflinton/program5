package metrocourier;


import java.util.Scanner;

public class MetroCourier {

    public static void main(String[] args) {
        //main method presents menu to the user
        Scanner scan = new Scanner(System.in); //used to read input from the console
        String input = "0"; //default value
        
        //add logic to load saved waybills from saved_waybills file
        
        //continue to present the menu until the user enters 4
        while(!input.equals("4"))
        {
            //print the menu
            System.out.println("********************************************************");
            System.out.println("*                   Metro Courier                      *");
            System.out.println("********************************************************");
            System.out.println("*              Select an option below:                 *");
            System.out.println("* 1 - Read Waybills                                    *");
            System.out.println("* 2 - Immediate Dispatch                               *");
            System.out.println("* 3 - End of Day Dispatch                              *");
            System.out.println("* 4 - Save and Exit                                    *");
            System.out.println("********************************************************");
            System.out.print(">");
            
            //try and read menu selection
            try{
                input = scan.nextLine();
                input = input.trim();
            }
            catch(Exception ex)
            {
                //unknown exception
                System.out.println("Unknown error: " + ex);
                System.exit(1);//exit the program will error of 1
            }
            
            //process menu action and call coresponding method
            switch(input)
            {
                case "1":   readWaybills();
                            break;
                case "2":   disptach();
                            break;
                case "3":   endOfDay();
                            break;
                case "4":   save();
                            break;
                default:    System.out.println("Invalid input!");
                            break;
            }
        }
    } 

    private static void readWaybills()
    {
        //method will read all waybills from waybill folder
        System.out.print("Reading waybills...");
        //add logic here
        System.out.println("done!");
        System.out.println("X waybills read, Y total waybills ready.");
        System.out.print("Press any key to continue>");
        //add logic here
    }

    private static void disptach()
    {
        //method will dispatch the newest waybill
        System.out.println("Dispatching 1 waybill...");
        //add logic here
        System.out.println("Waybill #: <number goes here>");
        System.out.println("Waybill Address: <address goes here>");
        System.out.print("Press any key to continue>");
        //add logic here
    }

    private static void endOfDay()
    {
        //method will queue the waybills to the output directory
        System.out.println("Y outstanding waybills have been queued and sent.");
        //add logic here
        System.out.print("Press any key to continue>");
        //add logic here
    }

    private static void save() 
    {
        //method will save any waybills in memory to saved_waybills, then exit
        System.out.println("Saved. Goodbye!");
    }
}
