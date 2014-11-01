package com.android.personalfinancetoolkit;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalFinanceToolkit extends Activity {
	int i;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.personalfinancetoolkit);
        GridView g = (GridView) findViewById(R.id.myGrid);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemClickListener( new OnItemClickListener()
        {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3)
			{
				
					   switch(position)
					   {
					   
					    case 0:               
					    	Intent myIntent = new Intent();  
					    	 
						      myIntent.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Emi");
						      myIntent.addFlags(Intent.FILL_IN_COMPONENT);
						      startActivity(myIntent);
						      break;

					    case 1:
					    	Intent myIntent1 = new Intent();              
					    	myIntent1.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Future");
					    	startActivity(myIntent1);
					    	break;
					    case 2:
					    	Intent myIntent2 = new Intent();              
					    	myIntent2.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Retirement");
					    	startActivity(myIntent2);
					    	break;
					    case 3:
					    	Intent myIntent3 = new Intent();              
					    	myIntent3.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Savings");
					    	startActivity(myIntent3);
					    	break;
					    case 4:
					    	Intent myIntent4 = new Intent();              
					    	myIntent4.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Discount");
					    	startActivity(myIntent4);
					    	break;
					    case 5:
					    	Intent myIntent5 = new Intent();              
					    	myIntent5.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.IncomeTax");
					    	startActivity(myIntent5);
					    	break;
					    case 6:
					    	Intent myIntent6 = new Intent();              
					    	myIntent6.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Dutch");
					    	startActivity(myIntent6);
					    	break;
					    case 7:
					    	Intent myIntent7=new Intent();
					    	myIntent7.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Shopping");
					    	startActivity(myIntent7);
					    	break;
					    case 8:
					    	Intent myIntent8=new Intent();
					    	myIntent8.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.Converter");
					    	startActivity(myIntent8);
					    	break;
				
					 }

			};
        });
    }
    public  boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	  menu.add(0,0,0,"Back").setIcon(R.drawable.back);
    	  menu.add(0,1,0,"Exit").setIcon(R.drawable.exit);
    	
    	  return true;
    }

public boolean onOptionsItemSelected(MenuItem item) {
	 
	  switch (item.getItemId()) {
	 
	  case 1:  exitOptionsDialog();
		 break;  
	  case 0:
		  	backOptionsDialog();
	  
	  }
	  return true;
}

private void backOptionsDialog()
{
	  new AlertDialog.Builder(this)
	  .setTitle("Alert Message")
	  .setMessage("Are You Sure You Want To Go Back")
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
  .setMessage("Are You Sure You Want To Close Application")
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
    public class ImageAdapter extends BaseAdapter {
    	
        Context mContext;
    	  
        public ImageAdapter(Context c)
        {
            mContext = c;
        }
        public int getCount()
        {
            return mThumbIds.length;
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int rowposition)
        {
            return rowposition;
        }
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v;
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				switch(position)
				{
				case 0:
				
					tv.setText("EMI Calculator ");
					iv.setImageResource(R.drawable.emi);
					break;
				case 1:
				
					tv.setText("Future Calculator");
					iv.setImageResource(R.drawable.future);
					break;
					
				case 2:
				
					tv.setText("Retirement Calculator");
					iv.setImageResource(R.drawable.retirement);
					break;
					
				case 3:
				
					tv.setText("Savings Calculator");
					iv.setImageResource(R.drawable.savings);
					break;
				case 4:
				
					tv.setText("Discount Calculator");
					iv.setImageResource(R.drawable.discount);
					break;
				case 5:
				
					tv.setText("IncomeTax Calculator");
					iv.setImageResource(R.drawable.incometax);
					break;
				case 6:
				
					tv.setText("Dutch Calculator");
					iv.setImageResource(R.drawable.dutch);
					break;
				case 7:
				
					tv.setText("Shopping Calculator");
					iv.setImageResource(R.drawable.shopping);
					break;
				case 8:
					
					tv.setText("Currency Converter");
					iv.setImageResource(R.drawable.converter);
					break;
		
			}
			return v;
		}

        private Integer[] mThumbIds = {
        		  R.drawable.emi,
                  R.drawable.future,
                  R.drawable.retirement,
                  R.drawable.savings,
                  R.drawable.discount,
                  R.drawable.incometax,
                  R.drawable.dutch,
                  R.drawable.shopping,
                  R.drawable.converter
        };
    }
}

