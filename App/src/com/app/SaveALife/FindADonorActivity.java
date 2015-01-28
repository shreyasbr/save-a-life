package com.app.SaveALife;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.example.SaveALife.R;
import com.app.util.DbUtil;

import java.util.List;



public class FindADonorActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findadonor);

		Spinner bloodgroupSpinner = setSpinnerFromArray(
				R.id.bloodgroup_spinner, R.array.bloodgroup_array);

	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.radio_currentlocation:
			if (checked)
				// do nothing
				break;
		case R.id.radio_city:
			if (checked)
				// startCitySelection();
				new StartCitySelectionTask().execute();
			break;
		}
	}

	public Spinner setSpinnerFromArray(int spinnerId, int arrayId) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, arrayId, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		return spinner;
	}

	public Spinner setSpinnerFromDatabase(int spinnerId, String tableName) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		// Specify the layout to use when the list of choices appears

		List<String> list = DbUtil.populateListFromDatabase(tableName);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		return spinner;
	}

	class StartCitySelectionTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Spinner countrySpinner = setSpinnerFromDatabase(
					R.id.country_spinner, "countryTable");
			countrySpinner.setVisibility(View.VISIBLE);

			SpinnerOnItemSelectedListener itemSelected = new SpinnerOnItemSelectedListener();
			countrySpinner.setOnItemSelectedListener(itemSelected);
			int selectedCountryPos = itemSelected.getPos();
			populateCitySpinner(selectedCountryPos);
			return null;
		}

	}

	/*
	 * public void startCitySelection() { Spinner countrySpinner =
	 * setSpinnerFromDatabase(R.id.country_spinner, "countryTable");
	 * countrySpinner.setVisibility(View.VISIBLE);
	 * 
	 * SpinnerOnItemSelectedListener itemSelected = new
	 * SpinnerOnItemSelectedListener();
	 * countrySpinner.setOnItemSelectedListener(itemSelected); int
	 * selectedCountryPos = itemSelected.getPos();
	 * populateCitySpinner(selectedCountryPos);
	 * 
	 * int selectedCountryPos = countrySpinner.getSelectedItemPosition();
	 * Log.i("selected country",
	 * countrySpinner.getItemAtPosition(selectedCountryPos).toString());
	 * populateCitySpinner(selectedCountryPos);
	 * 
	 * }
	 */

	public void populateCitySpinner(int selectedCountryPos) {

		// Spinner citySpinner = setSpinner(R.id.city_spinner,
		// R.array.country_array);
	}
}
