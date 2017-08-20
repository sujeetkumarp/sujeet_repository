package ECUtils;

import java.sql.*;
import static ECUtils.ECConst.*;
public class BaseDAO {
 public static Connection getCon(){
	 Connection con = null;
	 try {
		con = DriverManager.getConnection("jdbc:mysql://"+DB_HOST+":3306/"+DB_NAME, DB_USER, DB_PASS);
	} catch (SQLException e) {
		System.out.println("Exception Caught "+e);
	}
	return con;		
 }
 public static void closeCon(Connection con){
	 try {
		 con.close();
	} catch (SQLException e) {
		System.out.println("Exception Caught "+e);
	}
 }

}
