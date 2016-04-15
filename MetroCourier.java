
import java.util.Scanner;
import java.io.*;
import java.util.Stack;

public class MetroCourier {
    
    static Stack<Waybill> billStack = new Stack<Waybill>();
    static File dir = new File("new_waybills"); //directory file
    static int stackSize = 0;
    
    public static void main(String[] args) {
        //main method presents menu to the user
        Scanner scan = new Scanner(System.in); //used to read input from the console
        String input = "0"; //default value
	int i;

        //add logic to load saved waybills from saved_waybills file
        try{
      	    dir.createNewFile();
	} catch(IOException e) {
	    System.out.println("IO Exception creating new directory file");
	}	
	
	File savedBills = new File("saved_waybills.obj");
	if(savedBills.exists()) {
	    try{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("saved_waybills.obj"));
 	        while(true) {
		    try {
		        Waybill wb = (Waybill) in.readObject();
		        billStack.push(wb);
		        stackSize++;
		    } catch(EOFException e) {
		        System.out.println("Saved waybills have been read");
		    } catch(IOException e) {
			System.out.println("IO Exception whlie loading saved waybills");
		    } catch(ClassNotFoundException e) {
			System.out.println("Class Not Found Exception while loading saved waybills");
		    }
		} 
	    } catch(FileNotFoundException e) {
	        System.out.println("Error in finding save file");
	    } catch(IOException e) {
	        System.out.println("IO error for Object Input Stream for save file");
	    }

	}
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
	    i = 0;
            if(input.equals("1")) {
	    	i = 1;
	    } else if(input.equals("2")) {
	        i = 2;
	    } else if(input.equals("3")) {
		i = 3;
	    } else if(input.equals("4")) {
		i = 4;
	    }	
            switch(i)
            {
                case 1:   readWaybills();
                            break;
                case 2:   disptach();
                            break;
                case 3:   endOfDay();
                            break;
                case 4:   save();
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
	Scanner scan;
	String to;
	String from;
	String str;
	int num;
	int count = 0;
	Waybill wb = new Waybill();
	File folder = new File("new_waybills");
	try{
	    for( File fileEntry : folder.listFiles()) {
		try{
	            scan = new Scanner(fileEntry);
	            scan.skip("Waybill: ");
	            str = scan.nextLine();
	            num = Integer.parseInt(str);
	            scan.skip("Sender: ");
	            from = scan.nextLine();
	            scan.skip("Destination: ");
	            to = scan.nextLine();
  	            wb = new Waybill(num, to, from);
	            billStack.push(wb);
	  	    stackSize++;
	 	    count++;
		    fileEntry.delete();
	        } catch(FileNotFoundException e) {
		    System.out.println("fileEntry not found");
	        }
	    }
	} catch(NullPointerException e) {
	    System.out.println("Nothing is in the new_waybills folder");
	}
        System.out.println("done!");
        System.out.println("" + count + " waybills read, " + stackSize + " total waybills ready.");
        System.out.print("Press any enter to continue>");
        //add logic here
	scan = new Scanner(System.in);
	scan.nextLine();
    }

    private static void disptach()
    {
        //method will dispatch the newest waybill
        System.out.println("Dispatching 1 waybill...");
        //add logic here
        if(!billStack.empty()) {
	    Waybill dis = billStack.pop();
    	    stackSize--;
            System.out.println("Waybill #: " + dis.getNumber());
            System.out.println("Waybill Address: " + dis.getDestination());
	} else {
	    System.out.println("No Waybills in stack.");
	}
        System.out.print("Press any enter to continue>");
        //add logic here
	Scanner scan = new Scanner(System.in);
	scan.nextLine();
    }

    private static void endOfDay()
    {
        //method will queue the waybills to the output directory
	if(!billStack.empty()) {
        System.out.println("" + stackSize + " outstanding waybills have been queued and sent.");
        //add logic here
        File od = new File("waybill_queue.txt");
	try{
      	    od.createNewFile();
	} catch(IOException e) {
	    System.out.println("IO Exception creating output directory");
	}
	try{
            PrintWriter out = new PrintWriter(od);
	    while(!billStack.empty()) {
	        Waybill wb = billStack.pop();
	        String st = wb.toString();
	        out.println(st);
 	    }
	} catch(FileNotFoundException e) {
	    System.out.println("File Not Found Exception for creating print writer for waybill queue.");
	}
	} else {
	    System.out.println("No Waybills in stack.");
	}
	
        System.out.print("Press any enter to continue>");

        //add logic here
	Scanner scan = new Scanner(System.in);
	scan.nextLine();
    }

    private static void save() 
    {
        //method will save any waybills in memory to saved_waybills, then exit
	if(!billStack.empty()) {
	File save = new File("saved_waybills.obj");
	try{
      	    save.createNewFile();
	} catch(IOException e) {
	    System.out.println("IO Exception creating save file");
	}
	try{
	    FileOutputStream fout = new FileOutputStream("saved_waybills.obj");
	    ObjectOutputStream oos = new ObjectOutputStream(fout);
	    while(!billStack.empty()) {
		Waybill wb = billStack.pop();
		oos.writeObject(wb);
 	    }
	    oos.close();
	} catch(Exception ex) {
	    ex.printStackTrace();
	}
	} else {
	    System.out.println("No Waybills in stack.");
	}
        System.out.println("Saved. Goodbye!");
    }
}
