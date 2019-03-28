
 /****************************************************************/ 
 /*                      d	                            */ 
 /*                                                              */ 
 /****************************************************************/ 
 import java.awt.*; 
 import java.awt.event.*; 
 import javax.swing.*; 
 import java.io.*;

 /** 
  * Summary description for d 
  * 
  */ 
 public class d extends JFrame 
 { 
 	// Variables declaration 
 	private JTextArea jTextArea1; 
 	private JScrollPane jScrollPane1; 
 	private JPanel contentPane; 
 	// End of variables declaration 
  
  
 	public d() 
 	{ 
 		super(); 
 		initializeComponent(); 
 		// 
 		// TODO: Add any constructor code after initializeComponent call 
 		// 
  
 		this.setVisible(true); 
 	} 
	public String  view(String file)
	{

	char c[];
		int i=0;
		try
		{
			FileInputStream fis=new FileInputStream(file);
			c=new char[fis.available()];
			while(fis.available()>0)
			{
				c[i]=(char)fis.read();
				i++;	
			}
			jTextArea1.setText(String.valueOf(c));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return "s";
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
 		jTextArea1 = new JTextArea(); 
 		jScrollPane1 = new JScrollPane(); 
 		contentPane = (JPanel)this.getContentPane();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jTextArea1.setEnabled(false);
  
 		// 
 		// jTextArea1 
 		// 
 		// 
 		// jScrollPane1 
 		// 
 		jScrollPane1.setViewportView(jTextArea1); 
 		// 
 		// contentPane 
 		// 
 		contentPane.setLayout(null); 
 		addComponent(contentPane, jScrollPane1, 8,27,363,292); 
 		// 
 		// d 
 		// 
 		this.setTitle("d - extends JFrame"); 
 		this.setLocation(new Point(0, 0)); 
 		this.setSize(new Dimension(390, 388)); 
		this.setResizable(false);
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
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
   
  
 //============================= Testing ================================// 
 //=                                                                    =// 
 //= The following main method is just for testing this class you built.=// 
 //= After testing,you may simply delete it.                            =// 
 //======================================================================// 
 	public static void main(String[] args) 
 	{ 
 		try
		{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		}
		catch (Exception ee)
		{
		}
		
 		//new d().view("D:/SessionPageCount.java"); 
 	} 
 //= End of Testing = 
  
  
 } 
  
 