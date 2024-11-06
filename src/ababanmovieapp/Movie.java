package ababanmovieapp;

import java.util.Scanner;


public class Movie {
    
    public void mvTransaction(){
        
        Scanner sc = new Scanner (System.in);
        String response;
        do{
            
       
        System.out.println("WELCOME TO MOVIE!");    
        System.out.println("1. ADD MOVIE");
        System.out.println("2. VIEW MOVIE");
        System.out.println("3. UPDATE MOVIE");
        System.out.println("4. DELETE MOVIE");
        System.out.println("5. EXIT ");
        
        System.out.println("Enter Action: ");
        int action = sc.nextInt();
        Movie mv = new Movie ();
        

        switch(action){
            case 1:
                mv.addMovie();           
                break;
            case 2:       
                mv.viewMovie();
                break;
            case 3:
                mv.viewMovie();
                mv.updateMovie();
                mv.viewMovie();
                break;
            case 4:
                mv.viewMovie();
                mv.deleteMovie();
                mv.viewMovie();    
                break;
        }
        System.out.println("Do you want to continue? (yes/no)");
        response = sc.next();
    }while(response.equalsIgnoreCase("yes"));
    System.out.println("Thank You, See you soonest!");
    
    }
    
    
    public void addMovie(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Movie Name: ");
        String mname = sc.nextLine();
        System.out.print("Price: ");
        double pr = sc.nextDouble();
        System.out.print("Genre: ");
        String gr = sc.next();
        System.out.print("Duration: ");
        String dt = sc.next();

        String sql = "INSERT INTO tbl_movie (m_name, m_price, m_genre, m_duration) VALUES (?, ?, ?, ?)";
        conf.addRecord(sql, mname, pr, gr, dt);


    }

    public void viewMovie() {
        config conf = new config();
        String Query = "SELECT * FROM tbl_movie";
        String[] Headers = {"Movie_ID","Movie Name", "Price", "Genre", "Duration"};
        String[] Columns = {"m_id", "m_name", "m_price", "m_genre", "m_duration"};
        
        
        conf.viewRecords(Query, Headers, Columns);
    }
    private void updateMovie() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter the ID to update: ");
        int id = sc.nextInt();
  
        while(conf.getSingleValue("SELECT m_id FROM tbl_movie WHERE m_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Movie ID Again: ");
        id = sc.nextInt();
        }
        
        System.out.println("New Movie Name: ");
        String nmname = sc.next();
        System.out.println("New Price: ");
        String npr = sc.next();
        System.out.println("New Genre: ");
        String ngr = sc.next();
        System.out.println("New Duration: ");
        String ndt = sc.next();
        String qry = "UPDATE tbl_movie SET m_name = ?, m_price = ?, m_genre = ?, m_duration = ? WHERE c_id = ?";
        
        
        conf.updateRecord(qry, nmname, npr, ngr, ndt, id);         
        
        
    }
    
    private void deleteMovie() {
        Scanner sc = new Scanner (System.in);
        config conf = new config();
        System.out.println("Enter the ID  to delete: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT  m_id FROM tbl_movie WHERE m_id = ?", id) == 0){
        System.out.println("Selected ID doesn't exist!");
        System.out.print("Select Movie ID Again: ");
        id = sc.nextInt();
        }
        
        String qry = "DELETE FROM tbl_movie WHERE m_id = ?";
        
       
        conf.deleteRecord(qry, id);
    }
}


