package kohn.roadConditions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class RoadConditionsReader {
	public static void main(String [] args) throws FileNotFoundException {
		Gson gson = new GsonBuilder()
				.setLenient()
				.create();
		
		
			BufferedReader in;
		
				in = new BufferedReader(
						
					new FileReader(new File("src/kohn/roadConditions/roadconditions.geojson")));
					RoadConditionsModel feed = gson.fromJson(in, RoadConditionsModel.class);
					int index = 0;
			 		
			 		while (index < feed.getFeatures().size()) {
			 			String condition =feed.getFeatures().get(index).getProperties().getCondition();
			 			String details = feed.getFeatures().get(index).getProperties().getDetails();
			 			String subCondition = feed.getFeatures().get(index).getProperties().getSubCondition();
			 			String type = feed.getType();
			 			System.out.println("::"+condition+ "->"+type+ "\t"+details);
			 			index++;
			 		}
	
							
							
			
			
			
		
	
	}
}
