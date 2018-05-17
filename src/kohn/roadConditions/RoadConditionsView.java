package kohn.roadConditions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("serial")
public class RoadConditionsView extends JFrame{

	private JTextField highLatLng;
	private JTextField lowLatLng;
	
	private JTextArea roadConditions;

	
	String coordinatesNE = "41.252000, 286.413574";
	String coordinatesSW = "40.551374, 285.721436";
	Call<RoadConditionsModel>call;
	public RoadConditionsView() {
		setLocation(240, 80);
		setSize(980, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel input = new JPanel(new GridLayout(1,0));
		JPanel output = new JPanel(new GridLayout(0, 1));
		
		highLatLng = new JTextField();
		highLatLng.setText(coordinatesNE);
		lowLatLng = new JTextField();
		lowLatLng.setText(coordinatesSW);
		
		roadConditions = new JTextArea();
		output.add(roadConditions);
		
		input.add(new JLabel("   Current Road Conditions..."));		
		input.setBackground((new Color(3, 118, 38)));		
		output.setBackground(new Color(210, 226, 215));
		Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);		
		input.setBorder(border);
		output.setBorder(border);
		output.setSize(30, 42);
		
		setTitle("NE Coordinates: " + highLatLng.getText()+ " SW Coordinates: " + lowLatLng.getText());
		add(input, BorderLayout.PAGE_START);
		add(output, BorderLayout.CENTER);
		
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
