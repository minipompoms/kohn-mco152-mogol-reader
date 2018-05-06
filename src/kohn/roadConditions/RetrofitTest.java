package kohn.roadConditions;

import java.io.IOException;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTest {
	public static void main(String [] args)throws IOException  {
		String coordinatesNE = "-74.66171,40.95709";
		String coordinatesSW = "-73.29666,40.43650";
		
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

				long getDetails = feed.getFeatures().stream().count();
				
				System.out.println(getDetails);
			}

			@Override
			public void onFailure(Call<RoadConditionsModel> call, Throwable t) {
				t.getMessage();
			}
			
		});
	
	}
}
