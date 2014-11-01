package com.android.personalfinancetoolkit;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Discount extends Activity {
	Button button1,button2;
	EditText txtbox1,txtbox2; 
	TextView tv1,tv2,tv3,tv4,tv5,tv6; 
	double x=0;
	double y=0; 
	double z=0; 
	double w=0;
	double b=0;
	double a=0;
	int level;

	
	//@Override
	public void onCreate(Bundle savedInstanceState)
	{     
		
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.discount);   
		controls();
	}


	private void controls()
	{
		
		txtbox1 = (EditText) findViewById(R.id.txtbox1);
		txtbox2 = (EditText) findViewById(R.id.txtbox2);
		tv1 = (TextView) findViewById(R.id.lbl1);
		tv2 = (TextView) findViewById(R.id.lbl2);
		tv3= (TextView) findViewById(R.id.lbl3);
		tv4= (TextView) findViewById(R.id.lbl4);
		tv5=(TextView) findViewById(R.id.lbl5);
		tv6=(TextView) findViewById(R.id.lbl6);
	
		button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new Button.OnClickListener()
		{
			
	
			public void onClick(View v1) 
			{
				discount();
			}
		});
	}
	public  boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	  menu.add(0,0,0,"Clear").setIcon(R.drawable.clear);
    	  menu.add(0,1,0,"Back").setIcon(R.drawable.back);
    	  menu.add(0,2,0,"Exit").setIcon(R.drawable.exit);
    	  return true;
    }

  
  public boolean onOptionsItemSelected(MenuItem item) {
    	 
    	  switch (item.getItemId()) {
    	  case 2:  exitOptionsDialog();
			break;
    	  case 1:  backOptionsDialog();
    	  break; 
    	  case 0:clearOptionsDialog();
    		  		  break;
    	  
    	  }
    	  return true;
    	  
    }
  private void clearOptionsDialog()
  {
	  new AlertDialog.Builder(this)
	  .setTitle("Alert Message")
	  .setMessage("Are You Sure You Want To Clear The Form?")
	  .setPositiveButton("Yes",new DialogInterface.OnClickListener()
	  {
		  public void onClick(DialogInterface dialoginterface, int i)
		  {

	  		  txtbox1.setText("");
	  		  txtbox2.setText("");
	  		  tv4.setText("");
	  		  tv6.setText("");
	  		  txtbox1.requestFocus();
	
	  }})
	  .setNegativeButton("No",new DialogInterface.OnClickListener()
	  {
		  public void onClick(DialogInterface dialoginterface, int i)
		  {
			  
		  }
	  }).show();
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
	public void discount()
			{
					
				try
				{
					
					if(txtbox1.getText().toString().trim().length()==0)
					{
						txtbox1.setError("Please Enter Amount");
						tv4.setText("");
						tv6.setText("");
						
					}
					else if(txtbox2.getText().toString().trim().length()==0)
					{
						txtbox2.setError("Please Enter Discount");
						tv4.setText("");
						tv6.setText("");
					}
					
					else
					{
					
					x=Double.parseDouble(txtbox1.getText().toString());//amount
					y=Double.parseDouble(txtbox2.getText().toString());//discount
					if(x<1 || x>1000000000)
					{
							txtbox1.setError("Please Enter Amount Between 1 And 1000000000");
							tv4.setText("");
							tv6.setText("");
					}
					else if(y<0 || y>100)
					{
						txtbox2.setError("Please Enter Discount Between 1 And 100");
						tv4.setText("");
						tv6.setText("");
					}
				
					else
					{
						y=(y/100)*x;//discount rate
						//y=Math.round(y);
						w=x-y;
						DecimalFormat df=new DecimalFormat("#.##");
						Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
						tv4.setTypeface(typeface);
						tv6.setTypeface(typeface);
						tv4.setText("` "+df.format(y));
						tv6.setText("` "+df.format(w));
						}
					}
				}
				catch(Exception ee)
				{
				}
				finally
				{
				}
				
			}
		
}

