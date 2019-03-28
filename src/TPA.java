
 /****************************************************************/ 
 /*                      TPA	                            */ 
 /*                                                              */ 
 /****************************************************************/ 
 import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
 /** 
  * Summary description for TPA 
  * 
  */ 
 public class TPA extends JFrame 
 { 
 	// Variables declaration 
 	private JLabel jLabel1; 
 	private JPanel contentPane;
 	private JTable jTable1;
 	private JScrollPane jScrollPane1;	
	private JList jList1; 
 	private JScrollPane jScrollPane2; 
 	DefaultTableModel model = new DefaultTableModel();
	private JButton jButton1; 
	int i=0;
 	// End of variables declaration 
  
	String user;
 	public TPA() 
 	{ 
 		super(); 
 		initializeComponent(); 
 		// 
 		// TODO: Add any constructor code after initializeComponent call 
 		// 
 		loaddata();
 		this.setVisible(true); 
 		//integrity();
 	} 
  
 	/** 
 	 * This method is called from within the constructor to initialize the form. 
 	 * WARNING: Do NOT modify this code. The content of this method is always regenerated 
 	 * by the Windows Form Designer. Otherwise, retrieving design might not work properly. 
 	 * Tip: If you must revise this method, please backup this GUI file for JFrameBuilder 
 	 * to retrieve your design properly in future, before revising this method. 
 	 */ 
 	private void initializeComponent() 
 	{ 
 		jLabel1 = new JLabel(); 
 		contentPane = (JPanel)this.getContentPane(); 
  
 		// 
 		// jLabel1 
 		// 
 		jLabel1.setText("In Progress......"); 
 		jButton1 = new JButton(); 
 		
 		jScrollPane1 = new JScrollPane();
 		jTable1 = new JTable(model);
 		jList1 = new JList(); 
 		jScrollPane2 = new JScrollPane();
 		
 	
 		
 		model.addColumn("Serial No");
		model.addColumn("User");
		model.addColumn("File Name");
		model.addColumn("Operation");
		model.addColumn("Time");
		jScrollPane1.setViewportView(jTable1);
		jScrollPane2.setViewportView(jList1); 
 		// 
 		// contentPane 
 		// 
		
		
		jButton1.setText("Start"); 
 		jButton1.addActionListener(new ActionListener() { 
 			public void actionPerformed(ActionEvent e) 
 			{ 
 				i=1;
 				if(i==1)
 				{
 					integrity();
 				}
 			} 
  
 		});
 		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
						
												System.exit(0);
		
			}
		}
	);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		contentPane.setLayout(null); 
 		//addComponent(contentPane, jLabel1, 44,30,100,99); 
 		//addComponent(contentPane, jScrollPane2, 15,61,149,146); 
 		addComponent(contentPane, jButton1, 74,14,83,28); 
 		addComponent(contentPane, jScrollPane1, 15, 61, 700, 200);
 		// 
 		// TPA 
 		// 
 		this.setTitle("TPA - extends JFrame"); 
 		this.setLocation(new Point(0, 0)); 
 		this.setSize(new Dimension(790, 500)); 
 	} 
  
 	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
 	private void addComponent(Container container,Component c,int x,int y,int width,int height) 
 	{ 
 		c.setBounds(x,y,width,height); 
 		container.add(c); 
 	} 
  
 	// 
 	// TODO: Add any method code to meet your needs in the following area 
 	// 
 	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
  
	private void integrity() 
 	{ 
		String[] fl;
		int cnt=0,n;
		 try
			{
			 Thread.sleep(5000);
				int count = 0;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:desktop","sa","");
			Statement st=con.createStatement();
			Statement st1=con.createStatement();
			Statement st2=con.createStatement();
			Statement st3=con.createStatement();
			Statement st4=con.createStatement();
			Statement st5=con.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			File file;
			ResultSet rs1,rs2,rs3,rs4,rs5;
			//String fragname="onnumila";
			//ResultSet rs1= st.executeQuery("Select filename, max(opt_time) from Usage_History group by filename");
			ResultSet rs= st.executeQuery("Select filename from Usage_History");
			
			rs1=st2.executeQuery("select * from accesslog");
			
			while(rs1.next())
			{
				cnt=rs1.getInt(1);
			}
			Integer[] index = new Integer[cnt];
			for (int i = 0; i < index.length; i++) {
		        index[i] = i;
		    }
			Collections.shuffle(Arrays.asList(index));
			for(int i=0;i<cnt;i++)
			{
				//String filename = rs.getString(2);
				
				//System.out.println(fragname);
				rs2=st1.executeQuery("select * from accesslog where index ='"+index[i]+"'")	;
				rs3=st2.executeQuery("select * from duplicate where index ='"+index[i]+"'");
				String no=index[i].toString();
				if(rs2.next()){
					if(rs3.next())
					{
					String fragname = rs2.getString(2);
					String fragname1 = rs3.getString(2);
					String duplicate;
					duplicate=fragname1;
					File f=new File(duplicate);
					if(f.exists())
					{
						JOptionPane.showMessageDialog(null,fragname+" is periodically Audited and Secured","TPA",JOptionPane.INFORMATION_MESSAGE);
						System.out.println(fragname);
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,fragname1+" is missing","TPA",JOptionPane.INFORMATION_MESSAGE);
						n = st3.executeUpdate("update accesslog set fragname='"+fragname1+"' where index ='"+no+"'");
						JOptionPane.showMessageDialog(null,fragname1+" is is recovered","TPA",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("File not found");
						
						for(i=1;i<5;i++)
						{
							char c=(char)('0'+i);
							
							StringBuilder storage = new StringBuilder(duplicate);
							storage.setCharAt(3, c);
						
							
							System.out.println("File location:"+storage);
							File f1=new File(storage.toString());
							if(f1.exists())
							{
								Files.copy(f1.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
								System.out.println("File copied");
								int end = duplicate.length();
								String filename1 = duplicate.substring(8,end);
								String username = null;
								rs4=st4.executeQuery("select user_name from ftransaction where file_name ='"+filename1+"'");
								if(rs4.next())
									username = rs4.getString(1);
								System.out.println(username);
								rs5=st5.executeQuery("select email from login where username ='"+username+"'");
								String msg="Hello, this is to Inform you that your file's integrity is at risk "+
								" We have recovered your file, Please login to know more details and make a local backup copy ";
								if(rs5.next())
									new mail(rs5.getString(1),msg);
								break;
							}
							else
								continue;
						}
						
						
					}
					if(count%2==0)
					Thread.sleep(3*1000);
					count++;
					}
				}
				rs2.close();
				rs3.close();
			
			}
			rs.close();
			st.close();
			rs1.close();
			st1.close();
			
			st2.close();
			
			st3.close();
			con.close();
			 Thread.sleep(5*1000);
			}
			catch(Exception e1)
			{
			e1.printStackTrace();
			}
		loaddata();
		integrity();
 	} 
  
  
	 public void run()
	   {
	   
	   }
  
  
  public void loaddata()
  {
	  try
		{
			int count = 0;
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:desktop","sa","");
		Statement st=con.createStatement();
		
		ResultSet rs= st.executeQuery("Select * from Usage_History");
		while (rs.next()) {
			
			model.insertRow(count, new Object[] { count + 1, rs.getString(1),
					rs.getString(2), rs.getString(3), rs.getString(4) });
			count++;
		}
		rs.close();
		st.close();
		con.close();
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
   
  
 //============================= Testing ================================// 
 //=                                                                    =// 
 //= The following main method is just for testing this class you built.=// 
 //= After testing,you may simply delete it.                            =// 
 //======================================================================// 
 	public static void main(String[] args) 
 	{ 
 		JFrame.setDefaultLookAndFeelDecorated(true); 
 		JDialog.setDefaultLookAndFeelDecorated(true); 
 		try 
 		{ 
 			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
 		} 
 		catch (Exception ex) 
 		{ 
 			System.out.println("Failed loading L&F: "); 
 			System.out.println(ex); 
 		} 
 		new TPA(); 
 	} 
 //= End of Testing = 
  
  
 } 
  
 