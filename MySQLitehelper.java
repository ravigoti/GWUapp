package com.example.upd;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLitehelper {

	//public static final String TABLE_COMMENTS = "comments";
	  public static final String COLUMN_ID = "GWid";
	  public static final String COLUMN_DATE = "DateGWU";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_TIME = "time";
	  
	  public static final String TABLE_NAME = "UPDTable";

	  private static final String DATABASE_NAME = "UPDdb_version6";
	  private static final int DATABASE_VERSION = 6;
	  
	  private final Context context;
	  GetSet getset = new GetSet();
	  public void GetIdForGwid(GetSet get)
	  {
		 getset=get; 
	  }
	 
	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +
			  						" (" +COLUMN_ID+ " integer," + COLUMN_DATE + " VARCHAR," +
			  						COLUMN_LOCATION+" VARCHAR," +COLUMN_TIME +" VARCHAR);";
	  
	//  private static final String DATABASE_INSERT = "INSERT INTO " +TABLE_NAME +
	//		  										" Values (47688507,'DEC-07-2012','MARVIN 203','20:00');";
	  
	  private static final String DATABASE_INSERT = "INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +
              COLUMN_LOCATION+" ," +COLUMN_TIME +" )"  +
                              " Values (47688507,'DEC-07-2012','MARVIN 203','20:00');";
	  
	  
	 
	  DatabaseHelper dbhelper;
	  SQLiteDatabase db;

	  
	  
	  
	 public MySQLitehelper(Context ctx)
	  {
		  this.context = ctx;
		  dbhelper = new DatabaseHelper(ctx);
	  }
	 
	

	private static class DatabaseHelper extends SQLiteOpenHelper {
		 
		 public DatabaseHelper(Context context)
		 {
			 super(context,DATABASE_NAME, null,DATABASE_VERSION);
		 }
	 
	 @Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);			//execute create table
		db.execSQL(DATABASE_INSERT);			//execute insert query
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (47688507,'DEC-22-2012','THURSTON HALL','23:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (1234567,'DEC-14-2012','FUNGER','12:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (7654321,'DEC-29-2012','GELMAN','22:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (47688507,'DEC-26-2012','IVORY','23:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (47688507,'DEC-9-2012','MPA','11:30');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (1234567,'DEC-14-2012','DUQUES','8:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (7654321,'DEC-29-2012','TOMPKINS','9:00');");
		db.execSQL("INSERT INTO " +TABLE_NAME +  " (" +COLUMN_ID+ " ," + COLUMN_DATE + "," +COLUMN_LOCATION+" ," +COLUMN_TIME +" )" +" Values (47688507,'DEC-10-2012','HIMMELFARB LIBRARY','2:00');");

	 
	 }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLitehelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		    onCreate(db);
		}
	}
	
	
	// open the DB
	 public MySQLitehelper open() throws SQLException
	 {
		System.out.println("Inside open function");
		 db = dbhelper.getWritableDatabase();
		return this;
	 }
	 
	 public void close()
	 {
		 dbhelper.close();
	 }
	 
	
	 
	 public void insertRecord(long gwid, String date, String location, String time)
		 {
			   ContentValues initialValues = new ContentValues();
			  initialValues.put(COLUMN_ID, gwid);
			  initialValues.put(COLUMN_DATE, date);
			  initialValues.put(COLUMN_LOCATION, location);
			  initialValues.put(COLUMN_TIME, time);
			  db.insert(TABLE_NAME, null, initialValues);
		 }
	 
	public Cursor getAllrows()      //function to get all rows in the DB. Testing initially.
	{
		
		 Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
		 return cur;
	}
	
	public Cursor getRecord(long getid) throws SQLException
	{
			Cursor mCursor =
			db.query(true, TABLE_NAME, new String[] {COLUMN_ID,
			COLUMN_DATE, COLUMN_LOCATION, COLUMN_TIME},
			COLUMN_ID + "= "+getid, null, null, null, null, null);  
			if (mCursor != null) 
			{
				mCursor.moveToFirst();
			}
	 return mCursor;
 }
	
	public void DeleteRecord (String location)
	{
	    try {
	        //String sSQLQuery = "DELETE FROM "+TABLE_NAME +
	         //   "WHERE "+COLUMN_LOCATION+"='" + location + "';";
	        //db.execSQL(sSQLQuery);
	    	//db.dele
	    	//this.db.delete(
	    		//	  TABLE_NAME,
	    			//  COLUMN_LOCATION+" = "+location,null);
	    	
	    	this.db.delete(TABLE_NAME, COLUMN_LOCATION+" = ?",new String[] {location});
	        String Message = "Record is deleted: ";
	    } catch (SQLiteException ex) {
	        
	    }
	}
	
}
