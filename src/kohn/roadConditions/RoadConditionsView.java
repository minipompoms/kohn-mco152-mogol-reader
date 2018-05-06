package kohn.roadConditions;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
public class RoadConditionsView extends JFrame{

	private JTextField highLatLng;
	private JTextField lowLatLng;
	
	private JTextField roadConditions;
	
	String coordinatesNE = "-74.66171,40.95709";
	String coordinatesSW = "-73.29666,40.43650";
	public RoadConditionsView() {
		JTextField highLatLng = new JTextField();
		highLatLng.setText(coordinatesNE);
		JTextField lowLatLng = new JTextField();
		lowLatLng.setText(coordinatesSW);

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
	
	public static void main (String [] args) {
		new RoadConditionsView().setVisible(true);

	}
}
