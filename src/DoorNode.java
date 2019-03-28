/****************************************************************/
/*                     DoorNode							*/
/*                                                              */
/****************************************************************/
//import ch.randelshofer.quaqua.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;
/**
 * Summary description for Server
 *
 */
class DoorNode extends JFrame
{
	// Variables declaration
	private JLabel jLabel2;
	private JLabel jLabel5;
	public static JTextArea TxtArea;
	private JScrollPane jScrollPane1;
	private JButton jButton1;
	private JButton Cmd_Exit;
	private JPanel contentPane;
	// End of variables declaration


	public DoorNode()
	{
		super();
		initializeComponent();


		this.setVisible(true);
		this.setResizable(false);
	}


	private void initializeComponent()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jLabel2 = new JLabel();
		jLabel5 = new JLabel();
		TxtArea = new JTextArea();
		jScrollPane1 = new JScrollPane();
		jButton1 = new JButton();
		Cmd_Exit = new JButton();
		Cmd_Exit.setHorizontalTextPosition(SwingConstants.CENTER);
		jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPane = (JPanel)this.getContentPane();
      	jLabel2.setIcon(new ImageIcon("doornode.jpg"));
		jLabel2.setFont(new Font("Monotype Corsiva",Font.ITALIC,50));
		jLabel2.setForeground(new Color(149, 17, 109));
		jLabel5.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jLabel5.setText("Login Details");
		jScrollPane1.setViewportView(TxtArea);
		TxtArea.setEnabled(false);
		jButton1.setText("Clear");
		jButton1.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		Cmd_Exit.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jButton1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});
		Cmd_Exit.setText("Log Out");
		Cmd_Exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Cmd_Exit_actionPerformed(e);
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

		// contentPane
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(179, 226, 228));
		addComponent(contentPane, jScrollPane1, 40,170,350,200);
		addComponent(contentPane, jButton1, 140,400,100,39);
		addComponent(contentPane, Cmd_Exit, 250,400,100,39);
		addComponent(contentPane, jLabel2, 100,15,225,75);
		addComponent(contentPane, jLabel5, 40,130,125,38);
		//
		// Server
		//
		this.setTitle("GATAWAY");
		this.setLocation(new Point(23, -4));
		this.setSize(new Dimension(500, 500));
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}


	private void jButton1_actionPerformed(ActionEvent e)
	{
		TxtArea.setText("");
		System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
	}


	private void Cmd_Exit_actionPerformed(ActionEvent e)
	{
		dispose();
		System.exit(0);
		System.out.println("\nCmd_Exit_actionPerformed(ActionEvent e) called.");
	}

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new DoorNode();
		try
		{

		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:desktop","sa","");
		Statement st=con.createStatement();
		String res,request;

		System.out.println("My Server...");
		ServerSocket ss = new ServerSocket(2121);
		while(true)
			{
	 		Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			request = dis.readUTF();
			if(request.equalsIgnoreCase("login"))
			{
				String s1,s2=new String();
				s1=	dis.readUTF();
				s2= dis.readUTF();
				System.out.println("Username="+s1);
				System.out.println("password="+s2);
				ResultSet rs=st.executeQuery("select * from login where username='"+s1+"'and pword='"+s2+"'");
				if(rs.next())
					{
						res="Success";
						TxtArea.append("User "+s1+"  is Successfully Logged In..\n");
					}
				else
					{
						TxtArea.append("User "+s1+"  is Unauthorised user..\n");
						res="Failure";
					}
				System.out.println(res);
				dos.writeUTF(res);
			}
			else if(request.equalsIgnoreCase("signup"))
			{

				String user,pass1=new String();
				user= dis.readUTF();
				pass1= dis.readUTF();
				ResultSet rs=st.executeQuery("select * from login where username='"+user+"'");
				if(rs.next())
				{
					TxtArea.append("User "+user+"  is Signup failed..\n");
					res="failure";
				}
				else
				{
					res="success";
					TxtArea.append("User "+user+"  is Successfully Added..\n");
					st.executeUpdate("insert into login values('"+user+"' , '"+pass1+"')");
				}
				System.out.println(res);
				dos.writeUTF(res);
			}

			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}

}
