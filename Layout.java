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

class Layout implements ActionListener
{
	JFrame f;
	JFileChooser c;
	JTextField tf;
	// String[] fileNames;
	JButton b;
	JLabel jl;
	ArrayList<String> fileNames=new ArrayList<String>();
	Layout()
	{
		f=new JFrame();
		tf=new JTextField();
		tf.setBounds(40,100,200,30);
		b=new JButton("OK");
		b.setBounds(60,200,100,40);
		b.addActionListener(this);
		c=new JFileChooser();
		c.setDialogTitle("choose file");
		// f.add(c);
		// f.add(tf);
		f.add(b);
		f.setSize(600,600);
		f.setLayout(null);
		f.setVisible(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setMultiSelectionEnabled(true);//
		int x=c.showOpenDialog(null);
		if(x==JFileChooser.APPROVE_OPTION)
		{
			File[] files=c.getSelectedFiles();
			// String path=f.getPath();
			// StringBuilder sb=new StringBuilder();
			for(int i=0;i<files.length;i++)
			{
				// sb.append(f[i].getAbsolutePath());
				fileNames.add(files[i].getAbsolutePath());
			}

			for(int i=0;i<files.length;i++)
			{
				// System.out.println(fileNames.get(i));
				System.out.println(files[i]);
			}
			// for(int i=0;i<fileNames.size();i++)
			// {
			// 	System.out.println(fileNames.get(i));
			// }
			// prompt();//calling to enter the zip file name

			try{
			// FileOutputStream fos=new FileOutputStream("zip.zip");
				System.out.println("inside try");
                jl=new JLabel();
                jl.setBounds(40,20,400,30);
		        jl.setFont(new Font("Arial", Font.PLAIN, 20));
        jl.setOpaque(true);
		jl.setText("enter zip file name");
		f.add(jl);
					f.add(tf);
		 f.setVisible(true);

		 }catch(Exception e)
		 {
            e.printStackTrace();
		 }
		}
	}
		 public void actionPerformed(ActionEvent e)
		 {
              // jl.setOpaque(true);
				// tf.setText("zi.zip");
		 	try{
			FileOutputStream fos=new FileOutputStream(tf.getText());
            System.out.println("after fos");
			ZipOutputStream zos=new ZipOutputStream(fos);
			for(int i=0;i<fileNames.size();i++)
			{
				FileInputStream fis=new FileInputStream(new File(fileNames.get(i)));
				System.out.println("after fis");
                 ZipEntry zipEntry=new ZipEntry(fileNames.get(i));
                 System.out.println("after zipEntry");
                 add(fileNames.get(i),zos);
                 
			}
			zos.close();
			fos.close();
			System.exit(0);
		}catch(Exception ae)
		{
			ae.printStackTrace();
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
	
	
}