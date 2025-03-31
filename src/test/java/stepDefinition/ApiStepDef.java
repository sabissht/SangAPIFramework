package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlacePOJO;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class ApiStepDef extends Utils{
	
 // declare all request spec and response spec variables as global
	// RequestSpecification req;
	RequestSpecification reqS;
	ResponseSpecification resSpec;
	Response response;
	String placeid;
	TestDataBuild data  = new TestDataBuild();
	 
	// import all packages related to Given when and Then
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
             
		 reqS =	given().log().all().spec(requestSpecification()).
				        body(data.addPlacePayload(name, language, address));
	    
	}
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
	    // Write code here that turns the phrase above into concrete actions
		// create response spec object
		APIResources apiresource = APIResources.valueOf(resource);
		System.out.println(apiresource.getResource());
		 resSpec=	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(httpmethod.equalsIgnoreCase("POST"))
			 response = reqS.when().post(apiresource.getResource());	
		 else if(httpmethod.equalsIgnoreCase("GET"))
			 response = reqS.when().get(apiresource.getResource());                
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer status) {
	    // Write code here that turns the phrase above into concrete actions
		//response = response.then().spec(resSpec).extract().response();
	    assertEquals(response.getStatusCode(),400);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
	   // automatically at runtime status will replace KeyValue and get its value and compare it with expected value (this 2nd args value will come from scenario step )
	 //  assertEquals(jPath.get(KeyValue).toString(),expectedValue);
		
		assertEquals(getJsonResponse(response,KeyValue),expectedValue);
	}

	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String KeyValue, String dynamicValue) {
	    // Write code here that turns the phrase above into concrete actions
		placeid = getJsonResponse(response,KeyValue);
		   dynamicValue = this.placeid ;
		   System.out.println("Value of:" + KeyValue + "-" + dynamicValue);
		   
	}
	
	@Then("validate placeid created matches with {string} using {string}")
	public void validate_placeid_created_matches_with_using(String expectedfield, String resource) throws IOException {
	    
		//create request spec
		
		reqS =	given().spec(requestSpecification()).queryParam("place_id", placeid);
		user_calls_with_http_request(resource, "GET");
		String actualfieldvalue = getJsonResponse(response,"name");
		assertEquals(actualfieldvalue,expectedfield);
	}
}
