package com.company.project.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;

	protected String serviceUrl;
	protected String apiPathAuthenticate;
	protected String urlAuthenticate;
	protected String apiPathUser;
	protected String urlUser;

	protected Properties prop = null;

	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeSuite()
	public void setup() throws FileNotFoundException {

		serviceUrl = prop.getProperty("BASE_URL");

		apiPathAuthenticate = prop.getProperty("serviceURL_create");
		urlAuthenticate = serviceUrl + apiPathAuthenticate;

		apiPathUser = prop.getProperty("serviceURL_join");
		urlUser = serviceUrl + apiPathUser;

		String log4Jpath = System.getProperty("user.dir") + "/log4j.properties";
		PropertyConfigurator.configure(log4Jpath);

	}

}
