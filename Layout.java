import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

class Layout
{
	JFrame f;
	JFileChooser c;
	JTextField tf;
	// String[] fileNames;
	JButton b;JLabel jl;
	ArrayList<String> fileNames=new ArrayList<String>();
	Layout()
	{
		f=new JFrame();
		tf=new JTextField();
		tf.setBounds(40,100,200,30);
		b=new JButton("OK");
		b.setBounds(60,200,50,20);
		jl=new JLabel();
		jl.setFont(new Font("Arial", Font.PLAIN, 20));
        jl.setBounds(40,60,400,30);
        jl.setOpaque(true);
		jl.setText("enter zip file name");
		f.add(jl);
		c=new JFileChooser();
		c.setDialogTitle("choose file");
		f.add(c);
		f.add(tf);
		f.add(b);
		f.setSize(600,600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		c.setMultiSelectionEnabled(true);//

		int x=c.showOpenDialog(null);
		if(x==JFileChooser.APPROVE_OPTION)
		{
			File[] f=c.getSelectedFiles();
			// String path=f.getPath();
			// StringBuilder sb=new StringBuilder();
			for(int i=0;i<f.length;i++)
			{
				// sb.append(f[i].getAbsolutePath());
				fileNames.add(f[i].getAbsolutePath());
			}
			for(int i=0;i<fileNames.size();i++)
			{
				System.out.println(fileNames.get(i));
			}
			// prompt();//calling to enter the zip file name

			try{
			// FileOutputStream fos=new FileOutputStream("zip.zip");
			FileOutputStream fos=new FileOutputStream(tf.getText());

			ZipOutputStream zos=new ZipOutputStream(fos);
			for(int i=0;i<fileNames.size();i++)
			{
                 add(fileNames.get(i),zos);
			}
			
			zos.close();
			fos.close();
		}catch(IOException  e)
		{
			e.printStackTrace();
		}
			
		}
	}
	public void add(String fileName,ZipOutputStream zos)throws FileNotFoundException,IOException
	{
   System.out.println("inside addd");
   FileInputStream fis=new FileInputStream(new File(fileName));
   ZipEntry zipEntry=new ZipEntry(fileName);
   zos.putNextEntry(zipEntry);
   byte[] b=new byte[fis.available()];
   fis.read(b);
   zos.write(b);
   zos.closeEntry();
   fis.close();
   }
	public static void main(String s[])
	{
		new Layout();
	}
	public void prompt()
	{

	}
	
}
