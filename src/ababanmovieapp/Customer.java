package ababanmovieapp;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Customer {
    
    public void cTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response = "yes";
        int action = -1;  
        Customer cs = new Customer ();
        
        do{
            
       
        System.out.println("====================");    
        System.out.println("WELCOME CUSTOMER!");
        System.out.println("====================");
        System.out.println("1. ADD CUSTOMER");
        System.out.println("2. VIEW CUSTOMER");
        System.out.println("3. UPDATE CUSTOMER");
        System.out.println("4. DELETE CUSTOMER");
        System.out.println("5. EXIT ");
        
        System.out.println("---------------");
        System.out.println("Enter Action: ");
         try {
                action = sc.nextInt(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid action, Please enter a numeric action.");
                sc.nextLine();
                continue; 
            }

            if (action < 1 || action > 5) {
                System.out.println("Invalid action, Please enter a number between 1 to 5.");
                continue; 
            }

        
        

        switch(action){
            case 1:
                cs.addCustomers();           
                break;
            case 2:       
                cs.viewCustomers();
                break;
            case 3:
                cs.viewCustomers();
                cs.updateCustomers();
                cs.viewCustomers();
                break;
            case 4:
                cs.viewCustomers();
                cs.deleteCustomers();
                cs.viewCustomers();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addCustomers(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Status: ");
        String stat = sc.next();

        String sql = "INSERT INTO tbl_customer (c_fname, c_lname, c_email, c_status) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, fname, lname, email, stat);


    }

    public void viewCustomers() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_customer";
        String[] Headers = {"Customers_ID","FirstName", "LastName", "Email", "Status"};
        String[] Columns = {"c_id", "c_fname", "c_lname", "c_email", "c_status"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateCustomers() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New First Name: ");
        String nfname = sc.next();
        System.out.println("New Last Name: ");
        String nlname = sc.next();
        System.out.println("New Email: ");
        String nemail = sc.next();
        System.out.println("New Status: ");
        String nstat = sc.next();
        String qry = "UPDATE tbl_customer SET c_fname = ?, c_lname = ?, c_email = ?, c_status = ? WHERE c_id = ?";
        
        
        conf.updateRecord(qry, nfname, nlname, nemail, nstat, id);         
        
        
    }
    
    private void deleteCustomers() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Customer ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_customer WHERE c_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}

