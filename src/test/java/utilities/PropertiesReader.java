package utilities;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Used to load the values from "configuration.properties" file.
 */
public class PropertiesReader {


	Properties properties;

	public PropertiesReader() {

		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\configuration.properties");
			properties = new Properties();
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/*
	public String getBrowserType() {

		return properties.getProperty("browser");// Returns the value of brower
	}

	public String getUrl() {

		return properties.getProperty("url");// Returns the URL from properties file.
	}
*/
	public String getValue(String property) {

		return properties.getProperty(property);// Returns the URL from properties file.
	}
}
