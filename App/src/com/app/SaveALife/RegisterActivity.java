package com.app.SaveALife;

import java.util.ArrayList;
import java.util.List;

import com.app.util.JSONParser;
import com.example.SaveALife.R;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Shreyas on 28/01/2015.
 */
public class RegisterActivity extends Activity {
    private ProgressDialog pDialog;

    EditText inputUserName;
    EditText inputFullName;
    EditText inputBloodgroup;
    EditText inputCity;
    EditText inputEmailId;
    EditText inputPhoneNo;

    // url to create new product
    private static String url_create_product = "http://10.0.2.2:8080/BloodDonationDatabaseConnection/insert";

    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(url_create_product);
    HttpResponse response;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    JSONParser jsonParser = new JSONParser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Edit Text
        inputUserName = (EditText) findViewById(R.id.usernameEditText);
        inputFullName = (EditText) findViewById(R.id.fullNameEditText);
        inputBloodgroup = (EditText) findViewById(R.id.bloodgroupEditText);
        inputCity = (EditText) findViewById(R.id.cityEditText);
        inputEmailId = (EditText) findViewById(R.id.emailIdEditText);
        inputPhoneNo = (EditText) findViewById(R.id.phoneNoEditText);

        // Create button
        Button registerButton = (Button) findViewById(R.id.registerButton);

        // button click event
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewUser().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Creating User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating user
         * */
        protected String doInBackground(String... args) {
            String username = inputUserName.getText().toString();
            String fullName = inputFullName.getText().toString();
            String bloodgroup = inputBloodgroup.getText().toString();
            String city = inputCity.getText().toString();
            String emailId = inputCity.getText().toString();
            String phoneNo = inputPhoneNo.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("fullName", fullName));
            params.add(new BasicNameValuePair("bloodgroup", bloodgroup));
            params.add(new BasicNameValuePair("city", city));
            params.add(new BasicNameValuePair("emailId", emailId));
            params.add(new BasicNameValuePair("phoneNo", phoneNo));

            // getting JSON Object
            // Note that create product url accepts POST method
            jsonParser.makeHttpRequest(url_create_product, "POST", params);
            // making POST request
            // httpPost.setEntity(new UrlEncodedFormEntity(params));
            // response=httpClient.execute(httpPost);
            // check log cat for response
            // Log.d("Create Response", json.toString());

            // check for success tag
            // try {
            // int success = json.getInt(TAG_SUCCESS);
            //
            // if (success == 1) {
            // // successfully created product
            // Intent i = new Intent(getApplicationContext(),
            // AllUsersActivity.class);
            // startActivity(i);
            //
            // // closing this screen
            // finish();
            // } else {
            // // failed to create product
            // }
            // } catch (JSONException e) {
            // e.printStackTrace();
            // }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }

}
