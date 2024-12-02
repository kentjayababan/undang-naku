package ababanmovieapp;

import java.util.Scanner;

public class Report {

    public void viewReports() {
        Scanner sc = new Scanner(System.in);
        String response = "yes";
        int action = -1;
        
        Report rep = new Report ();       
        
        do {
            System.out.println("WELCOME TO REPORTS SECTION!");
            System.out.println("1. VIEW INDIVIDUAL REPORT");
            System.out.println("2. VIEW ALL REPORTS");
            System.out.println("3. EXIT");

            System.out.println("Enter Action: ");
            try {
                action = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid action, Please enter a numeric action.");
                sc.nextLine();
                continue;
            }

            if (action < 1 || action > 3) {
                System.out.println("Invalid action, Please enter a number between 1 to 3.");
                continue;
            }

            switch(action) {
                case 1:
                    rep.viewIndividualReport();
                    break;
                case 2:
                    rep.viewAllReports();
                    break;
                case 3:
                    System.out.println("Thank you! See you soon!");
                    break;
                default:
                    System.out.println("Invalid action. Try again!");
                    break;
            }

            System.out.println("Do you want to continue? (yes/no)");
            response = sc.next();
        } while(response.equalsIgnoreCase("yes"));
    }

    private void viewIndividualReport() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the ID of the report to view: ");
    int reportId = sc.nextInt();
    
    String qry = "SELECT t_id, c_fname, m_name, m_price, m_genre, m_duration, t_due, t_rcash, t_date, t_status FROM tbl_tickets "
                + "LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_tickets.c_id "
                + "LEFT JOIN tbl_movie ON tbl_movie.m_id = tbl_tickets.m_id "
                + "WHERE t_id = ?";
    
    
    String[] headers = {"T_ID", "Customer", "Movie Title", "Price", "Genre", "Duration", "Due", "Cash", "Date", "Order Status"};
    String[] columns = {"t_id", "c_fname", "m_name", "m_price", "m_genre", "m_duration", "t_due", "t_rcash", "t_date", "t_status"};
    
    
    config conf = new config();  
    conf.viewRecordById(qry, reportId, headers, columns);  

    }

    private void viewAllReports() {
        
        String qry = "SELECT t_id, c_fname, m_name, m_price, m_genre, m_duration, t_due, t_rcash, t_date, t_status FROM tbl_tickets "
                    + "LEFT JOIN tbl_customer ON tbl_customer.c_id = tbl_tickets.c_id "
                    + "LEFT JOIN tbl_movie ON tbl_movie.m_id = tbl_tickets.m_id";

        String[] headers = {"T_ID", "Customer", "Movie Title", "Price", "Genre", "Duration", "Due", "Cash", "Date", "Order Status"};
        String[] columns = {"t_id", "c_fname", "m_name", "m_price", "m_genre", "m_duration", "t_due", "t_rcash", "t_date", "t_status"};
        
        config conf = new config();
        conf.viewRecords(qry, headers, columns); 
    }
}
