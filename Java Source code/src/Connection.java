import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Connection {
   Connection con=null;
   public static java.sql.Connection Dbconnection(){
	   try{
		   String username="root";
		   String password="zaq1XSW@";
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   java.sql.Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/artgallery?autoReconnect=true&useSSL=false", username, password);
		   return con;
	   }
	   catch(Exception e){
		   JOptionPane.showMessageDialog(null,e);
		   return null;
	   }
   }
}