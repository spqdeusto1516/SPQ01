package clientUI;

import java.rmi.RemoteException;
import java.util.Scanner;

import javax.sound.midi.ControllerEventListener;

import clientController.ClientController;
import dto.BookDTO;

public class ClientConsole {
	
	private static ClientController controller;
	private Boolean logged=false;
	private Boolean Confirmation=false;
	private int ResNum;
	
	private int testint = 1;
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	try {
			controller =new ClientController(args);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        ClientConsole console = new ClientConsole();
        console = console.mainMenu(console);
        System.out.println("Application has been shut down");
    }

    private ClientConsole mainMenu(ClientConsole console) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
        System.out.println("Welcome to the console application");

        int selection = 0;
        if(!logged){
	        do {
	            System.out.println("[1] Create Account");
	            System.out.println("[2] Login to Account");
	            System.out.println("[3] Quit");
	
	            System.out.print("Insert selection: ");
	            // selection = testint++;
	            selection = scanner.nextInt();
	            switch (selection) {
	            case 1: return console.submenu1(console);
	            case 2: return console.submenu1(console);
	            case 3: return console;
	            default:
	                System.out.println("The selection was invalid!");
	            }
	        } while (selection != 3);
        }else{
        	 do {
 	            System.out.println("[1] Get Flights");
 	            System.out.println("[2] LogOut");
 	
 	            System.out.print("Insert selection: ");
 	            // selection = testint++;
 	            selection = scanner.nextInt();
 	            switch (selection) {
 	            case 1: controller.getBooks();
 	            		return console.submenu2(console);
 	            case 2: 
 	            	logged=false;
 	            	return console.mainMenu(console);
 	            case 3: return console;
 	            default:
 	                System.out.println("The selection was invalid!");
 	            }
 	        } while (selection != 2);
        }
        return console;
    }

    private ClientConsole submenu1(ClientConsole console) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
        System.out.println("Welcome to the LOGIN MENU");

        String name = "";
        String pass="";
        
        System.out.println("Enter Your User Name:");

        name = scanner.nextLine();
        
    	 System.out.println("Enter Your User Password:");

         pass = scanner.nextLine();
         
         //send Password
        
         if( controller.login(name, pass, 0)){
        	 logged=true;
        	 return console.mainMenu(console);
         }else{
        	 System.out.println("password Wrong Try Again");
        	 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 return console.submenu1(console);
         }
         }
         private ClientConsole submenu2(ClientConsole console) {
         	
         	Scanner scanner = new Scanner(System.in);
         	
             System.out.println("Welcome to the Flights MENU");
             
             for (int i=0;i<controller.books.size();i++) {
            	 System.out.println((1+i)+": "+controller.books.get(i).getAcronym()+" "+controller.books.get(i).getDescription());
			}
            
             for (int i=1;i<controller.books.size()+1;i++) {
            	 System.out.println("["+i+"] BookFlight "+i+"");
             }
	         System.out.println("[3] LogOut");
             int pass = scanner.nextInt();
              
              //send Password
             switch (pass) {
	            case 1: 
	            	ResNum=controller.book(controller.books.get(pass));
	            	return console.submenu3(console);
	            case 2: 
	            	ResNum=controller.book(controller.books.get(pass));
	            	return console.submenu3(console);
	            case 3: 
	            	ResNum=controller.book(controller.books.get(pass));
	            	return console.submenu3(console);
	            case 4: 
	            	ResNum=controller.book(controller.books.get(pass));
	            	return console.submenu3(console);
	            case 5: logged=false;
            	return console.mainMenu(console);
	            default:
	                System.out.println("The selection was invalid!");
	            }
             return console;
    }

		private ClientConsole submenu3(ClientConsole console) {
         	Scanner scanner = new Scanner(System.in);
         	
            System.out.println("Welcome to the Reservation:"+ResNum+" MENU");
            
            System.out.println("[1] MakePayment");
	         System.out.println("[2] Cancel");
            int pass = scanner.nextInt();
             
             //send Password
            switch (pass) {
	            case 1: 
	            	Confirmation=controller.pay(ResNum,0);
	            	if(Confirmation){
	            		System.out.println("Payment Accepted");
	            	}
	            	return console.mainMenu(console);
	            case 2: 
	            	return console.submenu2(console);
	            default:
	                System.out.println("The selection was invalid!");
	            }
            return console;
		}
}
