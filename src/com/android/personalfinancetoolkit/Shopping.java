package com.android.personalfinancetoolkit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Shopping extends Activity {
	AutoCompleteTextView item;
	TextView tv1,tv2;
	EditText amount;
	DBAdapter db;
	Double totalamount = 0.0;
	Double totala=0.0;
	ListView l1;
	String itemn;
	ShoppingAdapter notes;
	ArrayList<Constructor> shopList = new ArrayList<Constructor>();

	public void onCreate(Bundle SavedInstanceState)
	{
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.shopping);
		db = new DBAdapter(this);
		item = (AutoCompleteTextView) findViewById(R.id.entry1);
		String[] itemnames = getResources().getStringArray(R.array.items);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_dropdown_item_1line, itemnames);
		item.setAdapter(adapter);
		final Button additem = (Button) findViewById(R.id.ok);
		
		final Button clearlist = (Button) findViewById(R.id.clearlist);
		tv1 = (TextView) findViewById(R.id.label1);
		tv2 = (TextView) findViewById(R.id.r);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
		tv2.setTypeface(typeface);
		tv1.setText(Double.toString(totalamount));
		tv2.setText("`");
		amount=(EditText)findViewById(R.id.entry);
		l1 = (ListView) findViewById(R.id.l1);
		notes = new ShoppingAdapter(this, R.layout.shoplist, shopList);
		l1.setAdapter(notes);
		clearlist.setOnClickListener(new View.OnClickListener()
		{
				@Override
				public void onClick(View v)
				{
					AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(Shopping.this);
    		    	 //Read Update    
    		    	 alertDialog1.setTitle("Alert Message");    
    		    	 alertDialog1.setMessage("Are You Sure You Want To Delete All The List Items?");      
    		    	 alertDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    		    	 {          
    		    		 public void onClick(DialogInterface dialog, int which)
    		    		 {        
    		    			 db.open();
							db.deleteAll();
							shopList.clear();
							Toast.makeText(getApplicationContext(), "All Items Are Deleted From The List",Toast.LENGTH_SHORT).show();
							notes.notifyDataSetChanged();
							//db.deleteTotal();
							tv1.setText(Double.toString(totala));
							db.close();
    		    		 }     
    		    	 });  
    		    	 alertDialog1.setNegativeButton("No", new DialogInterface.OnClickListener()
    		    	 {
    		    		 public void onClick(DialogInterface dialog, int which)
    		    		 {
    		    			dialog.dismiss();
    		    		 }
    		    	 });
    		    	 //alertDialog1.show();
    		    	 alertDialog1.show();
    		    	 //<-- See This!    
    		      }  
       });

		additem.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				if (item.getText().toString().trim().length() == 0) 
				{
					item.setError("Please Enter Item Name");
				}
				else if (amount.getText().toString().trim().length() == 0) 
				{
					amount.setError("Please Enter Item Price");
				} 
				else if(Double.parseDouble(amount.getText().toString())<=0)
				{
					amount.setError("Item Price Must Be Greater Than Zero");
				}
				else
				{
					Double priceindouble = 0.0;
					db.open();

					String itemname = item.getText().toString();
					priceindouble = Double.parseDouble(amount.getText().toString());
					totalamount=Double.parseDouble(tv1.getText().toString());
					totalamount = totalamount + priceindouble;
					DecimalFormat df=new DecimalFormat("#.##");
					tv1.setText(df.format(totalamount));
					Toast.makeText(getApplicationContext(),"ITEM "+itemname+" IS ADDED TO THE LIST",Toast.LENGTH_SHORT).show();
					db.insertTitle(1, itemname, priceindouble);
					Long id = db.getrowid();
					System.out.println("id::::::::::::::::" + id);
					
					shopList.add(new Constructor(id, itemname, amount.getText().toString()));
					notes.notifyDataSetChanged();
					item.setText("");
					amount.setText("");
					item.requestFocus();
				}
			}
		});
		db.open();
		shopList.clear();
		db.open();
		Cursor c=db.getlistitems();
		Double n = 0.0;
		if(c.moveToFirst())
		{
			do
			{
				Long i=Long.parseLong(c.getString(0));
				String j=c.getString(1);
				String k=c.getString(2);
				Double m=Double.parseDouble(k);
				n=n+m;
				shopList.add(new Constructor(i,j,k));
				notes.notifyDataSetChanged();
			}
			while(c.moveToNext());
			//TextView tv1= (TextView) findViewById(R.id.label1);
			DecimalFormat df=new DecimalFormat("#.##");
			tv1.setText(df.format(n));	
		}
		c.close();
		db.close();
	}
	
	public  boolean onCreateOptionsMenu(Menu menu)
	{
    	  super.onCreateOptionsMenu(menu);
    	 // menu.add(0,0,0,"Clear").setIcon(R.drawable.clear);
    	  menu.add(0,0,0,"Back").setIcon(R.drawable.back);
    	  menu.add(0,1,0,"Exit").setIcon(R.drawable.exit);
    	  return true;
    }
	public boolean onOptionsItemSelected(MenuItem item) 
	{
   	  switch (item.getItemId())
  	  {
  	  	case 1:  exitOptionsDialog();
  	  			break;
  	  	case 0:  backOptionsDialog();
  	  			break;  
  	  //	case 0:clearOptionsDialog();
  	  			
  	  //			break;
  	  }
  	  return true;
  	}	
	
	private void backOptionsDialog()
	  {
		  new AlertDialog.Builder(this)
		  .setTitle("Alert Message")
		  .setMessage("Are You Sure You Want To Go Back?")
		  .setPositiveButton("Yes",new DialogInterface.OnClickListener()
		  {
			  public void onClick(DialogInterface dialoginterface, int i)
			  {
				 
				  finish();
		
		  }})
		  .setNegativeButton("No",new DialogInterface.OnClickListener()
		  {
			  public void onClick(DialogInterface dialoginterface, int i)
			  {
				  
			  }
		  }).show();
	  }
	private void exitOptionsDialog()
	{
		  new AlertDialog.Builder(this)
		  .setTitle("Alert Message")
		  .setMessage("Are You Sure You Want To Close Application?")
		  .setPositiveButton("Yes",new DialogInterface.OnClickListener()
		  {
			  public void onClick(DialogInterface dialoginterface, int i)
			  {
				 
				  Intent intent = new Intent(Intent.ACTION_MAIN);
				  intent.addCategory(Intent.CATEGORY_HOME);
				  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				  startActivity(intent);
		
		  }})
		  .setNegativeButton("No",new DialogInterface.OnClickListener()
		  {
			  public void onClick(DialogInterface dialoginterface, int i)
			  {
				  
			  }
		  }).show();
	}

	public class ShoppingAdapter extends BaseAdapter
	{
	    
		private Context context;
	    private List<Constructor> shop;
		@SuppressWarnings("unused")
		private int rowResID;
		
		public ShoppingAdapter(Context context, int rowResID, List<Constructor> shop)
		{ 
				this.context = context;
				this.rowResID = rowResID;
				this.shop = shop;
		}
		
	    public int getCount()
	    {                        
	        return shop.size();
	    }
	    
	    public Object getItem(int position)
	    {     
	        return shop.get(position);
	    }
	    
	    public long getItemId(int position)
	    {  
	        return position;
	    }
	    
	    public View getView(final int position, View convertView, ViewGroup parent) 
	    { 
	        final Constructor row = shop.get(position);
	        LayoutInflater inflater = LayoutInflater.from( context );
			View v = inflater.inflate( R.layout.shoplist, parent, false );
			final TextView rowid=(TextView)v.findViewById(R.id.sno);
			final TextView item = (TextView)v.findViewById( R.id.b1);
			final TextView price = (TextView)v.findViewById( R.id.b2 );
			Button delete=(Button)v.findViewById(R.id.minus);
			rowid.setText(Long.toString(row.rowid()));
		    item.setText(row.item());
		    DecimalFormat df=new DecimalFormat("#.##");
		    price.setText(df.format(Double.parseDouble(row.price())));
		    //String itemname;
		    delete.setOnClickListener(new View.OnClickListener()
		    {
		    	public void onClick(View v) 
		    	{
		    				itemn=item.getText().toString();
	    		    	 //Intent myIntent = new Intent(view.getContext(), agones.class);   
	    		    	 //startActivityForResult(myIntent, 0);      
	    		    	 Builder alertDialog = new AlertDialog.Builder(context);
	    		    	 //Read Update    
	    		    	 alertDialog.setTitle("Alert Message");   
	    		    	    		    	 
	    		    	 alertDialog.setMessage("Are You Sure You Want To Delete "+itemn+"?");      
	    		    	 alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    		    	 {          
	    		    		 public void onClick(DialogInterface dialog, int which)
	    		    		 {        
	    		    			 // here you can add functions  
	    		    			 	Double cost=Double.parseDouble(price.getText().toString());
	    							Double total=Double.parseDouble(tv1.getText().toString());
	    							total=total-cost;
	    							
	    							Toast.makeText(getApplicationContext(),"ITEM "+itemn+" IS DELETED FROM THE LIST",Toast.LENGTH_SHORT).show();
	    							DecimalFormat df=new DecimalFormat("#.##");
	    							tv1.setText(df.format(total));
	    							
	    							db.open();
	    							long row1=Long.parseLong(rowid.getText().toString());
	    							System.out.println("row1::::::::::::::::::"+row1);
	    			                db.deleteTitle(row1);
	    			                db.close();
	    			                db.open();
	    			                
	    			                // db.updateTitle(Double.toString(total)) ;
	    		                    db.close();
	    			                
	    		                    shopList.remove(row);
	    			                notes.notifyDataSetChanged();
	    		    		 }     
	    		    	 });  
	    		    	 alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
	    		    	 {
	    		    		 public void onClick(DialogInterface dialog, int which)
	    		    		 {
	    		    			dialog.dismiss();
	    		    		 }
	    		    	 });
	    		    	 alertDialog.show(); 
	    		    	 //<-- See This!    
	    		      }
	       });
		return v;
	    }
	}
}
