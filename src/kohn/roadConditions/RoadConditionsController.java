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
	
	private void requestRoadConditionsFeed(Call<RoadConditionsModel>call, 
			JTextComponent conditions) {
		call.enqueue(new Callback<RoadConditionsModel>() {

			@Override
			public void onResponse(Call<RoadConditionsModel> call, Response<RoadConditionsModel> response) {
				RoadConditionsModel feed = response.body();
				Optional<RoadConditions> roadConditions = feed.getFeatures()
						.stream()
						.findAny();
				
				RoadConditionsProperties properties =  roadConditions.get().getProperties();
				String details = String.valueOf(properties.getCondition()
												+ "\n" + properties.getDetails()+ properties.getSubCondition());
				System.out.println("conditions"+details);
				conditions.setText(details);
			}

			@Override
			public void onFailure(Call<RoadConditionsModel> call, Throwable t) {
				t.getMessage();
			}
			
		});
						
	}
	public void requestConditions() {
		requestRoadConditionsFeed(service.getData(view.getHighLatLng(), 
				view.getLowLatLng()),view.getRoadConditions());
	}
}
