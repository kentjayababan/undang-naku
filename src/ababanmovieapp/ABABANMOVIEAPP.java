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
        System.out.println("4. EXIT");
        
        System.out.print("Enter Action: ");
        int act = sc.nextInt();
        
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