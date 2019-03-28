
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class file1 extends JFrame
{
	private JLabel jLabel2;
	private static JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton Cmd_Logout;
	private JPanel contentPane;

	public file1()
	{
		super();
		initializeComponent();
		new fun().updateStatus("vss1","ON");
		this.setVisible(true);	
		this.setResizable(false);
	}
	
	private void initializeComponent()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jLabel2 = new JLabel();
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane();
		Cmd_Logout = new JButton();
		contentPane = (JPanel)this.getContentPane();
		jLabel2.setIcon(new ImageIcon("laptop_1.jpg"));
		Cmd_Logout.setIcon(new ImageIcon("logout.jpg"));
		jTextArea1.setFont(new Font("Palatino Linotype",Font.PLAIN,16));
		Cmd_Logout.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jScrollPane1.setViewportView(jTextArea1);
		jTextArea1.setEnabled(false);
		Cmd_Logout.setText("Log Out");
		Cmd_Logout.setBorderPainted(false); 
		Cmd_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_Logout_actionPerformed(e);
			}

		});
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
							
							new fun().updateStatus("vss1","off");
							System.exit(0);
			
				}
			}
		);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		addComponent(contentPane, jScrollPane1, 75,123,315,195);
		addComponent(contentPane, Cmd_Logout, 275,428,125,35);
		addComponent(contentPane, jLabel2, 0,0,500,500);
		this.setTitle("Volunteer Storage 1 ");
		this.setLocation(new Point(2, 45));
		this.setSize(new Dimension(500,540));
	}

	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}
	
	private void Cmd_Logout_actionPerformed(ActionEvent e)
	{
		
		new fun().updateStatus("vss1","off");
		System.exit(0);
		
	}
	protected void finalize() throws Throwable
	{
	new fun().updateStatus("vss1","off");
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
		file1 v1obj =new file1();


		try
		{
			System.out.println("Socket trying to connect");
			ServerSocket ss = new ServerSocket(7777);
			String fra_name,req,file_name;
			String data="";
			System.out.println("Socket connected");
			while(true)
			{
			
	 		Socket s = ss.accept();
			System.out.println("Connection established with grid server");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			req=dis.readUTF();
			if(req.equalsIgnoreCase("send"))
				{
					System.out.println("Inside the is send block");
					fra_name=dis.readUTF();
					System.out.println("fragement name===="+fra_name);		
			
					data=(String)dis.readUTF();
		            System.out.println("fragement data===="+data);
		
					byte b[]=data.getBytes();
					FileOutputStream fout=new FileOutputStream("vss1\\"+fra_name);
					
					fout.write(b);
					System.out.println(fra_name+"Successfully Saved");
					jTextArea1.append(fra_name+" is successfully stored\n");
					fout.close();
					dis.close();
				}
				else if(req.equalsIgnoreCase("read"))
				{
					System.out.println("in read operation");
					fra_name=dis.readUTF();
					data="";
					data=v1obj.fileToString(fra_name);
					jTextArea1.append("Reading ."+fra_name+"\n");
					System.out.println(fra_name+" data :="+data);
					dos.writeUTF(data);
				}
			}
		}
		catch (Exception er)
		{
			System.out.println("Technical Problem in Socket"+er);
		}
	}


 String fileToString(String filename) throws IOException
	{
	FileInputStream fis=new FileInputStream("vss1\\"+filename);
	byte b[]=new byte[fis.available()];
	fis.read(b);
	String sd=new String(b);
	return sd;
	}


}