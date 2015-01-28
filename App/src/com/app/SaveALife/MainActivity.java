package com.app.SaveALife;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.example.SaveALife.R;

/**
 * Created by Shreyas on 28/01/2015.
 */
public class MainActivity extends Activity {
    Button goToRegisterButton;
    Button findADonorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToRegisterButton = (Button) findViewById(R.id.registerButton);
        findADonorButton = (Button) findViewById(R.id.findadonorButton);

        goToRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);

            }
        });

        findADonorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(),
                        FindADonorActivity.class);
                startActivity(i);

            }
        });
    }

	/*
	 * public void goToRegister(View view) { Intent i = new Intent(this,
	 * RegisterActivity.class); startActivity(i); }
	 */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
