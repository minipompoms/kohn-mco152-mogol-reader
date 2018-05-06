package kohn.roadConditions;

import java.util.List;

public class RoadConditionsModel {
	
	private String type;
	private List<RoadConditions>features;

	public RoadConditionsModel(String type, List<RoadConditions>features) {
		this.features = features;
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public List<RoadConditions> getFeatures(){
		return features;
	}
	
}
