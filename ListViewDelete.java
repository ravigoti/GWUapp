package com.example.upd;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewDelete extends ListActivity {
	
	
	//String []list =  {"A","B","C","D"};
	 private ArrayList<String> thelist;
	 
			
	
	 MySQLitehelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_list_view_delete);
		dbhelper  = new MySQLitehelper(this);
		final Intent intent = getIntent();
		final Bundle extras = getIntent().getExtras();    //gets the GWID
		
		//ArrayAdapter ad =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,extras.getStringArrayList(SelectOptions.EXTRA_MESSAGE));


		
		thelist = new ArrayList<String>(extras.getStringArrayList(SelectOptions.EXTRA_MESSAGE));
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,extras.getStringArrayList(SelectOptions.EXTRA_MESSAGE)));
	}		

	public void onListItemClick(ListView parent, View view, int position, long id)
	{
		Toast.makeText(this, "You have selected "+thelist.get(position)+" and will be deleted", Toast.LENGTH_LONG).show();
		
		
		try {
		    dbhelper.open();
		    } catch (SQLException sqle) 
		    {
		     Log.e("TAG", "Never ignore exception!!! " + sqle);
		    } 
		dbhelper.DeleteRecord(thelist.get(position));
		thelist.remove(position);
		
		//ad.notifyDataSetChanged();
		
	}
	
	
}
