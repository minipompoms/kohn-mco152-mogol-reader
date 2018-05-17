package kohn.roadConditions;


import java.util.ArrayList;

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
		requestConditions();
	}

	private void requestRoadConditionsFeed(Call<RoadConditionsModel>call, 
			JTextComponent conditions) {
		call.enqueue(new Callback<RoadConditionsModel>() {

			@Override
			public void onResponse(Call<RoadConditionsModel> call, 
					Response<RoadConditionsModel> response) {
				RoadConditionsModel feed = response.body();
				showConditions(conditions, feed);
			}
			@Override
			public void onFailure(Call<RoadConditionsModel> call, Throwable t) {
				t.getMessage();
			}			
		});						
	}
	
	public void showConditions(JTextComponent conditions, RoadConditionsModel feed) {
		ArrayList<String> conditionsFeed = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
			feed.getFeatures()
				.stream()
				.forEach(e -> conditionsFeed.add(e.getProperties().getDetails()));
			int size = conditionsFeed.size()-1;
		for(int i =size; i > size-15; i--) {
			sb.append("\n  ").append(conditionsFeed.get(i)).append("\n");
		}
		conditions.setText(sb.toString());

	}
	
	public void requestConditions() {
		requestRoadConditionsFeed(service.getData(view.getHighLatLng(), 
				view.getLowLatLng()),view.getRoadConditions());
	}
}
