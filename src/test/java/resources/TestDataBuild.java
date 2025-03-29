package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlacePOJO;
import POJO.Location;

public class TestDataBuild {

	public AddPlacePOJO addPlacePayload(String name, String language, String address) 
	{
		
		// create main POJO class object and call all set methods to set values 
       
		 AddPlacePOJO p = new AddPlacePOJO();
         p.setAccuracy(50);
         p.setAddress(address);
         p.setLanguage(language);
         p.setPhone_number("(+91) 983 893 3937");
         p.setWebsite("http://google.com");
         p.setName(name);
         List<String> myList = new ArrayList<String>();
         myList.add("shoe park");
         myList.add("shop");
         p.setTypes(myList);
         
         //create an object for subclass(nested json)
         //below code creating sub json
         Location l = new Location();
         l.setLat(-38.383494);
         l.setLng(33.427362);
         
         p.setLocation(l);
     return p;
	}
}
