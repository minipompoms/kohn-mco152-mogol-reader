package kohn.roadConditions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MogolService {
	@Headers({ "x-api-key: rsSl0syd6E5vhOHSEnCDc2Wt4sXCUtHT39IAXCAb"})
	@GET("/conditions/region/{highLatLng}/{lowLatLng}?f=coordinates")
	Call<RoadConditionsModel> getData(
			@Path("highLatLng") String highLatLng, 
			@Path("lowLatLng") String lowLatLng);
				
}
