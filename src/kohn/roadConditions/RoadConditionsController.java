package kohn.roadConditions;

import java.util.Optional;

import javax.swing.text.JTextComponent;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoadConditionsController {

	private RoadConditionsView view;
	private MogolService service;
	
	
	
	public RoadConditionsController(RoadConditionsView view, MogolService service) {
		this.view = view;
		this.service = service;
	}
	
	public void refreshData() {
		
	}
	
	private void requestRoadConditionsFeed(Call<RoadConditionsFeed>call, 
			JTextComponent conditions) {
		call.enqueue(new Callback<RoadConditionsFeed>() {

			@Override
			public void onResponse(Call<RoadConditionsFeed> call, Response<RoadConditionsFeed> response) {
				RoadConditionsFeed feed = response.body();
				Optional<RoadConditions> roadConditions = feed.getFeatures()
						.stream()
						.findAny();
				
				RoadConditionsProperties properties =  roadConditions.get().getProperties();
				String details = String.valueOf(properties.getLocation() + "\t" + properties.getCondition()
												+ "\n" + properties.getDetails()+ properties.getSubCondition());
				conditions.setText(details);
			}

			@Override
			public void onFailure(Call<RoadConditionsFeed> call, Throwable t) {
				t.getMessage();
			}
			
		});
						
	}
	public void requestConditions() {
		requestRoadConditionsFeed(service.getData(view.getHighLatLng(), 
				view.getLowLatLng()),view.getRoadConditions());
	}
}
