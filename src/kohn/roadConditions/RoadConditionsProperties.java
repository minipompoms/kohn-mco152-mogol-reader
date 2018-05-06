package kohn.roadConditions;

	
public class RoadConditionsProperties {
	
	private String condition;
	private String subCondition;
	
	private String details;
	
	public RoadConditionsProperties(String condition, 
			String subCondition, String details) {
		this.condition = condition;
		this.subCondition = subCondition;
		this.details= details;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public String getDetails() {
		return details;
	}
	
	public String getSubCondition() {
		return subCondition;
	}
}
