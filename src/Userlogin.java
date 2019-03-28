

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 

public class Userlogin extends JFrame
{
	
	private JLabel jLabel1;
	private JLabel jLabel2,jl,j2;
	private JLabel jLabel5;
	public static JTextField jTextField1;
	private JPasswordField jPasswordField1;
	private JComboBox jComboBox1;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel contentPane;
	public static String s1,s2;
	String cloudserver;
	public static String user;

	public static String profile;

	public Userlogin()
	{
		super();
		
		initializeComponent();
		this.setVisible(true);
		this.setResizable(false);
	
	}

	
	private void initializeComponent()
	{
		
		String prof[]={"Select","TPA","Owner","user"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jl=new JLabel();
		j2=new JLabel();
		jLabel5 = new JLabel();
		jTextField1 = new JTextField();
		jPasswordField1 = new JPasswordField();
		jComboBox1 = new JComboBox(prof);
		jButton1 = new JButton();
		jButton2 = new JButton();


		jButton1.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jButton2.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jLabel1.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		j2.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jLabel2.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		jTextField1.setFont(new Font("Comic Sans MS",Font.PLAIN,18));
		
		contentPane = (JPanel)this.getContentPane();

		jLabel1.setText("User Name");
		
		jLabel2.setText("Password");
		j2.setText("Profile");
		
		jl.setText("Sign In");
		jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,50));
		jl.setForeground(new Color(49, 37, 229));
		
	
		jButton1.setText("Sign In");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

			try
				{
					s1=jTextField1.getText().trim();
					s2=jPasswordField1.getText().trim();
					user=s1;
					System.out.println(s1.length()+"\t"+s2.length());
					if( s1.length()!=0 && s2.length()!=0 &&!profile.equals("Select")&& profile !=null)
			        {
					System.out.println("Username="+s1);
					System.out.println("password="+s2);
					FileInputStream fis=new FileInputStream("server\\cloudserver.txt");
					byte b[]=new byte[fis.available()];
					fis.read(b);
					cloudserver=new String (b);
					Socket s = new Socket(cloudserver, 6543);
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF("login");
					dos.writeUTF(s1);
					dos.writeUTF(s2);
					dos.writeUTF(profile);
					DataInputStream dis2 = new DataInputStream(s.getInputStream());
					String res = dis2.readUTF();
					System.out.println("Response: "+res);
					if(res.equalsIgnoreCase("Success"))
						{	
							String ccd=" login Success";
							JOptionPane.showMessageDialog(Userlogin.this,ccd , "Login...",JOptionPane.INFORMATION_MESSAGE);
							dispose();
							System.out.println("Transforing control to the FILE SENDER form");
							if(profile.equals("TPA"))
							{
							new TPA();
							}
							else
							new User();
							
						}
					else
						{
			
							String ccd="Invalid Username or Password ....";
							jTextField1.setText("");
							jPasswordField1.setText("");
							JOptionPane.showMessageDialog(Userlogin.this,ccd , "Login...",JOptionPane.INFORMATION_MESSAGE);
						}
					dos.close();
					dis2.close();
					s.close();

					}	
					else
					JOptionPane.showMessageDialog(Userlogin.this,"Enter the correct data in all fields","Login",JOptionPane.INFORMATION_MESSAGE);
					
			        }
				catch (Exception ex)
				{
					System.out.println("Error:"+ex);
				}
 			} 
			
			
		});
		
		jButton2.setText("Sign Up");
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
					new SignUP();
			}

		});
		jComboBox1.addActionListener(new ActionListener() { 
 			public void actionPerformed(ActionEvent e) 
 			{ 
 				jComboBox1_actionPerformed(e); 
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
	
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255)); 
		addComponent(contentPane, jLabel1, 41,150,113,39); 
 		addComponent(contentPane, jLabel2, 41,190,114,39); 
 		addComponent(contentPane, j2, 41,230,110,35);
 		addComponent(contentPane, jl,100,15,225,75); 
 		addComponent(contentPane, jLabel5, 50,30,310,87); 
 		addComponent(contentPane, jTextField1, 190,150,121,25); 
 		addComponent(contentPane, jPasswordField1, 190,190,122,27); 
 		addComponent(contentPane, jComboBox1, 190,230,110,35);
		addComponent(contentPane, jButton1, 103,300,103,48);
		addComponent(contentPane, jButton2, 206,300,103,48);
		this.setTitle("User Login");
		this.setLocation(new Point(275,175));
		this.setSize(new Dimension(404, 407));
	}

	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}
	
	private void jButton1_actionPerformed(ActionEvent e)
	{
		System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
	}

	private void jButton2_actionPerformed(ActionEvent e)
	{
		System.out.println("\njButton2_actionPerformed(ActionEvent e) called.");
	}
	private void jComboBox1_actionPerformed(ActionEvent e) 
 	{ 
 		System.out.println("\njComboBox1_actionPerformed(ActionEvent e) called."); 
 		 
 		Object o = jComboBox1.getSelectedItem(); 
 		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected."); 
 		// TODO: Add any handling code here for the particular object being selected 
 		 profile=o.toString();
 	}

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		new Userlogin();
	}



}
