package com.example.upd;

import java.util.Map;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etGWid;
	Button OKbtn;
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		etGWid = (EditText)findViewById(R.id.etGWID);
		OKbtn = (Button)findViewById(R.id.OKbtn);
		
		final GetSet obj_getset = new GetSet();
		//MySQLitehelper db = new MySQLitehelper(null);
		
		final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);  
				// Create a shared preferences variable using the SharedPreferenceManager for storing GWids
		
		SharedPreferences.Editor editor = app_preferences.edit();  // We also need a shared preferences editor to handle the shared preference requests
		// Call the edit method (library function) to editor variable can edit the key, values.
		
		editor.putInt("47688507", 47688507);   // put in the shared preferences values of user GWids and give them a key for retrieval purposes
		editor.putInt("1234567", 1234567);
		editor.putInt("7654321", 7654321);
		
		editor.commit();   //commit is necessary to save the shared preferences
		
		
		
		OKbtn.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String gwidCheck = etGWid.getText().toString();  //get the value user enters for GWid
				
				if(app_preferences.contains(gwidCheck))       // this will check the stored shared preferences and compare with the value entered
				{
					Context context = getApplicationContext();
					CharSequence text = "Login Successfull";
					int duration = Toast.LENGTH_SHORT;								//If it exists, then create a toast message for success

					
					
					//etGWid.setText("");    // make the textbox empty
					long setid = Long.parseLong(gwidCheck);   // take the string gwid and convert to long
					obj_getset.setId(setid);    // set the gwid entered
					
					
					
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
					intentfunction(setid);
				} 
				else
				{
					Context context = getApplicationContext();
					CharSequence text = "Login Failed";						// If doesnt exist, create a toast for fail
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}
				
			}
		});
	}

	
	private void intentfunction(long setid)
	{
		 Intent intent = new Intent(this, SelectOptions.class);
      	 //editText = (EditText) findViewById(R.id.editText1);
      	 //editText = new EditText(this);
		 etGWid.setText("");   //set the edit text to blank
      	//String message = "TestHello";
      	
      	
      	intent.putExtra(EXTRA_MESSAGE, setid);
      	startActivity(intent);
      	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
