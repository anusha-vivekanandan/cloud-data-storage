import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Date date= new java.util.Date();
		 System.out.println(new Timestamp(date.getTime()));

		 
		// Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		 String formattedDate = sdf.format(date);
		 System.out.println(formattedDate); // 12/01/2011 4:48:16 PM

	}

}
