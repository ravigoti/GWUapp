package com.example.upd;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;


public class SelectOptions extends Activity {

	protected static final String EXTRA_MESSAGE = "This is a the GWID";
	Button btnView, btnDrop, btnLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_options);
		
		
		final Intent intent = getIntent();
		//String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		//long getid = intent.getLongExtra(MainActivity.EXTRA_MESSAGE, defaultValue)
				final Bundle extras = getIntent().getExtras(); 

	       if (extras != null) {
	           long getid = extras.getLong(MainActivity.EXTRA_MESSAGE,0);

	        }
		
		btnView = (Button)findViewById(R.id.btnViewShift);
		btnDrop = (Button)findViewById(R.id.btnDropShift);
		btnLocation = (Button)findViewById(R.id.btnViewLocation);
		
		 
		final GetSet gwid = new GetSet();
	/*	 ArrayList<String> results = new ArrayList<String>();
	        SQLiteDatabase sampleDB = null;
	        final MySQLitehelper db = null;
	        
	        final Dialog diag = null;
	        final TextView txt = null;   */
		final MySQLitehelper helper = new MySQLitehelper(this);
		
		
		
		btnView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				helper.open();
				Cursor c = helper.getRecord(extras.getLong(MainActivity.EXTRA_MESSAGE));
			//	List<String> list = new ArrayList<String>();
			 //    Cursor cursor = helper.query(constantValues.TABLE_NAME, new String[] { "emailid"},null, null, null, null, null); // here emailid is the field name in the table and contantValues.TABLE_NAME is the table name 
			    if (c.moveToFirst()) {
			     do {
			        
			    	 System.out.println("In Do while");
			    	 DisplayRecord(c); 

			     } while (c.moveToNext());
			  }
			    helper.close();	
			    System.out.println("Out of Do");			    
					
			}  
		});
		
		
		btnLocation.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent2 = new Intent(SelectOptions.this, MapViewActivity.class);
			    //startActivity(intent2);
				startActivity(intent2);
				
				
			}
		});
		
		btnDrop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				helper.open();
				Cursor c = helper.getRecord(extras.getLong(MainActivity.EXTRA_MESSAGE));
				List<String> list = new ArrayList<String>();
			 //    Cursor cursor = helper.query(constantValues.TABLE_NAME, new String[] { "emailid"},null, null, null, null, null); // here emailid is the field name in the table and contantValues.TABLE_NAME is the table name 
			    if (c.moveToFirst()) {
			     do {
			        
			    	 System.out.println("In Do while");
			    	 //DisplayRecord(c); 
			    	 list.add(c.getString(2));

			     } while (c.moveToNext());
			  }
			    helper.close();	
			    System.out.println("Out of Do");			    
					
				Intent intent3 = new Intent(SelectOptions.this, ListViewDelete.class);
			    //startActivity(intent2);
				intent3.putStringArrayListExtra(EXTRA_MESSAGE, (ArrayList<String>) list);
				startActivity(intent3);
				
			}
		});
	}
	
	


	public void DisplayRecord(Cursor c)
	{
		System.out.println("In side toast display function");
		Toast.makeText(this, "id: "+c.getString(0)+"\n"+
				"Date: "+c.getString(1)+"\n"+
				"Location: "+c.getString(2)+"\n"+
				"Time: "+c.getString(3), Toast.LENGTH_LONG).show();
	}
	

}
