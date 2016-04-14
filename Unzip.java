import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Unzip implements ActionListener
{
	// JFrame f;
	JFileChooser c;
	String folderName;File f;char first;
	// JTextField tf;
	// String[] fileNames;
	// JButton b;
	// JLabel jl;
	Unzip()
	{
		// f=new JFrame();
		// tf=new JTextField();
		// tf.setBounds(40,100,200,30);
		// b=new JButton("OK");
		// b.setBounds(60,200,100,40);
		// b.addActionListener(this);
		c=new JFileChooser();
		c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		c.setDialogTitle("choose file");
		// f.add(c);
		// f.add(tf);
		// f.add(b);
		// f.setSize(600,600);
		// f.setLayout(null);
		// f.setVisible(true);
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// c.setMultiSelectionEnabled(true);//
		int x=c.showOpenDialog(null);
		if(x==JFileChooser.APPROVE_OPTION)
		{
			f=c.getSelectedFile();
			first=f.toString().charAt(0);
			folderName=f.getName();
			System.out.println(f.getName());
		}
	}
	public void actionPerformed(ActionEvent ae)
	{

		try{
			ZipFile zfile=new ZipFile(f);
			// ZipFile zfile=new ZipFile("st.zip");
			Enumeration enm=zfile.entries();
			while(enm.hasMoreElements())
			{
				ZipEntry entry=(ZipEntry)enm.nextElement();
				String name=entry.getName();
				System.out.println("name  "+name);
				File file=new File(first+":\\"+name);
				InputStream is=zfile.getInputStream(entry);
                FileOutputStream fos=new FileOutputStream(file);
                int length=0;
                while((length=is.read())!=-1)
                {
                	fos.write(length);
                }
                is.close();
                fos.close();
			}
			zfile.close();
			}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String s[])
	{
		new Unzip();
	}
}