package kohn.roadConditions;

public class RoadConditions {

	private String id;
	private RoadConditionsProperties properties;

	public RoadConditions(String condition, 
			String subCondition, String location, String details) {
		properties = new RoadConditionsProperties(condition, subCondition, details);
	}
	public String getID() {
		return id;
	}

	public RoadConditionsProperties getProperties() {
		return properties;
	}

}
