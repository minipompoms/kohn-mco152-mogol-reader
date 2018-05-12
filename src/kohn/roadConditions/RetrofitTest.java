package kohn.roadConditions;

import java.io.IOException;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest {
	public static void main(String [] args)throws IOException  {
		String coordinatesNE = "41.252000, 286.413574";
		String coordinatesSW = "40.551374, 285.721436";
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.mogolinc.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		MogolService service = retrofit.create(MogolService.class);
		
		Call<RoadConditionsModel>call = service.getData(coordinatesNE, coordinatesSW);
		call.enqueue(new Callback<RoadConditionsModel>() {

			@Override
			public void onResponse(Call<RoadConditionsModel> call, Response<RoadConditionsModel> response) {
				RoadConditionsModel feed = response.body();
				int noEvents = feed.getFeatures().size();
				String condition = feed.getFeatures().get(600).getProperties().getCondition();
				String getDetails = feed.getFeatures().get(600).getProperties().getDetails();
				//System.out.println(noEvents);

				//System.out.println(condition);
				//System.out.println(getDetails);
			}

			@Override
			public void onFailure(Call<RoadConditionsModel> call, Throwable t) {
				t.getMessage();
			}
			
		});
	
	}
}
