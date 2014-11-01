package com.android.personalfinancetoolkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBAdapter
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_COST = "cost";
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "shoppinglist";
    private static final String DATABASE_TABLE = "shopping";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
    	"create table shopping(_id integer primary key autoincrement, "+ "name text not null,cost text not null);";
    
        
    private final Context context; 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db; 
    
    //Constructor of DBAdapter class
    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    
    //SQLiteOpenHelper
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion+ " to "+ newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
    	db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a title into the database---
    public long insertTitle(long id,String name,Double cost) 
    {
        ContentValues initialValues = new ContentValues();
        //initialValues.put(KEY_ROWID,id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_COST,cost);
        
        
        //System.out.println("id:::::"+id);
        System.out.println("name::::::::"+name);
        System.out.println("cost:::::::::::::"+cost);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

   //---updates a title---
    public boolean updateTitle(String name,Double cost) 
    {
        ContentValues args = new ContentValues();
        //args.put(KEY_ROWID, id);
        args.put(KEY_NAME, name);
        args.put(KEY_COST, cost);
        
        //args.put(KEY_CALORIES, calories);
        return db.update(DATABASE_TABLE, args, KEY_NAME,null) > 0;
    }
    
    //-------getting Specific titles from database
    public Cursor getAllTitles() 
    {
       return db.query(DATABASE_TABLE,new String[]{KEY_NAME,KEY_COST,}, null,null, null, null, null);
    }
    
    //----for deleting a particular record
    public int deleteTitle(Long id) 
    {
        int i= db.delete(DATABASE_TABLE, KEY_ROWID +"=" +"'"+id+"'", null);
        System.out.println("I:::::::::::::::::::"+i);
        return i;
    }
    
    //-- for deleting all records in the databse
    public void deleteAll()
    {
    	db.delete(DATABASE_TABLE, null, null);
    }
    
    //Cursor
    public Cursor getId(String where)
    {
    	Cursor c = db.query(DATABASE_TABLE, new String[] {KEY_ROWID},where,null,null,null,null);      
    	if (c != null) c.moveToFirst();    
    	return c;    
    } 
    
    public long getrowid()
    {
    	SQLiteStatement id;
    	id=db.compileStatement("SELECT last_insert_rowid() AS [id];");
    	return (Long)id.simpleQueryForLong();
    }

    public Cursor getlistitems()
    {
    	Cursor mCursor =
             db.query(true, DATABASE_TABLE, new String[] 
             {            		 
            		KEY_ROWID,
             		KEY_NAME,
             		KEY_COST,
             }, 
             null,
             null,
             null, 
             null, 
             null, 
             null);
    	
    		 if (mCursor != null)
    		 {
    			 mCursor.moveToFirst();
    		 }
    		 return mCursor;
    }
}
