

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;





public class testing {

     private static String url = "jdbc:mysql://localhost:3306/just";
     private static String username = "root";
     private static String password = "prabeer";
     static Connection con = null;

    public static void main(String[] args) throws Exception{
        

        Date date = new Date();
        
        long ts = date.getTime();
        
        Timestamp tt = new Timestamp(ts);
        System.out.println("date : "+tt);
        
        String time = tt.toString();
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
        
        Statement sql = con.createStatement();
        ResultSet rss = sql.executeQuery("select  * from attandance where user_id  = 1 and logout_time is null");
         
        
       
        if(rss.next())
        {
                String uniqineID = rss.getInt("user_id")+rss.getString("id");
                System.out.println("new unquiue id : "+uniqineID);
                
                
            
        }
        
        
            sql.close();
            con.close();
    }
    
}
