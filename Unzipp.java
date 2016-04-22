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

class Unzipp 
{
	// JFrame f;
	JFileChooser c;
	String folderName;File f;char first;

	Unzipp()
	{
		c=new JFileChooser();
		// c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		c.setDialogTitle("choose file");
		
		int x=c.showOpenDialog(null);
		 // int x=c.showSaveDialog(Unzipp.this);

		if(x==JFileChooser.APPROVE_OPTION)
		{
			f=c.getSelectedFile();
			first=f.toString().charAt(0);
			// System.out.println(f);
			folderName=f.getName();
			System.out.println(f.getName());
		
	
	// public void actionPerformed(ActionEvent ae)
	// {

		try{
			System.out.println("inside action");
			ZipFile zfile=new ZipFile(f);
			
			Enumeration enm=zfile.entries();
			File newDir=new File(String.valueOf(first));
				if(!newDir.exists())
				{
					newDir.mkdir();
				}
			while(enm.hasMoreElements())
			{
				ZipEntry entry=(ZipEntry)enm.nextElement();
				String name=entry.getName();
				String halfName=name.substring(2);
				
				// File file=new File(String.valueOf(first)+":\\"+name);
				File file=new File(newDir+":\\"+halfName);
                System.out.println("name  "+name);
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
}
	public static void main(String s[])
	{
		new Unzipp();
	}
}
