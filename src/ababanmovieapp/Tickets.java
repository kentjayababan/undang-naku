package ababanmovieapp;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Tickets {
    
    public void tsTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("WELCOME TO TICKET!");    
        System.out.println("1. ADD TICKET");
        System.out.println("2. VIEW TICKET");
        System.out.println("3. UPDATE TICKET");
        System.out.println("4. DELETE TICKET");
        System.out.println("5. Exit. ");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Tickets ts = new Tickets ();
        

        switch(action){
            case 1:
                ts.addTickets(); 
                ts.viewTickets();
                break;
            case 2:       
                ts.viewTickets();
                break;
            case 3:
                
                break;
            case 4:
                  
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
        
        System.out.print("Enter the ID of the Movie ");
        int mid = sc.nextInt();
        
        String msql = "SELECT m_id FROM tbl_movie WHERE m_id = ?";
        while(conf.getSingleValue(msql, mid) == 0){
            System.out.print("Movir does not exist, Select Again: ");
            mid = sc.nextInt();
            
        }
        System.out.println("Enter Quantity: ");
        double quantity = sc.nextDouble();
        
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
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate. format(format);

        String status = "Pending";
        
        String qry = "INSERT INTO tbl_tickets (c_id, m_id, t_due, t_rcash, t_date, t_status)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        conf.addRecord(qry, cid, mid, quantity, due, rcash, date, status);
        
    }
    
    public void viewTickets() {
    String qry = "SELECT t_id, c_fname, m_name, m_price, m_genre, m_duration, t_due, t_rcash, t_date, t_status FROM tbl_movie "
            + "LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_tickets.c_id "
            + "LEFT JOIN tbl_movie ON tbl_movie.b_id = tbl_tickets.m_id";

    String[] hrds = {"T_ID", "Customer", "Movie Title", "Price", "Genre", "Duration" , "Due" , "Cash", "Date" , "Order Status"};
    String[] clms = {"t_id", "c_fname", "m_name", "m_price", "m_genre", "m_duration" , "t_due" , "t_rcash", "t_date", "t_status"};
    config conf = new config();
    conf.viewRecords(qry, hrds, clms);
   }

}
