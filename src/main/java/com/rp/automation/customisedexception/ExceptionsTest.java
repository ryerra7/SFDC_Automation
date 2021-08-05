package main.java.com.rp.automation.customisedexception;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionsTest {
public static void main(String[] args) throws IOException {
	try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(""))))
	{
		
	}
	
	/*BufferedWriter bw = null;
	FileWriter fw = null;
	File file = null;
	try
	{
	file = new File("D:\\8AMBATCH\\frameworks\\config.properties");
	file.createNewFile();
	fw = new FileWriter(file);
	bw = new BufferedWriter(fw);
	}
	catch(Exception e) {
		System.out.println("Catch block");
	}
	finally
	{
		System.out.println("Finally block");
		bw.close();
	}*/	
	
	
	/*System.out.println("1");
	System.out.println("2");
	try
	{
	  FileInputStream fip = new FileInputStream("D:\\8AMBATCH\\frameworks\\config.properties");
	  try{
		  System.out.println("");
	  }
	  catch(Exception e1) {
		  
	  }
	}
	catch(FileNotFoundException ex)
	{
		System.out.println("FNFE");
		try {
			
		}
		catch(Exception e2) {
			
		}
	}
	catch(Exception e)
	{
		System.out.println("Catch block");
		//e.printStackTrace();
		//System.out.println(e.toString());
		//System.out.println(e);//e.toString()
	}
	System.out.println("3");
	System.out.println("4");*/
	
}
}
