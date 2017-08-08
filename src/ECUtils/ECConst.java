package ECUtils;
public class ECConst {
	public static String DB_NAME ="fx_texteditor";
	public static String DB_HOST="localhost";
	public static String DB_USER="root";
	public static String DB_PASS ="";
	public static String SQLS[] = 
	{
		"create table app_users (id INT NOT NULL AUTO_INCREMENT, u_name varchar(45), pass varchar(), security_ques varchar(120), answer varchar(80), PRIMARY KEY (id))",	
	};
}
