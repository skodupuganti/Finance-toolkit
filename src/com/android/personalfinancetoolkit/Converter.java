package com.android.personalfinancetoolkit;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Converter extends Activity  {
	TextView dollars;
	TextView euros;
	TextView rupees;
    RadioButton dol,eur,rup;
   	Button convert;
	EditText dollar1,euro1,rupee1;
	RadioGroup rg1;
	InputStream in = null;
	
	 String page1=null,page2=null,page3=null,page4=null,page5=null,page6=null;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.converter);
        
        dollar1=(EditText) this.findViewById(R.id.dollar);
        euro1=(EditText) this.findViewById(R.id.euro);
        rupee1=(EditText) this.findViewById(R.id.rupee);
        
        // euro1.setFocusable(false);
		//rupee1.setFocusable(false);
		//dollar1.setFocusable(false);
		
        dollar1.setEnabled(false);
        euro1.setEnabled(false);
        rupee1.setEnabled(false);
        
        dollars = (TextView)this.findViewById(R.id.dollars);
        euros = (TextView)this.findViewById(R.id.euros);
        rupees = (TextView)this.findViewById(R.id.rupees);
        
        rg1=(RadioGroup) findViewById(R.id.group1);
        
        dol = (RadioButton)this.findViewById(R.id.dol);
        eur = (RadioButton)this.findViewById(R.id.eur);
        rup = (RadioButton)this.findViewById(R.id.rup);
       
        rg1.setOnCheckedChangeListener(mCheckListener);
        convert = (Button)this.findViewById(R.id.convert);
        
        
        convert.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v)
        	{
        		if (dol.isChecked())
        		{
        			
        			rupee1.setText("");
        			euro1.setText("");
        			convertDollarsToEuros();
        			convertDollarstoRupees();
        		
        		}
        		else if(eur.isChecked())
        		{
        			
        			rupee1.setText("");
        			dollar1.setText("");
        			convertEurosToDollars();
        			convertEurosToRupees();
        		}
        		else if(rup.isChecked())
        		{
        			
        			euro1.setText("");
        			dollar1.setText("");
        			convertRupeesToDollars();
        			convertRupeesToEuros();
        		}
        		else
        		{
        			Toast.makeText(getApplicationContext(), "Please Select Any One", Toast.LENGTH_LONG).show();
        			
        		}
        		
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
			  dollar1.setText("");
	  		  rupee1.setText("");
	  		  euro1.setText("");
	
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
	private OnCheckedChangeListener mCheckListener = new OnCheckedChangeListener() 
    {

    		

			@Override
    		public void onCheckedChanged(RadioGroup group, int checkedId)
    		{
    			// TODO Auto-generated method stub
    			if (checkedId == R.id.dol)
    			{
    				Toast.makeText(getApplicationContext(),"You Selected Dollar Conversion",Toast.LENGTH_SHORT).show();
    				euro1.setText("");
        			dollar1.setText("");
        			rupee1.setText("");
    				
        			dollar1.setEnabled(true);
    				euro1.setEnabled(false);
    				rupee1.setEnabled(false);
    				   				
    				
    				//euro1.setFocusable(false);
    				//rupee1.setFocusable(false);
    				rupee1.clearFocus();
    				rupee1.clearFocus();
    				dollar1.requestFocus();
    				
    				getURLContent(page1);
    				getURLContent1(page2);
    			}
    			else if(checkedId==R.id.eur)
    			{
    				Toast.makeText(getApplicationContext(),"You Selected Euro Conversion",Toast.LENGTH_SHORT).show();
    				
    				euro1.setText("");
        			dollar1.setText("");
        			rupee1.setText("");
    				
    			
    				dollar1.setEnabled(false);
    				euro1.setEnabled(true);
    				rupee1.setEnabled(false);
    			
    				//dollar1.setFocusable(false);
    				//rupee1.setFocusable(false);
    				rupee1.clearFocus();
    				dollar1.clearFocus();
    				euro1.requestFocus();
    				
    				getURLContent2(page3);
    				getURLContent3(page4);
    			}
    			else if(checkedId==R.id.rup)
    			{
    				Toast.makeText(getApplicationContext(),"You Selected Rupee Conversion",Toast.LENGTH_SHORT).show();
    				
    				euro1.setText("");
        			dollar1.setText("");
        			rupee1.setText("");
    				
    				dollar1.setEnabled(false);
    				euro1.setEnabled(false);
    				rupee1.setEnabled(true);
    				
    				//euro1.setFocusable(false);
    				//dollar1.setFocusable(false);
    				euro1.clearFocus();
    				dollar1.clearFocus();
    				rupee1.requestFocus();
    		
    				getURLContent4(page5);
    				getURLContent5(page6);
    			}
    			else
    			{
    				
    			}
    			
    			
    		}
    		
    };
	protected void convertDollarsToEuros()
	{
		if(dollar1.getText().toString().trim().length()==0)
		{
			dollar1.setError("Please Enter Dollars");
		}
		else if(Double.parseDouble(dollar1.getText().toString())<=0)
		{
			dollar1.setError("No.Of Dollars Shoule Be Greater Than Zero");
		}
		else
		{
			
			Double val1 = Double.parseDouble(dollar1.getText().toString());
			if(page1==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1*0.691740;
				DecimalFormat df=new DecimalFormat("#.##");
				euro1.setText("€ "+df.format(val1));
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page1.toString();
			
			String str2=s.substring(28,34);
			System.out.println("str2:::::::::::::::::::::::::"+str2);
			Double v1=val1*Double.parseDouble(str2);
			DecimalFormat df=new DecimalFormat("#.##");
			euro1.setText("€ "+df.format(v1));
			}
		}
	}
	
	protected void convertEurosToDollars()
	{
		if(euro1.getText().toString().trim().length()==0)
		{
			euro1.setError("Please Enter Euros");
		}
		else if(Double.parseDouble(euro1.getText().toString())<=0)
		{
			euro1.setError("No.Of Euros Should Be Greater Than Zero");
		}
		else
		{
			Double val1 = Double.parseDouble(euro1.getText().toString());
			if(page3==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1/0.691740;
				DecimalFormat df=new DecimalFormat("#.##");
				dollar1.setText("$ "+df.format(val1));
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page3.toString();
			
			String str2=s.substring(21,27);
			Double v1=val1*Double.parseDouble(str2);
			DecimalFormat df=new DecimalFormat("#.##");
			dollar1.setText("$ "+df.format(v1));
		}
		}	
	}
	protected void convertDollarstoRupees()
	{
		if(dollar1.getText().toString().trim().length()==0)
		{
			dollar1.setError("Please Enter Dollars");
		}
		else if(Double.parseDouble(dollar1.getText().toString())<=0)
		{
			dollar1.setError("No.Of Dollars Should Be Greater Than Zero");
		}
		else
		{
			Double val1 = Double.parseDouble(dollar1.getText().toString());
			if(page2==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1*44.3;
				DecimalFormat df=new DecimalFormat("#.##");
				Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
				rupee1.setTypeface(typeface);
				rupee1.setText("` "+df.format(val1));
			}
			else 
			{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page2.toString();
			
			String str2=s.substring(28,34);
			
			System.out.println("str2:::::::::::::::::::::::::"+str2);
			Double v1=val1*Double.parseDouble(str2);
			Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
			rupee1.setTypeface(typeface);
			DecimalFormat df=new DecimalFormat("#.##");
			rupee1.setText("` "+df.format(v1));
			}
		}
	}
	protected void convertRupeesToDollars()
	{
		if(rupee1.getText().toString().trim().length()==0)
		{
			rupee1.setError("Please Enter Rupees");
		}
		else if(Double.parseDouble(rupee1.getText().toString())<=0)
		{
			rupee1.setError("No.Of Rupees Should Be Greater Than Zero");
		}
		
		else
		{
			Double val1 = Double.parseDouble(rupee1.getText().toString());
			if(page5==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1/44.3;
				DecimalFormat df=new DecimalFormat("#.##");
				dollar1.setText("$ "+df.format(val1));
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page5.toString();
			
			String str2=s.substring(29,35);
			Double v1=val1*Double.parseDouble(str2);
			DecimalFormat df=new DecimalFormat("#.##");
			dollar1.setText("$ "+df.format(v1));
		}
		}	
	}
	protected void convertEurosToRupees()
	{
		if(euro1.getText().toString().trim().length()==0)
		{
			euro1.setError("Please Enter Euros");
		}
		else if(Double.parseDouble(euro1.getText().toString())<=0)
		{
			euro1.setError("No.Of Euros Should Be Greater Than Zero");
		}
		else
		{
			Double val1 = Double.parseDouble(euro1.getText().toString());
			if(page4==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1*69.9133;
				DecimalFormat df=new DecimalFormat("#.##");
				Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
				rupee1.setTypeface(typeface);
				rupee1.setText("` "+df.format(val1));
			}
			else{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page4.toString();
			
			String str2=s.substring(21,27);
			Double v1=val1*Double.parseDouble(str2);
			Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
			rupee1.setTypeface(typeface);
			DecimalFormat df=new DecimalFormat("#.##");
			rupee1.setText("` "+df.format(v1));
		}}
	}
	protected void convertRupeesToEuros()
	{
		if(rupee1.getText().toString().trim().length()==0)
		{
			rupee1.setError("Please enter Rupees");
		}
		else if(Double.parseDouble(rupee1.getText().toString())<=0)
		{
			rupee1.setError("No.Of Rupees Should Be Greater Than Zero");
		}
		else
		{
			Double val1 = Double.parseDouble(rupee1.getText().toString());
			if(page6==null)
			{
				Toast.makeText(getApplicationContext(), "Standard Conversion",Toast.LENGTH_SHORT).show();
				val1=val1/69.9133;
				DecimalFormat df=new DecimalFormat("#.##");
				euro1.setText("€ "+df.format(val1));
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Url Conversion",Toast.LENGTH_SHORT).show();
			String s=page6.toString();
				
			String str2=s.substring(29,35);
			Double v1=val1*Double.parseDouble(str2);
			DecimalFormat df=new DecimalFormat("#.##");
			euro1.setText("€ "+df.format(v1));
		}}
	}
	public String getURLContent(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1USD%3D%3FEUR");    
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page1 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page1::::::::::::::"+page1);
    		return page1;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
	public String getURLContent1(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1USD%3D%3FINR");    
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page2 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page2::::::::::::::"+page2);
    		return page2;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
	public String getURLContent2(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1EUR%3D%3FUSD");    
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page3 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page3::::::::::::::"+page3);
    		return page3;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
	public String getURLContent3(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1EUR%3D%3FINR");    
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page4 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page4::::::::::::::"+page4);
    		return page4;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
	public String getURLContent4(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1INR%3D%3FUSD");    
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page5 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page5::::::::::::::"+page5);
    		return page5;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
	public String getURLContent5(String url) 
    {   
    	try
    	{    
    		DefaultHttpClient httpClient = new DefaultHttpClient();      
    		HttpGet httpGet = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1INR%3D%3FEUR");
    		ResponseHandler<String> resHandler = new BasicResponseHandler(); 
    		page6 = httpClient.execute(httpGet, resHandler); 
    		System.out.println("page6::::::::::::::"+page6);
    		return page6;
    	} 
    	catch (ClientProtocolException e) 
    	{       
    		return null;   
    	} 
    	catch (IOException e)
    	{      
    		return null;  
    	} 
   	} 
}

