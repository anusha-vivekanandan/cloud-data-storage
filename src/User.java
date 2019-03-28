/****************************************************************/
/*                      User	                            */
/*                                                              */
/****************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;
import java.sql.*;


public class User extends JFrame
{
	// Variables declaration
	private JTabbedPane Tab;
	private JPanel contentPane;
	//----- View
	private JLabel jLabel4;
	private JList jList1;
	private JScrollPane jScrollPane2;
	private JButton Cmd_View;
	private JButton Cmd_read;
	private JPanel View1;
	//-----Secure
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField jTextField1;
	private JTextArea jTextArea1;
	private JScrollPane jScrollPane1;
	private JButton jButton1,cmd_access;
	private JButton Cmd_Send;
	private JButton Cmd_Clear;
	private JPanel Secure,control,update,delete;
	

	//Acess permission
	private JCheckBox cb_read; 
 	private JCheckBox cb_write; 
 	private JCheckBox cb_del;
 	private JList jList1_acc;
	private JScrollPane jScrollPane3;
	
 	private JList jList1_user;
	private JScrollPane jScrollPane4;
	private JLabel jLabel5;
	
	
	
	// Delete
	private JLabel jLabel7;
	private JList jList1_del;
	private JScrollPane jScrollPane7;
	private JButton Cmd_Delete;

	//Write
 	private JList jList1_file;
	private JScrollPane jScrollPane5;
	private JLabel jLabel6,jLabel8;
	private JTextField jTxt_file;
	private JButton Cmd_write,cmd_browse;
	
	
	
	//-----
	File f;
	String path,str,user="",fstr="",file,file_acc,user_acc,file_write;
	String servers;
	Socket s;
	FileDialog fd;
	ResultSet rs;
	Connection con;
	Statement st;
	String data;
    String file_data="";
	ArrayList listitem=new ArrayList(20);
	boolean r,w,d=false;
	String prof;
	// End of variables declaration

	public User()
	{
		super();
		
		user=Userlogin.user;
		user=user.trim();
		initializeComponent();
		try
	      {	
			FileInputStream fis=new FileInputStream("server\\cloudserver.txt");
			byte b1[]=new byte[fis.available()];
			fis.read(b1);
			servers=new String (b1);
			System.out.println(" Cloud server System  name="+servers);
			
			Cmd_read.setEnabled(false);
			 jLabel1.setEnabled(false);
		  }
		catch (Exception ex)
			{
			System.out.println(ex);
			}
		this.setVisible(true);
		this.setResizable(false);

	}

	private void initializeComponent()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		prof=Userlogin.profile;
		Tab = new JTabbedPane();
		contentPane = (JPanel)this.getContentPane();
		//-----
		jLabel4 = new JLabel();
		jList1 = new JList();
		jScrollPane2 = new JScrollPane();
		Cmd_View = new JButton();
		Cmd_read = new JButton();
		View1 = new JPanel();
		//-----
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jTextField1 = new JTextField();
		jTextArea1 = new JTextArea();
		jScrollPane1 = new JScrollPane();
		jButton1 = new JButton();
		Cmd_Send = new JButton();
		Cmd_Clear = new JButton();
		Secure = new JPanel();
		//-----
		
		
		//Control
		
		
		 jLabel5  = new JLabel();
		 cb_write= new JCheckBox();
		 cb_read= new JCheckBox();
		 cb_del = new JCheckBox();
		 jList1_acc = new JList();
	     jScrollPane3= new JScrollPane();
	     jList1_user = new JList();
	     jScrollPane4= new JScrollPane();
	     cmd_access= new JButton();
		
		
		 jLabel7  = new JLabel();
		 jList1_del = new JList();
	     jScrollPane7= new JScrollPane();
		 Cmd_Delete= new JButton();
		 
		 //------
		
	     
	   
	     //----
	     
	     jScrollPane5= new JScrollPane();
	     Cmd_write= new JButton();
	     cmd_browse= new JButton();
	     jList1_file = new JList();
	     jLabel6  = new JLabel();
	     jLabel8  = new JLabel();
	     jTxt_file=new JTextField();
	     
		control = new JPanel();
		update = new JPanel();
		delete = new JPanel();
	
		

		//
		// Tab
		//
	
		
		Tab.addTab("Read ", View1);
		Tab.addTab("Write", update);
		Tab.addTab("Delete ", delete);
		
		Tab.setSelectedIndex(0);
		if(prof.equals("Owner"))
		{
			Tab.addTab("File to Secure", Secure);
			Tab.addTab("File Permission", control);
			jLabel2.setText("Owner");
		}
		else
			jLabel2.setText("User");
		
		//Tab.setTabPlacement(JTabbedPane.LEFT);
		Tab.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		Secure.setBackground(new Color(179, 226, 228));
		View1.setBackground(new Color(179, 226, 228));
		Tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e)
			{
				Tab_stateChanged(e);
			}

		});
		
		contentPane.setLayout(null);
		addComponent(contentPane, Tab, 2,71,600,520);
		jLabel4.setText("Available Files");
		jLabel4.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jList1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				jList1_valueChanged(e);
			}

		});

		jScrollPane2.setViewportView(jList1);

		Cmd_View.setText("View");
		Cmd_View.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		Cmd_read.setText("Read File");
		Cmd_read.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jList1.setFont(new Font("Palatino Linotype",Font.PLAIN,16));
		Cmd_View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_View_actionPerformed(e);
			}

		});
	Cmd_read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_read_actionPerformed(e);
			}

		});

		View1.setLayout(null);
		addComponent(View1, jLabel4, 16,120,150,39);
		addComponent(View1, jScrollPane2, 175,65,150,160);
		addComponent(View1, Cmd_View, 157,251,93,43);
		addComponent(View1, Cmd_read, 250,251,123,43);
		//Acess
		
		jLabel5.setText("Available Files");
		cb_write.setText("Write");
		cb_read.setText("Read");
		cb_del.setText("Delete");
		cmd_access.setText("Submit");
		jScrollPane3.setViewportView(jList1_acc);
		jScrollPane4.setViewportView(jList1_user);
		jList1_acc.setFont(new Font("Palatino Linotype",Font.PLAIN,16));
		jList1_user.setFont(new Font("Palatino Linotype",Font.PLAIN,16));
		control.setLayout(null);
		addComponent(control, jLabel5, 16,120,150,39);
		addComponent(control, jScrollPane3, 175,65,150,160);
		addComponent(control, jScrollPane4, 375,65,150,160);
		addComponent(control, cb_read, 80,230,150,39);
		addComponent(control, cb_write, 80,290,150,39);
		addComponent(control, cb_del, 80,350,150,39);
			
		addComponent(control, cmd_access, 250,300,150,39);
		
		jList1_acc.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				
				System.out.println("\njList1_acc_valueChanged(ListSelectionEvent e) called.");
				if(!e.getValueIsAdjusting())
				{
					Object o = jList1_acc.getSelectedValue();
					System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
					file_acc=o.toString();
					System.out.println("Select File name for access is="+file_acc);
					
				}
			}

		});
		jList1_user.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				
				System.out.println("\njList1_user_valueChanged(ListSelectionEvent e) called.");
				if(!e.getValueIsAdjusting())
				{
					Object o = jList1_user.getSelectedValue();
					System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
					user_acc=o.toString();
					System.out.println("Select File name for access is="+user_acc);
					
				}
				
			}

		});
		
		 
 		cb_read.addItemListener(new ItemListener() { 
 			public void itemStateChanged(ItemEvent e) 
 			{ 
 				cb_read_itemStateChanged(e); 
 			} 
  
 		}); 
		
 		cb_write.addItemListener(new ItemListener() { 
 			public void itemStateChanged(ItemEvent e) 
 			{ 
 				cb_write_itemStateChanged(e); 
 			} 
  
 		}); 
 		cb_del.addItemListener(new ItemListener() { 
 			public void itemStateChanged(ItemEvent e) 
 			{ 
 				cb_del_itemStateChanged(e); 
 			} 
  
 		}); 
 		
 		cmd_access.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				try {
					System.out.println(user_acc + file_acc + r + w + d);
					
					String req=file_acc+"#"+user_acc+"#"+r+"#"+w+"#"+d;
					if(r||w||d)
						{
					 s=new Socket(servers,6543);
					 DataInputStream dis=new DataInputStream(s.getInputStream());
					 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
					 //FileOutputStream fout=new FileOutputStream(file);
					 dos.writeUTF("insertaccess");
					 dos.writeUTF(req);
					 String res=dis.readUTF();	
					 JOptionPane.showMessageDialog(null,"Access permission granted","Access",JOptionPane.INFORMATION_MESSAGE);
					 dis.close();
					 dos.close();
					 s.close();
					 }
					else
					{
						JOptionPane.showMessageDialog(null,"Please select any one access permission and then proced","Access",JOptionPane.INFORMATION_MESSAGE);
					}
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
				
				
			}

		});
		
 		
 		//Write
 		jLabel6.setText("Available Files");
 		jLabel8.setText("New  File");
		Cmd_write.setText("Write");
		cmd_browse.setText("Browse");
	
		update.setLayout(null);
 		
 		addComponent(update, jLabel6, 16,120,150,39);
		addComponent(update, jScrollPane5, 175,65,150,160);
		addComponent(update, jLabel8, 16,230,150,39);
		addComponent(update, jTxt_file,  175,230,150,39);
		addComponent(update, cmd_browse, 80,380,150,39);
		addComponent(update, Cmd_write, 280,380,150,39);
		jScrollPane5.setViewportView(jList1_file);
	
		
		
		
		jList1_file.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				
				System.out.println("\njjList1_file_valueChanged(ListSelectionEvent e) called.");
				if(!e.getValueIsAdjusting())
				{
					Object o = jList1_file.getSelectedValue();
					System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
					file_write=o.toString();
					System.out.println("Select File name for access is="+file_write);
					
				}
			}

		});
		cmd_browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				cmd_browse_actionPerformed(e);
			}

		});
		Cmd_write.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				cmd_write_actionPerformed(e);
				
			}

		});

		
		// Delete 
		
		jLabel7.setText("Available Files");
		jLabel7.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jList1_del.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
			

				System.out.println("\njList1_del_valueChanged(ListSelectionEvent e) called.");
				if(!e.getValueIsAdjusting())
				{
					Object o = jList1_del.getSelectedValue();
					System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
					file=o.toString();
					System.out.println("Select File name is="+file);
					
				}
			}

		});

		jScrollPane7.setViewportView(jList1_del);

		Cmd_Delete.setText("Delete");
		Cmd_Delete.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		
		delete.setLayout(null);
		addComponent(delete, jLabel7, 16,120,150,39);
		addComponent(delete, jScrollPane7, 175,65,150,160);
		addComponent(delete, Cmd_Delete, 157,251,93,43);
				
		Cmd_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				 try {
					s=new Socket(servers,6543);
					 DataInputStream dis=new DataInputStream(s.getInputStream());
					 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
					 //FileOutputStream fout=new FileOutputStream(file);
					 dos.writeUTF("delfile");
					 dos.writeUTF(file);
					 dos.writeUTF(user);
					 dis.close();
					 dos.close();
					 s.close();
					 
					 listitem.remove(file);
					 jList1_del.setListData(listitem.toArray());	
				}  catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

		});
		
		
		
		
		jLabel1.setEnabled(true);
		
		jLabel2.setFont(new Font("Monotype Corsiva",Font.ITALIC,50));
		jLabel2.setForeground(new Color(149, 17, 109));


		jLabel3.setText("Select the File");
		jLabel3.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jButton1.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		Cmd_Send.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		Cmd_Clear.setFont(new Font("Palatino Linotype",Font.PLAIN,18));
		jTextField1.setFont(new Font("Palatino Linotype",Font.PLAIN,16));

		jScrollPane1.setViewportView(jTextArea1);
		jTextArea1.setEnabled(false);
		jButton1.setText("Browse");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});

		Cmd_Send.setText("Save");
		Cmd_Send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_Send_actionPerformed(e);
			}

		});

		Cmd_Clear.setText("Clear");
		Cmd_Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Cmd_Clear_actionPerformed(e);
			}

		});

		Secure.setLayout(null);
		contentPane.setBackground(new Color(179, 226, 228));
		
		addComponent(contentPane, jLabel2, 130,15,250,35);
		addComponent(Secure, jLabel3, 16,35,130,32);
		addComponent(Secure, jTextField1, 149,35,228,31);
		addComponent(Secure, jScrollPane1, 145,85,247,145);
		addComponent(Secure, jButton1, 388,32,90,40);
		addComponent(Secure, Cmd_Send, 141,260,93,43);
		addComponent(Secure, Cmd_Clear, 244,260,93,43);
		addComponent(contentPane, jLabel1, -100,450,471,52);

		
		this.setTitle(user+" FileSender ");
		this.setLocation(new Point(0, 0));
		this.setSize(new Dimension(700, 640));
	setResizable(false);

	}


	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	private void cb_read_itemStateChanged(ItemEvent e) 
 	{ 
 		System.out.println("\ncb_read_itemStateChanged(ItemEvent e) called."); 
 		System.out.println(">>" + ((e.getStateChange() == ItemEvent.SELECTED) ? "selected":"unselected"));
 		
		if ((e.getStateChange() == ItemEvent.SELECTED))
			r = true;
		else
			r = false;
		// TODO: Add any handling code here 
 		 
 	} 
  
 	private void cb_write_itemStateChanged(ItemEvent e) 
 	{ 
 		System.out.println("\ncb_write_itemStateChanged(ItemEvent e) called."); 
 		System.out.println(">>" + ((e.getStateChange() == ItemEvent.SELECTED) ? "selected":"unselected")); 
 		if ((e.getStateChange() == ItemEvent.SELECTED))
			w = true;
		else
			w = false;
 		 
 	} 
  
 	private void cb_del_itemStateChanged(ItemEvent e) 
 	{ 
 		System.out.println("\ncb_del_itemStateChanged(ItemEvent e) called."); 
 		System.out.println(">>" + ((e.getStateChange() == ItemEvent.SELECTED) ? "selected":"unselected")); 
 		if ((e.getStateChange() == ItemEvent.SELECTED))
			d = true;
		else
			d = false;
 		 
 	} 
	private void Tab_stateChanged(ChangeEvent e)
	{
		System.out.println("\nTab_stateChanged(ChangeEvent e) called.");
		System.out.println("index="+Tab.getSelectedIndex());
		try
		{
		if(Tab.getSelectedIndex()==0)
		{
			System.out.println("in view tab");
			
			listitem.clear();
			
			
			 s=new Socket(servers,6543);
			 DataInputStream dis=new DataInputStream(s.getInputStream());
			 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			 //FileOutputStream fout=new FileOutputStream(file);
			 dos.writeUTF("availfile");
			 dos.writeUTF(user);
			 String avil_file=dis.readUTF();	
			 String avail[]=avil_file.split("#");
			 dis.close();
			 dos.close();
			 s.close();
			if(null !=avail && avail.length!=0)
			{
				
				
				for (String st: avail)
				{
					listitem.add(st);	
				}
				jList1.setListData(listitem.toArray());
			}
			if(listitem.isEmpty())
				JOptionPane.showMessageDialog(null,"no Secure file to View","File Not Found",JOptionPane.INFORMATION_MESSAGE);
			 else
				jList1.setListData(listitem.toArray());
			
		}
		
		else if(Tab.getSelectedIndex()==4)
		{
			System.out.println("in Access tab");
			
			listitem.clear();
			DataInputStream dis;;
			DataOutputStream dos;
			
			 s=new Socket(servers,6543);
			  dis=new DataInputStream(s.getInputStream());
			  dos=new DataOutputStream(s.getOutputStream());
			 //FileOutputStream fout=new FileOutputStream(file);
			 dos.writeUTF("availfile");
			 dos.writeUTF(user);
			 String avil_file=dis.readUTF();	
			 String avail[]=avil_file.split("#");
			 dis.close();
			 dos.close();
			 s.close();
			if(null !=avail && avail.length!=0)
			{
				
				for (String st: avail)
				{
					listitem.add(st);	
				}
				jList1_acc.setListData(listitem.toArray());
			}
			else
			{
				JOptionPane.showMessageDialog(null,"no Secure file to View","File Not Found",JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			
			
			
			 s=new Socket(servers,6543);
			 dis=new DataInputStream(s.getInputStream());
			  dos=new DataOutputStream(s.getOutputStream());
			
			 dos.writeUTF("availuser");
			  avil_file=dis.readUTF();	
			 String avail_user[]=avil_file.split("#");
			 dis.close();
			 dos.close();
			 s.close();
			 
			 if(null !=avail_user && avail_user.length!=0)
				{
				 jList1_user.setListData(avail_user);
				}
			 else
				{
					JOptionPane.showMessageDialog(null,"No User","File Not Found",JOptionPane.INFORMATION_MESSAGE);
					
				}
				
		}
		
		if(Tab.getSelectedIndex()==1)
		{
			System.out.println("in write tab");
			
			listitem.clear();
			
			
			 s=new Socket(servers,6543);
			 DataInputStream dis=new DataInputStream(s.getInputStream());
			 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			 //FileOutputStream fout=new FileOutputStream(file);
			 dos.writeUTF("availfilewrite");
			 dos.writeUTF(user);
			 String avil_file=dis.readUTF();	
			 String avail[]=avil_file.split("#");
			 dis.close();
			 dos.close();
			 s.close();
			if(null !=avail && avail.length!=0)
			{
				
				
				for (String st: avail)
				{
					listitem.add(st);	
				}
				//jList1.setListData(listitem.toArray());
			}
			if(listitem.isEmpty())
				JOptionPane.showMessageDialog(null,"no Secure file to View","File Not Found",JOptionPane.INFORMATION_MESSAGE);
			 else
				 jList1_file.setListData(listitem.toArray());
			
				
		}
		if(Tab.getSelectedIndex()==2)
		{
			System.out.println("in delte tab");
			
			listitem.clear();
			
			
			 s=new Socket(servers,6543);
			 DataInputStream dis=new DataInputStream(s.getInputStream());
			 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			 //FileOutputStream fout=new FileOutputStream(file);
			 dos.writeUTF("availfiledel");
			 dos.writeUTF(user);
			 String avil_file=dis.readUTF();	
			 String avail[]=avil_file.split("#");
			 dis.close();
			 dos.close();
			 s.close();
			if(null !=avail && avail.length!=0)
			{
				
				
				for (String st: avail)
				{
					listitem.add(st);	
				}
				//jList1.setListData(listitem.toArray());
			}
			if(listitem.isEmpty())
				JOptionPane.showMessageDialog(null,"no Secure file to View","File Not Found",JOptionPane.INFORMATION_MESSAGE);
			 else
				 jList1_del.setListData(listitem.toArray());
			
				
		}
		
		
		}
		catch(Exception ed){
			System.out.println(ed);
		}
		
	}
	private void jList1_valueChanged(ListSelectionEvent e)
	{
		System.out.println("\njList1_valueChanged(ListSelectionEvent e) called.");
		if(!e.getValueIsAdjusting())
		{
			Object o = jList1.getSelectedValue();
			System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
			file=o.toString();
			System.out.println("Select File name is="+file);
			
		}
	}

	private void Cmd_View_actionPerformed(ActionEvent e)
	{
		String res;
		if (jList1.getSelectedIndex()>=0 )
		 {
		   try
			 {
			   	 
				 jLabel1.setEnabled(true);		
				// Thread.sleep(2000);
				 
				 s=new Socket(servers,6543);
				 DataInputStream dis=new DataInputStream(s.getInputStream());
				 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
				 FileOutputStream fout=new FileOutputStream(file);
				 dos.writeUTF("Viewfile");
				 dos.writeUTF(file);
				 dos.writeUTF(user);
				 System.out.println("File name="+file);
				 data="";
				 res=dis.readUTF();
				 if(res.equals("data"))
				 {
				 data=dis.readUTF();
		    	 System.out.println("The decripted data is"+data);
				 byte b[]=data.getBytes();
				 fout.write(b);
				 Thread.sleep(1000);
				 jLabel1.setEnabled(false);
				 Cmd_read.setEnabled(true);
				 }
				 else
	 				JOptionPane.showMessageDialog(User.this,"No full source available to view the file ","Fragment not found",JOptionPane.INFORMATION_MESSAGE);
				 dis.close();
				 dos.close();
				 s.close();
				 view v=new view("CloudServer\\"+file);
		     }
			catch(Exception ex)
			{
				System.out.println("Error ::"+e);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Select the file name from the List","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		System.out.println("\nCmd_View_actionPerformed(ActionEvent e) called.");


	}
	private void Cmd_read_actionPerformed(ActionEvent e)
	{
		
		new d().view("CloudServer\\"+file);
		Cmd_read.setEnabled(false);
		


	}
	
	
	private void cmd_write_actionPerformed(ActionEvent e)
	{
		if(file.equalsIgnoreCase(file_write))
		{
			try {
				s=new Socket(servers,6543);
				 DataInputStream dis1=new DataInputStream(s.getInputStream());
				 DataOutputStream dos1=new DataOutputStream(s.getOutputStream());
				 dos1.writeUTF("Sendfile");
				 dos1.writeUTF(user);
				 dos1.writeUTF(file);
				 str=jTextArea1.getText();
				 System.out.println("in send button file path="+path);
				 dos1.writeUTF(file_data);
				 dos1.writeUTF("update");
				 String ack=dis1.readUTF();
				 if(ack.equalsIgnoreCase("ACK"))
					{
					jLabel1.setEnabled(false);
					JOptionPane.showMessageDialog(this,"Ur File scueesfully secured","Acknowledment",JOptionPane.INFORMATION_MESSAGE);
					}
				 
				 dis1.close();
				 dos1.close();
				 s.close();
			}  catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Select the same file name to upload","File Not Found",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	private void cmd_browse_actionPerformed(ActionEvent e)
	{
		
	   fd=new FileDialog(this,"select file",FileDialog.LOAD);
	   fd.setFile("*.txt");
	   fd.setVisible(true);
       String dir=fd.getDirectory();
       file=fd.getFile();
	   path=(dir+file);
	   System.out.println("selected file path="+ path);
	  
	  if (!path.equals("nullnull")|| path==null)
	  {
		jTxt_file.setText(path);
	    try
		{
			FileInputStream fin=new FileInputStream(path);
			byte b[]=new byte[fin.available()];
			fin.read(b);
			file_data=new String(b);
			//jTextArea1.setText(file_data);
			fin.close();
		 }
	    catch (IOException io)
		{
		   System.out.println("Exception in reading the input  file"+io);
		}
	  }
	}

		
	  private void jButton1_actionPerformed(ActionEvent e)
		{
			
		   fd=new FileDialog(this,"select file",FileDialog.LOAD);
		   fd.setFile("*.txt");
		   fd.setVisible(true);
	       String dir=fd.getDirectory();
	       file=fd.getFile();
		   path=(dir+file);
		   System.out.println("selected file path="+ path);
		  
		  if (!path.equals("nullnull")|| path==null)
		  {
			jTextField1.setText(path);
		    try
			{
				FileInputStream fin=new FileInputStream(path);
				byte b[]=new byte[fin.available()];
				fin.read(b);
				file_data=new String(b);
				jTextArea1.setText(file_data);
				fin.close();
			 }
		    catch (IOException io)
			{
			   System.out.println("Exception in reading the input  file"+io);
			}
		}



	}

	private void Cmd_Send_actionPerformed(ActionEvent e)
	{
		System.out.println("path="+path);
	if (path.equals("nullnull")|| path==null||path.equals("null"))
	
	//if(jTextField1.getText().length()>0)
		{	
			JOptionPane.showMessageDialog(this,"Select the file","Error",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
	 	try
		  {		
			jLabel1.setEnabled(true);
			System.out.println(" Inside try");
			
			 s=new Socket(servers,6543);
			 DataInputStream dis=new DataInputStream(s.getInputStream());
			 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
			 dos.writeUTF("isavailable");
			 dos.writeUTF(file);
			 String avail=dis.readUTF();
			 dis.close();
			 dos.close();
			 s.close();
			if(avail.equalsIgnoreCase("true"))
			{
			JOptionPane.showMessageDialog(null,"The file name is already present in the secure list","Error",JOptionPane.INFORMATION_MESSAGE);
			 jTextField1.setText("");
			}
			else
			{
			 s=new Socket(servers,6543);
			 DataInputStream dis1=new DataInputStream(s.getInputStream());
			 DataOutputStream dos1=new DataOutputStream(s.getOutputStream());
			 dos1.writeUTF("Sendfile");
			 dos1.writeUTF(user);
			 dos1.writeUTF(file);
			 str=jTextArea1.getText();
			 System.out.println("in send button file path="+path);
			 dos1.writeUTF(file_data);
			 dos1.writeUTF("create");
			 String ack=dis1.readUTF();
			 if(ack.equalsIgnoreCase("ACK"))
				{
				jLabel1.setEnabled(false);
	 			JOptionPane.showMessageDialog(this,"Ur File scueesfully secured","Acknowledment",JOptionPane.INFORMATION_MESSAGE);
				}
			 dis1.close();
			 dos1.close();
			 s.close();
			}

		}
		catch (Exception ie)
		{
			System.out.println("PRoblem in send the file"+ie.getMessage());
		}	
		
		}
	}

	private void Cmd_Clear_actionPerformed(ActionEvent e)
	{
		jTextArea1.setText("");
		jTextField1.setText("");

	}


	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new User();
	}
}
