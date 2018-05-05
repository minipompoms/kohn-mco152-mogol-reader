package kohn.roadConditions;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoadConditionsView {

	private JTextField highLatLng;
	private JTextField lowLatLng;
	
	private JTextField roadConditions;
	
	public RoadConditionsView() {
		

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.mogolinc.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		MogolService service = retrofit.create(MogolService.class);
		
		RoadConditionsController controller = new RoadConditionsController(this, service);
		controller.refreshData();
	}
	
	public String getHighLatLng() {
		return highLatLng.getText();
	}
	
	public String getLowLatLng() {
		return lowLatLng.getText();
	}
	
	public JTextComponent getRoadConditions() {
		return roadConditions;
	}
}
