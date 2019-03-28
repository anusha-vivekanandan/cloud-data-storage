/****************************************************************/
/*                      Design	                            */
/*                                                              */
/****************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class SignUP extends JFrame
{
	// Variables declaration
	private JLabel jLabel1,jLabel3;
	private JLabel jLabel2,jl,j2;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField1,jTextField2;
	private JPasswordField jPasswordField1;
	private JComboBox jComboBox1;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel contentPane;
	String cloudserver;
	String user,pass1,profile,email;
	// End of variables declaration


	public SignUP()
	{
		super();
		initializeComponent();
		this.setVisible(true);
		this.setResizable(false);
	}

	
	private void initializeComponent()
	{
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		String prof[]={"Select","Owner","user"};
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jComboBox1 = new JComboBox(prof);
		jl=new JLabel();
		j2=new JLabel();
		jLabel4 = new JLabel();
		jTextField1 = new JTextField();
		jPasswordField1 = new JPasswordField();
		jTextField2 = new JTextField();
		jButton1 = new JButton();
		jButton2 = new JButton();
		contentPane = (JPanel)this.getContentPane();
		
		jLabel1.setText("User Name");
		jLabel2.setText("Password");
		jLabel3.setText("E-mail");
		j2.setText("Profile");
		jl.setText("Sign UP");
		jl.setFont(new Font("Monotype Corsiva",Font.ITALIC,50));
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setForeground(new Color(149, 17, 109));
		
		
		jButton1.setFont(new Font("Bookman Old Style",Font.PLAIN,20));
		jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
		jButton1.setText("SET");

		jButton2.setFont(new Font("Bookman Old Style",Font.PLAIN,20));
		jButton2.setHorizontalTextPosition(SwingConstants.CENTER);
		jButton2.setText("RESET");
		
		jLabel1.setFont(new Font("Bookman Old Style",Font.PLAIN,20));       
		jLabel2.setFont(new Font("Bookman Old Style",Font.PLAIN,20));
		jLabel3.setFont(new Font("Bookman Old Style",Font.PLAIN,20));
		j2.setFont(new Font("Bookman Old Style",Font.PLAIN,20));
		jTextField1.setFont(new Font("Bookman Old Style",Font.PLAIN,18));
		jTextField2.setFont(new Font("Bookman Old Style",Font.PLAIN,18));
		jPasswordField1.setFont(new Font("Bookman Old Style",Font.PLAIN,18));
		jComboBox1.addActionListener(new ActionListener() { 
 			public void actionPerformed(ActionEvent e) 
 			{ 
 				jComboBox1_actionPerformed(e); 
 			} 
  
 		}); 
		
		
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});
		
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}

		});
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
							dispose();
				
				}
			}
		);
		
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(179, 226, 228)); 
		addComponent(contentPane, jLabel1, 50,132,150,35);
		addComponent(contentPane, jLabel2, 50,174,110,35);
		addComponent(contentPane, jLabel3, 50,216,70,35);
		addComponent(contentPane, j2, 50,270,110,35);
		addComponent(contentPane, jl,100,15,225,75);
		addComponent(contentPane, jLabel4, 50,21,275,90);
		addComponent(contentPane, jTextField1, 200,137,111,35);
		addComponent(contentPane, jPasswordField1, 200,176,109,35);
		addComponent(contentPane, jTextField2, 200,215,107,35);
		addComponent(contentPane, jComboBox1, 200,270,110,35);
		addComponent(contentPane, jButton1, 95,340,97,48);
		addComponent(contentPane, jButton2, 198,340,103,48);
		
		
		this.setTitle("New User Sign Up");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(400, 500));
	setResizable(false);
	}

	
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}


	private void jButton1_actionPerformed(ActionEvent e)
	{
		
			user=jTextField1.getText().trim();
			pass1=jPasswordField1.getText().trim();
			email=jTextField2.getText().trim();
			
	
				if( user.length()!=0 && pass1.length()!=0 &&!profile.equals("Select")&& profile !=null)
				{	
						
					System.out.println("Username="+user);
					System.out.println("password="+pass1);
					System.out.println("password="+email);
					try
					{
					FileInputStream fis=new FileInputStream("server\\cloudserver.txt");
					byte b[]=new byte[fis.available()];
					fis.read(b);
					cloudserver=new String (b);
					
					Socket s = new Socket(cloudserver, 6543);
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					
					dos.writeUTF("signup");
					dos.writeUTF(user);
					dos.writeUTF(pass1);
					dos.writeUTF(profile);
					dos.writeUTF(email);
			
					DataInputStream dis2 = new DataInputStream(s.getInputStream());
					String res = dis2.readUTF();
					System.out.println("Response: "+res);

					if(res.equalsIgnoreCase("success"))
					{	
						JOptionPane.showMessageDialog(this,"New User Successfully Registered","Thanks",JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}	
					else
					{
						JOptionPane.showMessageDialog(this,"Already Username Exist","Warning...",JOptionPane.INFORMATION_MESSAGE);
						jTextField1.setText("");
						jPasswordField1.setText("");
						jTextField2.setText("");
						jTextField1.setFocusable(true);
					}
					dos.close();
					dis2.close();
					s.close();
				}
				catch (Exception ex)
				{
				}
				}
				else
					JOptionPane.showMessageDialog(SignUP.this,"Enter the correct data in all  fields","Login",JOptionPane.INFORMATION_MESSAGE);
	
		
	}
	private void jComboBox1_actionPerformed(ActionEvent e) 
 	{ 
 		System.out.println("\njComboBox1_actionPerformed(ActionEvent e) called."); 
 		 
 		Object o = jComboBox1.getSelectedItem(); 
 		System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected."); 
 		// TODO: Add any handling code here for the particular object being selected 
 		 profile=o.toString();
 	} 
  

	private void jButton2_actionPerformed(ActionEvent e)
	{
			jTextField1.setText("");
			jTextField2.setText("");
			jPasswordField1.setText("");
			jTextField1.setFocusable(true);
		System.out.println("\njButton2_actionPerformed(ActionEvent e) called.");
		// TODO: Add any handling code here

	}

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		
		new SignUP();
	}

}
