package com.tylermoses.grexapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	// SQLite variables for database
	public static final String KEY_ROWID = "_id"; // gives us the row id 
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";
	
	// Set up database variables
	private static final String DATABASE_NAME = "HotOrNotDB"; // DB name
	private static final String DATABASE_TABLE = "peopleTable"; // DB table name
	private static final int DATABASE_VERSION = 1; // DB version
	
	// Set up database
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	// SQLiteHelper Class extension
	private static class DbHelper extends SQLiteOpenHelper{
		
		// Constructor
		public DbHelper(Context context){
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			// Execute Sql code to create table
			db.execSQL( "CREATE TABLE " + DATABASE_TABLE + 
			" (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ KEY_NAME + " TEXT NOT NULL, " 
				+ KEY_HOTNESS + " TEXT NOT NULL" +
			" );"		
			); // end Execute SQL
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	} //end DbHelper
	
	// Set ourContext
	public HotOrNot(Context c){
		ourContext = c;
	}
	
	public HotOrNot open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String hername, String herHot) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues(); // a Bundle of values for insert
		cv.put(KEY_NAME, hername); // put data in cv
		cv.put(KEY_HOTNESS, herHot); // put more data in cv 
		return ourDatabase.insert(DATABASE_TABLE, null, cv); // insert into database
	}

	public String getData() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[] {
				KEY_ROWID , KEY_NAME, KEY_HOTNESS
		};
		// start cursor too read database
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		
		// cycle through database
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			// Append results to result
			result = result + c.getString(iRow) + " " + c.getString(iName) +
					" " + c.getString(iHotness) + "\n";
			
		}
		return result;
	}

	public String getNAme(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[] {
				KEY_ROWID , KEY_NAME, KEY_HOTNESS
		};
		// start cursor too read database
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "-" + l, null, null, null, null);
		String result = "";
		if(c !=null ){
			c.moveToFirst();
			result = c.getString(1);
			return result;
		}
		return null;
		
	}

	public String getHotness(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[] {
				KEY_ROWID , KEY_NAME, KEY_HOTNESS
		};
		// start cursor too read database
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "-" + l, null, null, null, null);
		
		String result = "";
		if(c !=null ){
			c.moveToFirst();
			result = c.getString(2);
			return result;
		}
		
		
		return null;
	}

	public void editEntry(long lRow, String hername, String herHot) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, hername);
		cvUpdate.put(KEY_HOTNESS, herHot);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "-" + lRow, null);
		
	}

	public void deleteEntry(long lRow) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "-" + lRow, null);
	}
	
} // end HotOrNot
