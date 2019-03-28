
 /****************************************************************/ 
 /*                      ff	                            */ 
 /*                                                              */ 
 /****************************************************************/ 
 import java.awt.*; 
 import java.awt.event.*; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

 import javax.swing.*; 
import javax.swing.event.*; 
 /** 
  * Summary description for ff 
  * 
  */ 
 public class ff extends JFrame 
 { 
 	// Variables declaration 
 	private JList jList1; 
 	private JScrollPane jScrollPane1; 
 	private JButton jButton1; 
 	private JPanel contentPane; 
 	// End of variables declaration 
  
  
 	public ff() 
 	{ 
 		super(); 
 		initializeComponent(); 
 		// 
 		// TODO: Add any constructor code after initializeComponent call 
 		// 
  
 		this.setVisible(true); 
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
 		jList1 = new JList(); 
 		jScrollPane1 = new JScrollPane(); 
 		jButton1 = new JButton(); 
 		contentPane = (JPanel)this.getContentPane(); 
  
 		// 
 		// jList1 
 		// 
 		jList1.addListSelectionListener(new ListSelectionListener() { 
 			public void valueChanged(ListSelectionEvent e) 
 			{ 
 				jList1_valueChanged(e); 
 			} 
  
 		}); 
 		// 
 		// jScrollPane1 
 		// 
 		jScrollPane1.setViewportView(jList1); 
 		// 
 		// jButton1 
 		// 
 		jButton1.setText("jButton1"); 
 		jButton1.addActionListener(new ActionListener() { 
 			public void actionPerformed(ActionEvent e) 
 			{ 
 				try {
					jButton1_actionPerformed(e);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
 			} 
  
 		}); 
 		// 
 		// contentPane 
 		// 
 		contentPane.setLayout(null); 
 		addComponent(contentPane, jScrollPane1, 100,58,100,100); 
 		addComponent(contentPane, jButton1, 123,174,83,28); 
 		// 
 		// ff 
 		// 
 		this.setTitle("ff - extends JFrame"); 
 		this.setLocation(new Point(0, 0)); 
 		this.setSize(new Dimension(390, 300)); 
 	} 
  
 	/** Add Component Without a Layout Manager (Absolute Positioning) */ 
 	private void addComponent(Container container,Component c,int x,int y,int width,int height) 
 	{ 
 		c.setBounds(x,y,width,height); 
 		container.add(c); 
 	} 
  
 	// 
 	// TODO: Add any appropriate code in the following Event Handling Methods 
 	// 
 	private void jList1_valueChanged(ListSelectionEvent e) 
 	{ 
 		System.out.println("\njList1_valueChanged(ListSelectionEvent e) called."); 
 		if(!e.getValueIsAdjusting()) 
 		{ 
 			Object o = jList1.getSelectedValue(); 
 			System.out.println(">>" + ((o==null)? "null" : o.toString()) + " is selected."); 
 			// TODO: Add any handling code here for the particular object being selected 
 			 
 		} 
 	} 
  
 	private void jButton1_actionPerformed(ActionEvent e) throws UnknownHostException, IOException 
 	{ 
 		ArrayList listitem=new ArrayList(20);
 		 Socket s=new Socket("localhost",6543);
		 DataInputStream dis=new DataInputStream(s.getInputStream());
		 DataOutputStream dos=new DataOutputStream(s.getOutputStream());
		 //FileOutputStream fout=new FileOutputStream(file);
		 dos.writeUTF("availfile");
		 dos.writeUTF("a");
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
  
 	// 
 	// TODO: Add any method code to meet your needs in the following area 
 	// 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
   
  
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
 		new ff(); 
 	} 
 //= End of Testing = 
  
  
 } 
  
 