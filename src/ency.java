import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
//import java.util.Vector;
public class ency 
{

String s;
File f; 
FileInputStream fin=null;
FileOutputStream fout =null;
RandomAccessFile keydesc=null;
Random ra=new Random();
//BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
MathCalc mc = new MathCalc();
DESalgorithm DES = new DESalgorithm();
int index=0;
int i=0, cnt =0,a=0,keyelem=0,found=0,len;
String s2 ="", s3="",fstr="",kstr="",c="", keyarr,inpkey="",tarfile="";
String keystr="";
char x;
int wr;
String key1="";
byte[] by1;
int[] eachblk =new int[8];
int inparr[] = new int[8];
int []datablock=new int[64];
int []keyblock=new int[64];
int []encdecblock = new int[64];
Vector res= new Vector(5);

 //-----encription------------------------------------
  public Vector test(String sfile,String tfile)throws Exception
      {
		for(int h=0;h<64;h++)
		{
		     datablock[h]=0;
       	}
       	for(i=0;i<8;i++)
		{
	           inparr[i]=0;
       	}       
	      fstr=sfile;
		  tarfile=tfile;
       	  fin= new FileInputStream(fstr);
		  inpkey=rnd();
		  res.clear();
		  res.add(inpkey);
          len=inpkey.length();
 	      if(len!=8) 
			   System.out.println("Checking the key value");
               //JOptionPane.showMessageDialog(this,"Encryption  Key > 8 char ");
  		for(int j=0;j<8;j++)
		{
		        x = inpkey.charAt(j);
		        s2=mc.convertToBinary((int)x);
		        s3=s3.concat(s2);
    		}
	        keyblock= mc.convertToInt(s3);
	        s3="";
	        s2="";     
       
       tfile="CloudServer\\Enc"+tfile;
	   res.add(tfile);
	   //fstr="Encription.txt";
	   System.out.println("Destination file: "+tfile);
       fout = new FileOutputStream(tfile);
      i=0;
     do{
           inparr[i] = fin.read();
           cnt++;
           i++;
          
           s3="";
           if(cnt==8){
               for(int j=0;j<8;j++)
		{
                   s2 = mc.convertToBinary(inparr[j]); 
	           s3 = s3.concat(s2);
               }
               datablock= mc.convertToInt(s3);
               i=0;
               cnt=0;
               encdecblock=DES.enc_dec(keyblock,datablock,'e');
               for(int ocnt=0,w=0;((ocnt<8)&&(w<64));ocnt++)
				{
                     for(int msb=0;msb<8;msb++,w++)
						{
                         eachblk[msb]=encdecblock[w];
						}
					wr=mc.convertBinToInt(eachblk,8); 
	                fout.write(wr);
                }
        } 
     
     } while(inparr[0]!=-1);
      
        fin.close();
        fout.close();
		return res;
 }
//-----------------------------------------------------------

 //----------------generate Random String--------------------
 String rnd()
	{
		String key="",key1="";
		int ran;
		for(int i=0;i<10;i++)
		{
		ran=(int)ra.nextInt(9);
		key=String.valueOf(ran);
		key1=key1+key;
		}
		System.out.println("Random String="+key1);
		return key1;
	}
//-----------------------------------------

//-------copy the file------------------
void copy(int a,int b,byte byt[])
 {
	for(int i=a,j=0;i<b;i++,j++)
		 {
			byt[j]=by1[i];
          }
 }
//-----------------------------------------

//-------spilt the file----------------------
Vector spilit(String file)
		{
	
		 FileInputStream fis;
		 OutputStream f0,f1,f2,f3,f4;
         String st1="";
		 String frage1,frage2,frage3,frage4;
	     int s1=0,s2=0,s3=0,s4=0,length=0; 
		 

		 frage1="fr1"+tarfile;
		 frage2="fr2"+tarfile;
		 frage3="fr3"+tarfile;
		 frage4="fr4"+tarfile;
		 res.clear();
		 res.add(frage1);
		 res.add(frage2);
		 res.add(frage3);
		 res.add(frage4);

		 //        System.out.println("Frage1="+frage1);
           //      System.out.println("tarfile="+tarfile);

	 try
	 {
	  		fis= new FileInputStream(file);
	        length = fis.available();
	        by1=new byte[length];
	        s2=length/2;
	        s3=s2+ s2/2;
	        s4=length;
		    s1=s2/2;
                        
				byte b1[]=new byte[s1];
	            byte b2[]=new byte[s2-s1];
		        byte b3[]=new byte[s3-s2];
			    byte b4[]=new byte[s4-s3];
          
				System.out.println("s1="+s1+"s2="+s2+"s3="+s3+"s4="+s4);
                       
			    fis.read(by1);
				st1=new String(by1);
	            by1=st1.getBytes();
            
		        copy(0,s1,b1);
			    copy(s1,s2,b2);
				copy(s2,s3,b3);
	            copy(s3,s4,b4);
				 
                f0 = new FileOutputStream("CloudServer\\"+frage1);
                f0.write(b1);
                f0.close();


                f1 = new FileOutputStream("CloudServer\\"+frage2);
                f1.write(b2);
                f1.close();

				f2 = new FileOutputStream("CloudServer\\"+frage3);
                f2.write(b3);
                f2.close();

                f4 = new FileOutputStream("CloudServer\\"+frage4);
                f4.write(b4);
                f4.close();
        
             }
                        
              catch(Exception ex)
      			  {
				  ex.printStackTrace();
				  }
                        
              return res;                  
            }
//-----------------------------------------





   }