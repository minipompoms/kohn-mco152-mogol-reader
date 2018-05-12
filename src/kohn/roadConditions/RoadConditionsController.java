package kohn.roadConditions;

import java.awt.Component;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		
		feed.getFeatures()
				.stream()
				.forEach(e -> System.out.println(e.getProperties().getDetails()+"\n"));
				
		//String deets = String.valueOf(roadConditions);
		
		//conditions.setText(deets);
	}
	
	public void requestConditions() {
		requestRoadConditionsFeed(service.getData(view.getHighLatLng(), 
				view.getLowLatLng()),view.getRoadConditions());
	}
}
