
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
public class view extends JFrame 
{
	//Button b,b1;
	private JScrollPane jScrollPane1;
	TextArea t1;
	private JPanel contentPane;
	public view(String file)
	{
		
		
		super("View Files");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,400);
		//setResizable(false);
		setLayout(null);
		contentPane = (JPanel)this.getContentPane();
		t1=new TextArea();
		/*b=new Button("BrowseFile");
		add(b);
		b1=new Button("Exit");

		add(b1);*/
		jScrollPane1 = new JScrollPane();
		t1.setEnabled(false);
		//t1=new TextArea(20,50);
		jScrollPane1.setViewportView(t1);
		
		addComponent(contentPane, jScrollPane1, 20,50,306,180);
		
		/*b.addActionListener(this);
		b1.addActionListener(this);*/
		setVisible(true);
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
			t1.setText(String.valueOf(c));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}
	/*public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{*/
//		FileDialog f=new FileDialog(this,"select File",FileDialog.LOAD);
//		f.setVisible(true);
//		String dir=f.getDirectory();
//		String file=f.getFile();
//		char c[];
//		int i=0;
//		try
//		{
//			FileInputStream fis=new FileInputStream(dir+file);
//			c=new char[fis.available()];
//			while(fis.available()>0)
//			{
//				c[i]=(char)fis.read();
//				i++;	
//			}
//			t1.setText(String.valueOf(c));
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
//		}
//		else
			//dispose();
	//}
	//}
	public static void main(String arg[])
	{
		try
		{
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		}
		catch (Exception ee)
		{
		}
		new view("D:/SessionPageCount.java");
	}
}