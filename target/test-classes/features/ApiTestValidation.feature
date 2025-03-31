
@tag
Feature: validating Place Api's
  I want to use this template for my feature file

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add Place payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"	
    And "scope" in response body is "APP"	 
    And "reference" in response body should be "${reference_variable}" 
    And "id" in response body should be "${id_variable}"
    And "place_id" in response body should be "${placeid_variable}" 
    And validate placeid created matches with "<name>" using "GetPlaceAPI" 
    
    Examples:
    
    | name     | language | address        |
    | Sangeeta | English  | Rochfortbridge |
    | Shubham  | Irish    | Mullingar      |
	
