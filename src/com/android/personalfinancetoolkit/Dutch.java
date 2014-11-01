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
import android.widget.RadioButton;
import android.widget.TextView;


public class Dutch extends Activity
{
		// Widgets in the application
	    EditText txtAmount;
	     EditText txtPeople;
	     EditText txtTipOther;
	     Button btnCalculate;
	     boolean isError = false;
	     RadioButton rbu1,rbu2,rbu3;
	     TextView txtTipAmount;
	     TextView txtTotalToPay;
	     TextView txtTipPerPerson;
	     TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.dutch);
	        controls();
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
	    	  case 1: backOptionsDialog();
	  			break; 
	    	  case 0:
	    		  		 clearOptionsDialog();
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
				  txtAmount.setText("");
		  		  txtPeople.setText("");
		  		  txtTipOther.setText("");
		  		 
		  		txtTipAmount.setText("");
		  		txtTipPerPerson.setText("");
		  		txtTotalToPay.setText("");
		  		  txtAmount.requestFocus();
		
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
	    private void controls()
	    {
	    	txtAmount = (EditText) findViewById(R.id.txtAmount);
		    txtPeople = (EditText) findViewById(R.id.txtPeople);
		    txtTipOther = (EditText) findViewById(R.id.txtTipOther);
	        tv1 = (TextView) findViewById(R.id.lbl1);
	        tv2 = (TextView) findViewById(R.id.lbl2);
	    	txtTipAmount = (TextView) findViewById(R.id.txtTipAmount);
	    	txtTotalToPay = (TextView) findViewById(R.id.txtTotalToPay);
	        txtTipPerPerson = (TextView) findViewById(R.id.txtTipPerPerson);
	        btnCalculate = (Button) findViewById(R.id.btnCalculate);
	        btnCalculate.setOnClickListener(new Button.OnClickListener()
	        {
				 public void onClick(View v) 
	        	 {
					 dutch();
	        	 }
			});
	    }
	    
	        private void dutch() 
	        {
	        	try
	        	{
	        	
	            
				if(txtAmount.getText().toString().trim().length()==0)
				{
					showErrorAlert("Please Enter Bill Amount",txtAmount.getId());
					isError=true;
				}
				else if(txtPeople.getText().toString().trim().length()==0)
				{
					showErrorAlert("Please Enter No.of people",txtPeople.getId());
					isError=true;
				}
				else if(txtTipOther.getText().toString().trim().length()==0)
				{
					showErrorAlert("Please Enter Tip Percentage",txtTipOther.getId());
					isError=true;
				}
				else
				{
					
					Double billAmount = Double.parseDouble(txtAmount.getText().toString());
		        	Double totalPeople = Double.parseDouble(txtPeople.getText().toString());
		            Double percentage = Double.parseDouble(txtTipOther.getText().toString());
		            
		            if (billAmount < 1.0) 
		            {
		            	showErrorAlert("Enter A Valid Total Amount.",txtAmount.getId());
		            	isError = true;
		            }
		            else if (totalPeople < 1.0)
		            {
		            	showErrorAlert("Enter A Valid Value For No. Of people.",txtPeople.getId());
		            	isError = true;
		            }
		            else if(percentage<0 || percentage>10)
		            {
		            	showErrorAlert("Tip Percentage Should Be Less Than 10.",txtTipOther.getId());
		            	isError=true;
		            }
		            else 
		            {
		            	
		            		Double tipAmount = ((billAmount * percentage) / 100);
		            		Double totalToPay = billAmount + tipAmount;
		            		Double perPersonPays = totalToPay / totalPeople;
		            		perPersonPays=(double) Math.round(perPersonPays);
		            		DecimalFormat df=new DecimalFormat();
		            		Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
							txtTipAmount.setTypeface(typeface);
							txtTotalToPay.setTypeface(typeface);
							txtTipPerPerson.setTypeface(typeface);
		            		txtTipAmount.setText("` "+df.format(tipAmount));
		            		txtTotalToPay.setText("` "+df.format(totalToPay));
		            		//DecimalFormat df1=new DecimalFormat("##.##");
		            		txtTipPerPerson.setText("` "+df.format(perPersonPays));
		            	
		        	}
	        	}
	        	}
				catch(Exception e)
	        	{}
	        	finally{}
	           
	        }
	       
	        private void showErrorAlert(String errorMessage,final int fieldId)
	        {
	            new AlertDialog.Builder(this).setTitle("Error").setMessage(errorMessage).setNeutralButton("OK",new DialogInterface.OnClickListener()
	            {
	            	@Override
	                public void onClick(DialogInterface dialog,int which)
	            	{
	            		findViewById(fieldId).requestFocus();
	                }
	             }).show();
	        }
	    }
 			 
	        	 
	        	 