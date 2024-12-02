package ababanmovieapp;
    
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Scanner;


public class ABABANMOVIEAPP {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);    
        boolean exit = true;
        do{
        System.out.println("WELCOME TO MOVIEAPP");
        System.out.println("");
        System.out.println("1. CUSTOMER");
        System.out.println("2. MOVIE");
        System.out.println("3. TICKETS");
        System.out.println("4. REPORT SECTION");
        System.out.println("5. EXIT");
        
        System.out.print("Enter Action: ");
        int act = -1; 

            try {
                act = sc.nextInt(); 
            } catch (InputMismatchException e) {
               
                System.out.println("Invalid action, Please enter a numeric action.");
                sc.nextLine(); 
                continue; 
            }

            if (act < 1 || act > 5) {
                System.out.println("Invalid action, Please enter a number between 1 to 5.");
                continue; 
            }

        
        switch(act){
            case 1:
                Customer cs = new Customer ();
                cs.cTransaction();
            break;
            
            case 2:
                Movie mv = new Movie ();
                mv.mvTransaction();
            break;
            
            case 3:
                Tickets ts = new Tickets ();
                ts.tsTransaction();
            break;
            
            case 4:
                Report rep = new Report (); 
                rep.viewReports();
             
            case 5:
                System.out.println("you sure??? (yes/no): ");
                String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                           exit = false;
                }
            break;   
            
        }
        }while (exit);
        System.out.print("See youu!");               
    
    }} 