
package java_attandance_record;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;


public class Java_Attandance_Record {
    
     private static String url = "jdbc:mysql://localhost:3306/attandance";
     private static String username = "root";
     private static String password = "";
     static Connection con = null;
     
     
     
    public static void main(String[] args) {
       
        System.out.println("Please provide your id & password");  
        System.out.println("-----------------------------------");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Id       : ");
        int id = sc.nextInt();
        
        System.out.print("Password : ");
        String pass = sc.next();
        
        System.out.println("");
        
        Date date = new Date();
        
        long ts = date.getTime();
        
        Timestamp tt = new Timestamp(ts);
        //System.out.println("date : "+tt);
        String time = tt.toString();
        
       String logintime = tt.toString();
        
        try{
            

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            
            PreparedStatement stmt = con.prepareStatement("select * from demo where id = "+id+" and password = '"+pass+"' ");
            ResultSet rs = stmt.executeQuery();
            
            
            
            if(!rs.next()){
             
                  System.out.println("no user found");
              
            }  else{
            
                try{

                    String fname = rs.getString("firstname");
                    String lname = rs.getString("lastname");
                    String fullname = fname+" "+lname;
                    
                   Statement checkLogout = con.createStatement();
                   ResultSet checkrs = checkLogout.executeQuery("select logout_time from attandance where user_id = "+id+" and logout_time is null");
                   
                   if(checkrs.next())
                   {
                       PreparedStatement stmt3 = con.prepareStatement("update attandance set logout_time = '"+time+"' where user_id = "+id);
                       
                       int rsUpdate = stmt3.executeUpdate();
                       
                       if(rsUpdate > 0)
                       {
                           System.out.println("Logout Time captured");
                       }
                    }
                   
                   else{
                    PreparedStatement stmt2 = con.prepareStatement("insert into attandance(user_id, name, login_time) values(?,?,?)");
                    stmt2.setInt(1, id);
                    stmt2.setString(2, fullname);
                    stmt2.setString(3, logintime);
                    
                    int insertUpdate = stmt2.executeUpdate();
                    
                    if(insertUpdate > 0)
                    {
                        
                        System.out.println("Attandance Recorded Successfully");
                    }
                   
                    
                   }
                    
                }
                
                catch(Exception e)
                {
                    e.printStackTrace();
                }
          }
          
            con.close();
            int CLOSE_ALL_RESULTS = Statement.CLOSE_ALL_RESULTS;
            int CLOSE_ALL_RESULTS1 = PreparedStatement.CLOSE_ALL_RESULTS;
         
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }
        
        
    }
    

