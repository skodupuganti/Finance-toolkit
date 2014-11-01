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

public class Retirement extends Activity {
	
	//public static final String tag = "EmiCalculate";
	Button button1,button2;

	EditText txtbox1,txtbox2,txtbox3,txtbox4; 
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8; 
	double x=0;
	double y=0; 
	double z=0; 
	double w=0;
	double p=0;
	double q=0;
	double r=0;
	double a=0;
	int level;

	
	//@Override
	public void onCreate(Bundle savedInstanceState)
	{     
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.retirement);   
		controls();
	}
	private void controls()
	{
		txtbox1 = (EditText) findViewById(R.id.txtbox1);
		txtbox2 = (EditText) findViewById(R.id.txtbox2);
		txtbox3 = (EditText) findViewById(R.id.txtbox3);
		txtbox4 = (EditText) findViewById(R.id.txtbox4);
				
		tv1 = (TextView) findViewById(R.id.lbl1);
		tv2 = (TextView) findViewById(R.id.lbl2);
		tv3 = (TextView) findViewById(R.id.lbl3);
		tv4 = (TextView) findViewById(R.id.lbl4);
		tv5 = (TextView) findViewById(R.id.lbl5);
		tv6 = (TextView) findViewById(R.id.lbl6);
		tv7 = (TextView) findViewById(R.id.lbl7);
		tv8 = (TextView) findViewById(R.id.lbl8);
		
		button1 = (Button) findViewById(R.id.button1);
		
		button1.setOnClickListener(new Button.OnClickListener()
		{
	
			public void onClick(View v1) 
			{
				retirement();
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
    	  case 0:	clearOptionsDialog();
    		  		  
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
	  		  txtbox3.setText("");
	  		  txtbox4.setText("");
	  		  tv6.setText("");
	  		  tv8.setText("");
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
  	public void retirement()
			{
				
					
				try
				{
					
					if(txtbox1.getText().toString().trim().length()==0)
					{
						txtbox1.setError("Please Enter Present Age");
						tv6.setText("");
						tv8.setText("");
					}
					else if(txtbox2.getText().toString().trim().length()==0)
					{
						txtbox2.setError("Please Enter Retired Age");
						tv6.setText("");
						tv8.setText("");
					}
					else if(txtbox3.getText().toString().trim().length()==0)
					{
						txtbox3.setError("Please Enter Amount");
						tv6.setText("");
						tv8.setText("");
					}
					else if(txtbox4.getText().toString().trim().length()==0)
					{	
						txtbox4.setError("Please Enter Interest");
						tv6.setText("");
						tv8.setText("");
					}
					else 
					{
						x=Double.parseDouble(txtbox1.getText().toString());//present age
						y=Double.parseDouble(txtbox2.getText().toString());//retired age
						z=Double.parseDouble(txtbox3.getText().toString());//amount
						p=Double.parseDouble(txtbox4.getText().toString());//interest
						if(x>y)
						{ 
							txtbox1.setError("Present Age Must Be Less Than Retired Age");
							tv6.setText("");
							tv8.setText("");
						}
						else if(x==y)
						{ 
							txtbox1.setError("Present & Retired Ages Should Not Be Equal");
							//txtbox1.setError("present & retired age must not be equal");
							tv6.setText("");
							tv8.setText("");
						}
						else if(x<=18 || x>58)
						{
							txtbox1.setError("Please Enter Present Age Between 18 And 58");
							tv6.setText("");
							tv8.setText("");
						}
						else if(y<=30 || y>80)
						{
							txtbox2.setError("Please Enter Retired Age Between 30 And 80");
							tv6.setText("");
							tv8.setText("");
						}
						else if(z<=1 || z>1000000000)
						{
							txtbox3.setError("Please Enter Amount Between 1 And 100000");
							tv6.setText("");
							tv8.setText("");
						}
						else if(p<=0 || p>100)
						{
							txtbox4.setError("Please Enter Interest Between 1 And 100");
							tv6.setText("");
							tv8.setText("");
						}
						
						else
						{
							q=y-x;
							w=q*12;
							p=p/1200;
							a=z*(1+p)*((Math.pow((1+p),w))-1)/p;
							a=Math.round(a);
							DecimalFormat df=new DecimalFormat();
							Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
							tv8.setTypeface(typeface);
							tv6.setText(df.format(q)+" Years");
							tv8.setText(df.format(a));
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


