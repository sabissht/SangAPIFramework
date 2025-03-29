package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		//provide base url
				//RestAssured.baseURI ="https://rahulshettyacademy.com";
			// create an object of PrintStream to write the log using FileOutputStream 
		if(req == null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 //create request specification object
				 req = new RequestSpecBuilder().setBaseUri(getGlobalPropertyValue("baseURL")).addQueryParam("key", "qaclick123").
				addFilter(RequestLoggingFilter.logRequestTo(log)).
				addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
				 return req;
	}
		else
		 return req;
	}
	
	public static String getGlobalPropertyValue(String Key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Desktop\\API Test\\APITestFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		 return prop.getProperty(Key);
	}

	public static String getJsonResponse(Response response, String attribute) {
		
		 String resp = response.asString();
		  
		   JsonPath jp = new JsonPath(resp);
		  return jp.get(attribute).toString();
	}
}
