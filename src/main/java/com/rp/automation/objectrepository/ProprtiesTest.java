package main.java.com.rp.automation.objectrepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class ProprtiesTest {
public static void main(String[] args) throws IOException {
	
	FileInputStream fip = new FileInputStream("D:\\8AMBATCH\\frameworks\\src\\com\\rameshsoft\\automation\\objectrepository\\OR_Gmail.properties");
	Properties properties = new Properties();
	properties.load(fip);
	
	Set keys = properties.keySet();
	for(Object obj : keys)
	{
		String key = (String) obj;
		String value = properties.getProperty(key);
		System.out.println(key+" ************ "+value);
	}
	
	properties.setProperty("hello", "plz do practice");
	properties.setProperty("java", "selenium");
	FileOutputStream fop = new FileOutputStream("D:\\8AMBATCH\\frameworks\\src\\com\\rameshsoft\\automation\\objectrepository\\OR_Gmail.properties");
	
	properties.store(fop, "file is saved succesfully");
	
	
	/*String val1 = (String) properties.get("un_id");
	System.out.println(val1);
	*//*String val1 = properties.getProperty("un_id123");
	String val11 = properties.getProperty("un_id123","value is not there so keep as PRACTICE");
	String val2 = properties.getProperty("un_xpath");
	String val3 = properties.getProperty("next_id");
	String val4 = properties.getProperty("pwd_name");
	String val5 = properties.getProperty("pwd_xpath");
	System.out.println(val1);
	System.out.println(val11);
	System.out.println(val2);
	System.out.println(val3);
	System.out.println(val4);
	System.out.println(val5);
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
