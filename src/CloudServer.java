/****************************************************************/
/*                      Server	                            */
/*                                                              */
/****************************************************************/
//import ch.randelshofer.quaqua.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CloudServer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration
	private JLabel jLabel1,jLabel2;
	public static JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton Cmd_Clear;
	private JButton Cmd_logout;
	private JPanel contentPane;
	static java.util.Date date= new java.util.Date();
	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm a");
	// End of variables declaration
	

	public CloudServer()
	{
		super();
		initializeComponent();
		this.setVisible(true);
		this.setResizable(false);
	}

	private void initializeComponent()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane();
		Cmd_Clear = new JButton();
		Cmd_logout = new JButton();
		contentPane = (JPanel)this.getContentPane();
		jLabel1.setText("Cloud Server");
		jLabel2.setText("File Request");
		
		jLabel1.setFont(new Font("Monotype Corsiva",Font.ITALIC,50));
		jLabel1.setForeground(new Color(149, 17, 109));
		jLabel2.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jLabel2.setForeground(new Color(149, 17, 109));
		jTextArea1.setBackground(new Color(242, 246, 249));
		
		Cmd_Clear.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		Cmd_logout.setFont(new Font("Comic Sans MS",Font.PLAIN,18));

		jTextArea1.setEnabled(false);
		jScrollPane1.setViewportView(jTextArea1);

		Cmd_Clear.setText("Clear");
		Cmd_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_Clear_actionPerformed(e);
			}

		});
		
		Cmd_logout.setText("Logout");
		Cmd_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_logout_actionPerformed(e);
			}

		});
		
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(179, 226, 228)); 
		addComponent(contentPane, jLabel1, 88,46,310,53);
		addComponent(contentPane, jLabel2, 35,120,110,35);

		addComponent(contentPane, jScrollPane1, 29,150,306,180);
		addComponent(contentPane, Cmd_Clear, 350,210,90,39);
		addComponent(contentPane, Cmd_logout, 350,259,90,39);
		
		this.setTitle("Cloud Server");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(470, 397));
	}


	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}
	private void Cmd_Clear_actionPerformed(ActionEvent e)
	{
		jTextArea1.setText("");


	}

	private void Cmd_logout_actionPerformed(ActionEvent e)
	{
		System.out.println("\nCmd_logout_actionPerformed(ActionEvent e) called.");
		dispose();
		System.exit(0);

	}
	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		
		
		Socket vss1=null,vss2=null,vss3=null,vss4=null;
		String VUser1;
		String VUser2;
		String VUser3;
		String VUser4;
		String enc_file="",frgfile;
		byte b[]=null;
		int i=0;
		DataInputStream dis,disv1=null,disv2,disv3,disv4;
		DataOutputStream dos,dosv1,dosv2,dosv3,dosv4;
		ency enc=new ency();
		decy dec=new decy();

	
		CloudServer gs=new CloudServer();
		String request,res = null;
		String path="",file="",user,key="",data="",def_file="",fra_file="",file_data;
		String status1,status2,status3,status4="";
		String []status={"","","",""};
		Vector enc_res= new Vector(5);
		byte []dat;
		
		try
		{
		ServerSocket ss = new ServerSocket(6543);
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con;
		Statement stmt;
		ResultSet rs;
		int cnt = 0;
		VUser1=gs.sys("server\\vss1.txt");
		VUser2=gs.sys("server\\vss2.txt");
		VUser3=gs.sys("server\\vss3.txt");
		VUser4=gs.sys("server\\vss4.txt");
		while(true)
			{
	 		Socket s = ss.accept();
			System.out.println("Connection establised");
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
			
			con=DriverManager.getConnection("Jdbc:Odbc:desktop","sa","");
			stmt=con.createStatement();
			request = dis.readUTF();
			System.out.println(request);
			if(request.equalsIgnoreCase("login"))
			{
				String s1,s2,s3=new String();
				s1=	dis.readUTF();
				s2= dis.readUTF();
				s3= dis.readUTF();
				System.out.println("Username="+s1);
				System.out.println("password="+s2+s3);
				 rs=stmt.executeQuery("select * from login where username='"+s1+"'and pword='"+s2+"' and profile='"+s3+"'");
				if(rs.next())
					{
						res="Success";
						jTextArea1.append("User "+s1+"  is Successfully Logged In..\n");
					}
				else
					{
					jTextArea1.append("User "+s1+"  is Unauthorised user..\n");
						res="Failure";
					}
				System.out.println(res);
				dos.writeUTF(res);
			}
			else if(request.equalsIgnoreCase("signup"))
			{

				String pass1=new String();
				String email=new String();
				user= dis.readUTF();
				pass1= dis.readUTF();
				String profile=dis.readUTF();
				email= dis.readUTF();
				
				ResultSet rs1=stmt.executeQuery("select * from login where username='"+user+"'");
				if(rs1.next())
				{
					jTextArea1.append("User "+user+"  is Signup failed..\n");
					res="failure";
				}
				else
				{
					res="success";
					jTextArea1.append("User "+user+"  is Successfully Added..\n");
					stmt.executeUpdate("insert into login values('"+user+"' , '"+pass1+"','"+profile+"','"+email+"')");
				}
				System.out.println(res);
				dos.writeUTF(res);
			}
			else if(request.equalsIgnoreCase("isavailable"))
			{
				file=dis.readUTF();
				rs=stmt.executeQuery("select * from ftransaction where file_name='"+file+"' ");
				System.out.println(" database query executed");
				if(rs.next())
				{
					res="true";
					
				}
				else
				{
					res="false";
					
				}
				System.out.println(res);
				dos.writeUTF(res);
					
				
			}
			else if (request.equalsIgnoreCase("availfile")) {
					String fname="";
					res="";
					user = dis.readUTF();
					rs = stmt.executeQuery("select file_name from ftransaction where user_name='"
									+ user + "'");
					System.out.println("Query executed");
					
					while (rs.next()) {
						fname = rs.getString(1);
						res+=fname+"#";
					}
					
					rs = stmt.executeQuery("select filename from access where user='"+ user + "' and access ='read'");
					while (rs.next()) {
						fname = rs.getString(1);
						res+=fname+"#";
					}
			System.out.println("Query executed");
					System.out.println(res);
					dos.writeUTF(res);

				}
			else if (request.equalsIgnoreCase("availfilewrite")) {
				String fname="";
				res="";
				user = dis.readUTF();
				rs = stmt.executeQuery("select file_name from ftransaction where user_name='"
								+ user + "'");
				System.out.println("Query executed");
				
				while (rs.next()) {
					fname = rs.getString(1);
					res+=fname+"#";
				}
				
				rs = stmt.executeQuery("select filename from access where user='"+ user + "' and access ='write'");
				while (rs.next()) {
					fname = rs.getString(1);
					res+=fname+"#";
				}
		System.out.println("Query executed");
				System.out.println(res);
				dos.writeUTF(res);

			}
			else if (request.equalsIgnoreCase("availfiledel")) {
				String fname="";
				res="";
				user = dis.readUTF();
				rs = stmt.executeQuery("select file_name from ftransaction where user_name='"
								+ user + "'");
				System.out.println("Query executed");
				
				while (rs.next()) {
					fname = rs.getString(1);
					res+=fname+"#";
				}
				
				rs = stmt.executeQuery("select filename from access where user='"+ user + "' and access ='delete'");
				while (rs.next()) {
					fname = rs.getString(1);
					res+=fname+"#";
				}
		System.out.println("Query executed");
				System.out.println(res);
				dos.writeUTF(res);

			}
			
			
			else if(request.equalsIgnoreCase("delfile"))
			{
				file=dis.readUTF();
				 user=dis.readUTF();
				
				 ;
				stmt.executeUpdate("delete * from ftransaction where file_name='"+file+"' ");
				stmt.executeUpdate("delete * from access where filename='"+file+"' ");
				 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
				 String formattedDate = sdf.format(date);
				stmt.executeUpdate("insert into Usage_history values ('"+user+"','"+file+"','Delete','"+formattedDate+"')");
				System.out.println(" delete  database query executed");
			
			}
			else if (request.equalsIgnoreCase("insertaccess")) {
				String fname="";
				res="";
				fname = dis.readUTF();
				String value []=fname.split("#");
				if(value[2].equals("true"))
				{
				int n = stmt.executeUpdate("insert into access values('"+value[0]+"','"+value[1]+"','read')");
				}
				if(value[3].equals("true"))
				{
				int n = stmt.executeUpdate("insert into access values('"+value[0]+"','"+value[1]+"','write')");
				}
				if(value[4].equals("true"))
				{
				int n = stmt.executeUpdate("insert into access values('"+value[0]+"','"+value[1]+"','delete')");
				}
				System.out.println("Query executed");
				stmt.close();
				System.out.println(res);
				dos.writeUTF(res);

			}
			
			else if (request.equalsIgnoreCase("availuser")) {
				String fname="";
				res="";
				
				rs = stmt.executeQuery("select username from login");
				System.out.println("Query executed");
				while (rs.next()) {
					fname = rs.getString(1);
					res+=fname+"#";
				}
				System.out.println(res);
				dos.writeUTF(res);

			}

			else if(request.equalsIgnoreCase("Sendfile"))
				{
				System.out.println("Inside the if condition");
				user=dis.readUTF();
				file=dis.readUTF();
				file_data=dis.readUTF();
				System.out.println("User name="+user+"\nFile Name="+file+"\n file data="+ file_data);
				jTextArea1.append("User "+user+" send "+file+" file\n");
				FileOutputStream fout=new FileOutputStream("CloudServer\\"+file);
				dat=new  byte[file_data.length()];
				dat=file_data.getBytes();
				fout.write(dat);
				fout.close();
				path="CloudServer\\"+file;
				//data encription
				enc_res.clear();
				enc_res=enc.test(path,file);
				key=(String )enc_res.get(0);
				enc_file=(String )enc_res.get(1);
				String username=null;
				System.out.println("Encripted file name is "+enc_file);
				System.out.println("Encripted Key  is "+key);
				rs=stmt.executeQuery("SELECT user_name FROM fTransaction where file_name='"+file+"'");
				while(rs.next())
				{
					username=rs.getString(1);
					System.out.println("Username : "+username);
				}
				rs=stmt.executeQuery("SELECT email FROM login where username='"+username+"'");
				while(rs.next())
				{
					String email_id=rs.getString(1);
					System.out.println("email id : "+email_id);
					String msg="Your file has been modified by the User : "+user+ " , To know more about the modification, Please Login";
					new mail(email_id,msg);
				}
				//----------------update the transaction
				String mode=dis.readUTF();
				int n;
				
			
				 
				if(mode.equals("create"))
				{
					n = stmt.executeUpdate("insert into fTransaction(user_name,file_name,s_key,encriptedfile) values('"+user+"','"+file+"','"+key+"','"+enc_file+"')");
					
					 String formattedDate = sdf.format(date);
					
					n=stmt.executeUpdate("insert into Usage_history values ('"+user+"','"+file+"','create','"+formattedDate+"')");
							
				}
				else
				{
					n = stmt.executeUpdate("update fTransaction set s_key='"+key+"' where file_name ='"+file+"'");
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
					 String formattedDate = sdf.format(date);
					
					n=stmt.executeUpdate("insert into Usage_history values ('"+user+"','"+file+"','update','"+formattedDate+"')");
				}
				System.out.println( "affected record"+ n );
				enc_res.clear();
				//stmt.close();
				//con.close();
				System.out.println("Transaction Inserted to database");
				
				//data fragmentaion
				enc_res=enc.spilit(enc_file);
				System.out.println("File Spilited");
				
				//connection to Volunteer1 
				for( i=0;i<4;i++)
				{
				vss1= new Socket(VUser1,7777);
				dosv1=new DataOutputStream(vss1.getOutputStream());		
				dosv1.writeUTF("send");
				frgfile=(String)enc_res.get(i);
				dosv1=new DataOutputStream(vss1.getOutputStream());
				dosv1.writeUTF(frgfile);
				System.out.println("fragement  name="+frgfile);
				data=gs.fileToString(frgfile);
				System.out.println("fragement  data="+data);
				dosv1.writeUTF(data);
				System.out.println("fragement  send");
				String formattedDate = sdf.format(date);
				rs=stmt.executeQuery("select * from accesslog");
				while(rs.next())
				{
					cnt=rs.getInt(1);
				}
				cnt++;
				n=stmt.executeUpdate("insert into accesslog values ('"+cnt+"','vss1\\"+frgfile+"','"+formattedDate+"')");
				n=stmt.executeUpdate("insert into duplicate values ('"+cnt+"','vss1\\"+frgfile+"','"+formattedDate+"')");
				System.out.println("access log updated");
				dosv1.close();
				vss1.close();
				}
				//connection to Volunteer2......... 

				for( i=0;i<4;i++)
				{
				vss2=new Socket(VUser2,8888);
				dosv2=new DataOutputStream(vss2.getOutputStream());	
				dosv2.writeUTF("send");
				frgfile=(String)enc_res.get(i);
				dosv2=new DataOutputStream(vss2.getOutputStream());
				dosv2.writeUTF(frgfile);
				System.out.println("fragement  name="+frgfile);
				data=gs.fileToString(frgfile);
				System.out.println("fragement  data="+data);
				dosv2.writeUTF(data);
				System.out.println("fragement  send");
				String formattedDate = sdf.format(date);
				rs=stmt.executeQuery("select * from accesslog");
				while(rs.next())
				{
					cnt=rs.getInt(1);
				}
				cnt++;
				n=stmt.executeUpdate("insert into accesslog values ('"+cnt+"','vss2\\"+frgfile+"','"+formattedDate+"')");
				n=stmt.executeUpdate("insert into duplicate values ('"+cnt+"','vss2\\"+frgfile+"','"+formattedDate+"')");
				
				System.out.println("access log updated");
				dosv2.close();
				vss2.close();
				}


				//connection to Volunteer3......... 
			
				for( i=0;i<4;i++)
				{
				vss3=new Socket(VUser3,9999);
				dosv3=new DataOutputStream(vss3.getOutputStream());	
				dosv3.writeUTF("send");
				frgfile=(String)enc_res.get(i);
				dosv3=new DataOutputStream(vss3.getOutputStream());
				dosv3.writeUTF(frgfile);
				System.out.println("fragement  name="+frgfile);
				data=gs.fileToString(frgfile);
				System.out.println("fragement  data="+data);
				dosv3.writeUTF(data);
				System.out.println("fragement  send");
				String formattedDate = sdf.format(date);
				rs=stmt.executeQuery("select * from accesslog");
				while(rs.next())
				{
					cnt=rs.getInt(1);
				}
				cnt++;
				n=stmt.executeUpdate("insert into accesslog values ('"+cnt+"','vss3\\"+frgfile+"','"+formattedDate+"')");
				n=stmt.executeUpdate("insert into duplicate values ('"+cnt+"','vss3\\"+frgfile+"','"+formattedDate+"')");
				System.out.println("access log updated");
				dosv3.close();
				vss3.close();
				}

			//connection to Volunteer4......... 

			
				for( i=0;i<4;i++)
				{
				vss4=new Socket(VUser4,7788);
				dosv4=new DataOutputStream(vss4.getOutputStream());	
				dosv4.writeUTF("send");
				frgfile=(String)enc_res.get(i);
				dosv4=new DataOutputStream(vss4.getOutputStream());
				dosv4.writeUTF(frgfile);
				System.out.println("fragement  name="+frgfile);
				data=gs.fileToString(frgfile);
				System.out.println("fragement  data="+data);
				dosv4.writeUTF(data);
				System.out.println("fragement  send");
				String formattedDate = sdf.format(date);
				rs=stmt.executeQuery("select * from accesslog");
				while(rs.next())
				{
					cnt=rs.getInt(1);
				}
				cnt++;
				n=stmt.executeUpdate("insert into accesslog values ('"+cnt+"','vss4\\"+frgfile+"','"+formattedDate+"')");
				n=stmt.executeUpdate("insert into duplicate values ('"+cnt+"','vss4\\"+frgfile+"','"+formattedDate+"')");
				System.out.println("access log updated");
				dosv4.close();
				vss4.close();
				}
				stmt.close();
				con.close();
				
				jTextArea1.append(file +"Successfully saved");
				dos.writeUTF("ACK");
				enc_res.clear();
			 }
			
			
//
//				to View the file 
//
		else if(request.equalsIgnoreCase("Viewfile"))
			{
				
				String fra1="",fra2="",fra3="",fra4="",avil="s";
				key="";
				file="";
				System.out.println("Inside the view condition");
				file=dis.readUTF();
				user=dis.readUTF();
				jTextArea1.append("\n File Request "+file);
				System.out.println("File Name="+file);
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("Jdbc:Odbc:desktop","sa","");
				stmt=con.createStatement();
				rs=stmt.executeQuery("select s_key from ftransaction where file_name='"+file+"'");
				System.out.println("Query Executed");
				if(rs.next())
					{
						key=rs.getString(1);
						System.out.println("File Name="+file+"\nKey value="+key);
					}
				else
					{
						System.out.println("No key value present or decription");
						JOptionPane.showMessageDialog(null,"No key value present or decription","key Not Found",JOptionPane.INFORMATION_MESSAGE);
					}
				// connect to volunteers to read the file
						
				
				String formattedDate = sdf.format(date);
				
				stmt.executeUpdate("insert into Usage_history values ('"+user+"','"+file+"','read','"+formattedDate+"')");
				i=0;
				rs=stmt.executeQuery("select status from v_status ");
				while(rs.next())
				{
				status[i]=rs.getString(1);
				System.out.println("volunteer  status =="+status[i]);
				jTextArea1.append("\nVolunteer "+(i+1)+"  Status =="+status[i]);
				i++;
				}
				stmt.close();
				con.close();
//				Reading first block
				if (status[0].equalsIgnoreCase("True"))
				 {
					System.out.println("Connecting to V1");
					System.out.println("V1 next");
					vss1= new Socket(VUser1,7777);
					dosv1=new DataOutputStream(vss1.getOutputStream());
					disv1=new DataInputStream(vss1.getInputStream());
					dosv1.writeUTF("read");
					fra_file="fr1"+file;
					System.out.println(fra_file);
					dosv1.writeUTF(fra_file);
					fra1=disv1.readUTF();
					dosv1.close();
					disv1.close();
					vss1.close();
					System.out.println("V1 CLOSED");
				 }
				 else if(status[2].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V3");
					vss3= new Socket(VUser3,9999);
					dosv3=new DataOutputStream(vss3.getOutputStream());
					disv3=new DataInputStream(vss3.getInputStream());
					dosv3.writeUTF("read");
					fra_file="fr1"+file;
					dosv3.writeUTF(fra_file);
					fra1=disv3.readUTF();
					dosv3.close();
					disv3.close();
					vss3.close();
				  }
				  else if(status[3].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V4");
					vss4= new Socket(VUser4,7788);
					dosv4=new DataOutputStream(vss4.getOutputStream());
					disv4=new DataInputStream(vss4.getInputStream());
					dosv4.writeUTF("read");
					fra_file="fr1"+file;
					dosv4.writeUTF(fra_file);
					fra1=disv4.readUTF();
					dosv4.close();
					disv4.close();
					vss4.close();
				  }
				 else
				   {
						System.out.println("No Source available to read first fragment data");
						JOptionPane.showMessageDialog(null,"No source available to get the fragement one","Grid",JOptionPane.INFORMATION_MESSAGE);
						avil="n";
//						goto L1;
				   }
					System.out.println("first fragment data is===="+fra1);

//				Reading Second  block
				if (status[0].equalsIgnoreCase("True"))
				 {
					System.out.println("Connecting to V1");
					vss1= new Socket(VUser1,7777);
					dosv1=new DataOutputStream(vss1.getOutputStream());
					disv1=new DataInputStream(vss1.getInputStream());
					dosv1.writeUTF("read");
					fra_file="fr2"+file;
					dosv1.writeUTF(fra_file);
					fra2=disv1.readUTF();
					dosv1.close();
					disv1.close();
					vss1.close();
				 }
				 else if(status[1].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V2");
					vss2= new Socket(VUser2,8888);
					dosv2=new DataOutputStream(vss2.getOutputStream());
					disv2=new DataInputStream(vss2.getInputStream());
					dosv2.writeUTF("read");
					fra_file="fr2"+file;
					dosv2.writeUTF(fra_file);
					fra2=disv2.readUTF();
					dosv2.close();
					disv2.close();
					vss2.close();
				  }
				  else if(status[3].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V4");
					vss4= new Socket(VUser4,7788);
					dosv4=new DataOutputStream(vss4.getOutputStream());
					disv4=new DataInputStream(vss4.getInputStream());
					dosv4.writeUTF("read");
					fra_file="fr2"+file;
					dosv4.writeUTF(fra_file);
					fra2=disv4.readUTF();
					dosv4.close();
					disv4.close();
					vss4.close();
				  }
				 else
				   {
						System.out.println("No Source available to read Second fragment data");
						JOptionPane.showMessageDialog(null,"No source available to get the second fragement ","Grid",JOptionPane.INFORMATION_MESSAGE);
						avil="n";
//						goto l1;
				   }
					System.out.println("Second fragment data is===="+fra2);


//				Reading third  block
				if (status[0].equalsIgnoreCase("True"))
				 {
					System.out.println("Connecting to V1");
					vss1= new Socket(VUser1,7777);
					dosv1=new DataOutputStream(vss1.getOutputStream());
					disv1=new DataInputStream(vss1.getInputStream());
					dosv1.writeUTF("read");
					fra_file="fr3"+file;
					dosv1.writeUTF(fra_file);
					fra3=disv1.readUTF();
					dosv1.close();
					disv1.close();
					vss1.close();
				 }
				 else if(status[1].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V2");
					vss2= new Socket(VUser2,8888);
					dosv2=new DataOutputStream(vss2.getOutputStream());
					disv2=new DataInputStream(vss2.getInputStream());
					dosv2.writeUTF("read");
					fra_file="fr3"+file;
					dosv2.writeUTF(fra_file);
					fra3=disv2.readUTF();
					dosv2.close();
					disv2.close();
					vss2.close();
				  }
				  else if(status[2].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V3");
					vss3= new Socket(VUser3,9999);
					dosv3=new DataOutputStream(vss3.getOutputStream());
					disv3=new DataInputStream(vss3.getInputStream());
					dosv3.writeUTF("read");
					fra_file="fr3"+file;
					dosv3.writeUTF(fra_file);
					fra3=disv3.readUTF();
					dosv3.close();
					disv3.close();
					vss3.close();
				  }
				 else
				   {
						System.out.println("No Source available to read Third fragment data");
						JOptionPane.showMessageDialog(null,"No source available to get the Third fragement ","Grid",JOptionPane.INFORMATION_MESSAGE);
						avil="n";
//						goto l1;
				   }
					System.out.println("Third fragment data is===="+fra3);

//				Reading Fourth block
				if (status[1].equalsIgnoreCase("True"))
				 {
					System.out.println("Connecting to V2");
					vss2= new Socket(VUser2,8888);
					dosv2=new DataOutputStream(vss2.getOutputStream());
					disv2=new DataInputStream(vss2.getInputStream());
					dosv2.writeUTF("read");
					fra_file="fr4"+file;
					dosv2.writeUTF(fra_file);
					fra4=disv2.readUTF();
					dosv2.close();
					disv2.close();
					vss2.close();
				 }
				 else if(status[2].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V3");
					vss3= new Socket(VUser3,9999);
					dosv3=new DataOutputStream(vss3.getOutputStream());
					disv3=new DataInputStream(vss3.getInputStream());
					dosv3.writeUTF("read");
					fra_file="fr4"+file;
					dosv3.writeUTF(fra_file);
					fra4=disv3.readUTF();
					dosv3.close();
					disv3.close();
					vss3.close();
				  }
				  else if(status[3].equalsIgnoreCase("True"))
				  {
					System.out.println("Connecting to V4");
					vss4= new Socket(VUser4,7788);
					dosv4=new DataOutputStream(vss4.getOutputStream());
					disv4=new DataInputStream(vss4.getInputStream());
					dosv4.writeUTF("read");
					fra_file="fr4"+file;
					dosv4.writeUTF(fra_file);
					fra4=disv4.readUTF();
					dosv4.close();
					disv4.close();
					vss4.close();
				  }
				 else
				   {
						System.out.println("No Source available to read Fourth fragment data");
						JOptionPane.showMessageDialog(null,"No source available to get the Fourth fragement ","Grid",JOptionPane.INFORMATION_MESSAGE);
	        		    avil="n";
//						goto l1;
					}
					System.out.println("Fourth fragment data is===="+fra4);
					

				//Defragement the data file
				if (avil.equals("s"))
				{
				def_file="def"+file;
				System.out.println("defragement file name is="+def_file);
				FileOutputStream fout=new FileOutputStream("CloudServer\\"+def_file);
				b=null;
				b=fra1.getBytes();
				fout.write(b);
				b=null;
				b=fra2.getBytes();
				fout.write(b);

				b=null;
				b=fra3.getBytes();
				fout.write(b);

				b=null;
				b=fra4.getBytes();
				fout.write(b);

				data=gs.fileToString(def_file);
				System.out.println("defragement file contain="+data);
				String s_file="CloudServer\\"+def_file;
				data="";
				fout.flush();
				dec.test(s_file,key,file);
				data=gs.fileToString(file);
				System.out.println("The decripted data is"+data);
				dos.writeUTF("data");
				dos.writeUTF(data);
				}
				else
				{
				dos.writeUTF("ERROR");
				System.out.println("No Full fragments avilable to view the file");
				JOptionPane.showMessageDialog(new CloudServer(),"No full source available to view the file ","Fragment not found",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			//stmt.close();
			//con.close();
		}
		}
		catch (Exception e)
		{
			e.printStackTrace( );
		}
	}

 String fileToString(String filename) throws IOException
	{
	FileInputStream fis=new FileInputStream("CloudServer\\"+filename);
	byte b[]=new byte[fis.available()];
	fis.read(b);
	String sd=new String(b);
	return sd;
	}
	String sys(String file)throws IOException
	{
		FileInputStream fis=new FileInputStream(file);
		byte b[]=new byte[fis.available()];
		fis.read(b);
		file=new String (b);
		return file;
	}

}
