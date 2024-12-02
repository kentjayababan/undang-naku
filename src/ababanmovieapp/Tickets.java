package ababanmovieapp;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Tickets {
    
    public void tsTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response = "yes";
        int action = -1;  
        Tickets ts = new Tickets ();

        
        do{
            
       
        System.out.println("WELCOME TO TICKET!");    
        System.out.println("1. ADD TICKET");
        System.out.println("2. VIEW TICKET");
        System.out.println("3. UPDATE TICKET");
        System.out.println("4. DELETE TICKET");
        System.out.println("5. Exit. ");
        
        System.out.println("Enter Action: ");
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
                ts.addTickets(); 
                ts.viewTickets();
                break;
            case 2:       
                ts.viewTickets();
                break;
            case 3:
                ts.viewTickets();
                ts.updateTickets();
                ts.viewTickets();
                break;
            case 4:
                ts.viewTickets();
                ts.deleteTickets();
                ts.viewTickets();
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addTickets(){
        Scanner sc = new Scanner (System.in);   
        config conf = new config(); 
        Customer ct = new Customer ();
        ct.viewCustomers();
        
        System.out.print("Enter the ID of the Customer: ");
        int cid = sc.nextInt();
        
        String csql = "SELECT c_id FROM tbl_customer WHERE c_id = ?";
        while(conf.getSingleValue(csql, cid) == 0){
            System.out.print("Customer does not exist, Select Again: ");
            cid = sc.nextInt();
            
        }
        Movie mv = new Movie ();
        mv.viewMovie();
        
        System.out.print("Enter the ID of the Movie: ");
        int mid = sc.nextInt();
        
        String msql = "SELECT m_id FROM tbl_movie WHERE m_id = ?";
        while(conf.getSingleValue(msql, mid) == 0){
            System.out.print("Movie does not exist, Select Again: ");
            mid = sc.nextInt();
            
        }
         String stockQuery = "SELECT m_stock FROM tbl_movie WHERE m_id = ?";
        double stock = conf.getSingleValue(stockQuery, mid);
        
        double quantity;
        if (stock == 0) {
            System.out.println("Movie is out of stock and not available for ordering.");
            quantity = 0;

            
            String updateStatusQuery = "UPDATE tbl_movie SET m_status = 'Not Available' WHERE m_id = ?";
            conf.updateRecord(updateStatusQuery, mid);
        } else {
            System.out.print("Enter Quantity: ");
            quantity = sc.nextDouble();
            
            while (quantity > stock) {
                System.out.println("Requested quantity exceeds available stock. Available stock: " + stock);
                System.out.print("Enter Quantity: ");
                quantity = sc.nextDouble();
            }
        }
        
        String priceqry = "SELECT m_price FROM tbl_movie WHERE m_id = ?";
        double price = conf.getSingleValue(priceqry, mid);
        double due = price * quantity;
        
        System.out.println("---------------------------");
        System.out.println("Total Due: "+due);
        System.out.println("---------------------------");

        System.out.println("");
        
        System.out.println("Enter the received cash: ");
        double rcash = sc.nextDouble();
        
        while(rcash < due){
            System.out.println("Invalid Amount, Try Again: ");
            rcash = sc.nextDouble();
        }
        sc.nextLine();
        System.out.print("Ticket Date: ");
        String date = sc.nextLine();
        
        String status = (quantity == 0) ? "Not Available" : "Pending";

        
        String qry = "INSERT INTO tbl_tickets (c_id, m_id, t_stockqty, t_due, t_rcash, t_date, t_status)"
                + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        conf.addRecord(qry, cid, mid, quantity, due, rcash, date, status);
        
        
    }
    
    public void viewTickets() {
    String qry = "SELECT t_id, c_fname, m_name, m_price, m_genre, m_duration, t_due, t_rcash, t_date, t_status FROM tbl_tickets "
            + "LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_tickets.c_id "
            + "LEFT JOIN tbl_movie ON tbl_movie.m_id = tbl_tickets.m_id";

    String[] hrds = {"T_ID", "Customer", "Movie Title", "Price", "Genre", "Duration" , "Due" , "Cash", "Date" , "Order Status"};
    String[] clms = {"t_id", "c_fname", "m_name", "m_price", "m_genre", "m_duration" , "t_due" , "t_rcash", "t_date", "t_status"};
    config conf = new config();
    conf.viewRecords(qry, hrds, clms);
   }
    
    private void updateTickets() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;

        while (true) {
            System.out.print("Enter the ID to update: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Ticket ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT t_id FROM tbl_tickets WHERE t_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }
        
        sc.nextLine();
        System.out.println("Enter New Ticket Date: ");
        String date = sc.nextLine();

        String qry = "UPDATE tbl_tickets SET t_date = ? WHERE t_id = ?";
        conf.updateRecord(qry, date, id);
}
    private void deleteTickets() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;

        while (true) {
            System.out.print("Enter the ID to delete: ");
            while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a valid Order ID: ");
            sc.next();
        }
            try {
                id = sc.nextInt();
                if (conf.getSingleValue("SELECT t_id FROM tbl_tickets WHERE t_id = ?", id) != 0) {
                    break; 
                }
                System.out.println("Selected ID doesn't exist! Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid ID.");
                sc.nextLine(); 
            }
        }

        String qry = "DELETE FROM tbl_tickets WHERE t_id = ?";
        conf.deleteRecord(qry, id);
}
}