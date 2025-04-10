package SwagLabs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
	static Properties prop=new Properties();
	static String projectpath=System.getProperty("user.dir");

	public static void getprop() {
		try {
			InputStream input=new FileInputStream(projectpath+"/src/test/resources/Config/Config.properties");
			prop.load(input);
			String browsername=prop.getProperty("browser");
			Swag_Login.browsername=browsername;
			

		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}

	}
	public static void main(String[] args) {
		getprop();
	}
}
