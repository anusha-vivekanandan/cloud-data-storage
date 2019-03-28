
import java.sql.*;
public class fun
{
public void updateStatus(String v_name,String state)
{
   try
	{	
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("Jdbc:Odbc:desktop","sa","");
	Statement st=con.createStatement();
	if(state.equalsIgnoreCase("ON"))
		st.executeUpdate("update v_status set status='True' where volunteer='"+v_name+"'");
	else
		{
		System.out.println("Changing the status");
		st.executeUpdate("update v_status set status='False' where volunteer='"+v_name+"'");
		}
	st.close();
	con.close();
	}
catch (Exception e)
	{
	System.out.println("Error in database connection"+e);
	}
}
}