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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class IncomeTax extends Activity  { 
    /** Called when the activity is first created. */ 
 	Button button1;
	EditText txtbox1,txtbox2; 
	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
	double x=0;
	double y=0; 
	double z=0; 
	double w=0;
	double b=0;
	double a=0;
	double c=0;
	double d=0;
	double e=0;
	double f=0;
	double g=0;
	double h=0;
	double i=0;
	double j=0;
	double k=0;
	int level;

	
	//@Override
	public void onCreate(Bundle savedInstanceState)
	{     
		
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.incometax);  
		final Spinner payeetype = (Spinner) findViewById(R.id.spinner1);

		//int payeetype_selected=payeetype.getSelectedItemPosition();
		
		ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,
				R.array.payeetype, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		payeetype.setAdapter(adapter);
		payeetype.setOnItemSelectedListener(new MyOnItemSelectedListener());
		controls();
	}


	private void controls()
	{
		
		txtbox1 = (EditText) findViewById(R.id.txtbox1);
		txtbox2= (EditText) findViewById(R.id.txtbox2);
		t1 = (TextView) findViewById(R.id.lb1);
		t2 = (TextView) findViewById(R.id.lb2);
		t3= (TextView) findViewById(R.id.lb3);
		t4= (TextView) findViewById(R.id.lb4);
		t5=(TextView) findViewById(R.id.lb5);
		t8 = (TextView) findViewById(R.id.lb8);
		t9 = (TextView) findViewById(R.id.lb9);
		t10= (TextView) findViewById(R.id.lb10);
		t11= (TextView) findViewById(R.id.lb11);
		t12=(TextView) findViewById(R.id.lb12);
		t13=(TextView) findViewById(R.id.lb13);
		tv1=(TextView)findViewById(R.id.b1);
		tv2=(TextView)findViewById(R.id.b2);
		tv3=(TextView)findViewById(R.id.b3);
		tv4=(TextView)findViewById(R.id.b4);
		tv5=(TextView)findViewById(R.id.b5);
		tv6=(TextView)findViewById(R.id.b6);
		tv7=(TextView)findViewById(R.id.b7);
		tv8=(TextView)findViewById(R.id.b8);
		tv9=(TextView)findViewById(R.id.tv1);
		tv10=(TextView)findViewById(R.id.a1);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "Rupee_For_Indian.ttf");
		tv1.setTypeface(typeface);
		tv2.setTypeface(typeface);
		tv3.setTypeface(typeface);
		tv4.setTypeface(typeface);
		tv5.setTypeface(typeface);
		tv6.setTypeface(typeface);
		tv7.setTypeface(typeface);
		tv8.setTypeface(typeface);
		t5.setTypeface(typeface);
		t9.setTypeface(typeface);
		t11.setTypeface(typeface);
		t13.setTypeface(typeface);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v1) 
			{
								final Spinner payee = (Spinner) findViewById(R.id.spinner1);
				String selectedpayee=payee.getSelectedItem().toString();
				if(selectedpayee.equals("Citizen"))
				{
					citizen();
				}
				else if(selectedpayee.equals("Sr.citizen>60"))
				{
					srCitizen();
				}
				else 
				{
					verySrCitizen();
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
			  txtbox1.setText("");
	  		  txtbox2.setText("");
	  		  tv1.setText("");
	  		  tv2.setText("");
	  		  tv3.setText("");
	  		  tv4.setText("");
	  		  tv5.setText("");
	  		  tv6.setText("");
	  		  tv7.setText("");
	  		  tv8.setText("");
	  		  tv9.setText("");
	  		  t5.setText("");
			//t7.setText("");
	  		  t9.setText("");
	  		  t11.setText("");
	  		  t13.setText("");
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
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
		
		public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), "PAYEE TYPE IS " +
	          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
	      switch(pos)
	      {
	      	case 0:
	      		txtbox1.setText("");
	      		txtbox2.setText("");
	      		txtbox1.requestFocus();
	      		tv10.setText("180,000");
	      		tv1.setText("");
				tv2.setText("");
				tv3.setText("");
				tv4.setText("");
				tv5.setText("");
				tv6.setText("");
				tv7.setText("");
				tv8.setText("");
				tv9.setText("");
				t5.setText("");
				//t7.setText("");
				t9.setText("");
				t11.setText("");
				t13.setText("");
	      		break;
	      	case 1:
	      		txtbox1.setText("");
	      		txtbox2.setText("");
	      		txtbox1.requestFocus();
	      		tv10.setText("190,000");
	      		tv1.setText("");
				tv2.setText("");
				tv3.setText("");
				tv4.setText("");
				tv5.setText("");
				tv6.setText("");
				tv7.setText("");
				tv8.setText("");
				tv9.setText("");
				t5.setText("");
				//t7.setText("");
				t9.setText("");
				t11.setText("");
				t13.setText("");
	      		break;
	      	case 2:
	      		txtbox1.setText("");
	      		txtbox2.setText("");
	      		txtbox1.requestFocus();
	      		tv10.setText("240,000");
	      		tv1.setText("");
				tv2.setText("");
				tv3.setText("");
				tv4.setText("");
				tv5.setText("");
				tv6.setText("");
				tv7.setText("");
				tv8.setText("");
				tv9.setText("");
				t5.setText("");
				//t7.setText("");
				t9.setText("");
				t11.setText("");
				t13.setText("");
	      		break;
	      }
	      		
	      	
	      	
	      
	    }

	    public void onNothingSelected(@SuppressWarnings("rawtypes") AdapterView parent) {
	      // Do nothing.
	    }
	}
		
			public void citizen()
			{
					
				try
				{
					
					
					if(txtbox1.getText().toString().trim().length()==0)
					{
						txtbox1.setError("Please Enter Gross Total Income");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					else if(txtbox2.getText().toString().trim().length()==0)
					{
						txtbox2.setError("Please Enter Deduction Amount");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					
					else
					{
					
						x=Double.parseDouble(txtbox1.getText().toString());//amount
						y=Double.parseDouble(txtbox2.getText().toString());
						
						if(x<1 || x>1000000000)
						{
							txtbox1.setError("Please Enter Amount Between 1 And 1000000000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>100000)
						{
							txtbox2.setError("Deduction Amount Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income And Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y==x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
							
						}
						
						else if(x>=1 && x<=180000)
						{
							
							a=x-y;
							f=a-180000;
							b=f*0.10;
							c=x/12;
							double c1=x/12;
							d=b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat("#.##");
							tv1.setText(df.format(a));
							tv2.setText("-");
							tv3.setText("-");
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("-");
							tv9.setText("-");
							t5.setText(df.format(a));
							//t7.setText(df.format(b));
							t9.setText(df.format(c));
							t11.setText("-");
							t13.setText("0%");
							
						}
						else if((x-y)>180000 && (x-y)<=500000)
						{
							a=x-y;
							f=a-180000;
							b=f*0.10;
							c=(int)x/12;
							double c1=x/12;
							d=(int)b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("180,000");
							tv2.setText(df.format(f));
							tv3.setText(df.format(b));
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(b));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(b));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
						}
						else if((x-y)>500000 && (x-y)<=800000)
						{							
							a=x-y;
							g=a-500000;
							i=g*0.20;
							h=g*0.20+32000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)h/12;
							double d1=h/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("180,000");
							tv2.setText("320,000");
							tv3.setText("32,000");
							tv4.setText(df.format(g));
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(h));
							tv9.setText("-");
							tv5.setText("` "+df.format(i));
							t5.setText("` "+df.format(a));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
						}
						else
						{
							a=x-y;
							i=a-800000;
							h=i*0.30;
							j=i*0.30+92000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)j/12;
							double d1=j/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("180,000");
							tv2.setText("320,000");
							tv3.setText("32,000");
							tv4.setText("300,000");
							tv5.setText("60,000");
							tv6.setText(df.format(i));
							tv7.setText(df.format(h));
							tv8.setText("` "+df.format(j));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(j));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
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
			public void srCitizen()
			{
					
				try
				{
					
					
					if(txtbox1.getText().toString().trim().length()==0)
					{
						txtbox1.setError("Please Enter Gross Total Income");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					else if(txtbox2.getText().toString().trim().length()==0)
					{
						txtbox2.setError("Please Enter Deduction Amount");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					
					else
					{
					
						x=Double.parseDouble(txtbox1.getText().toString());//amount
						y=Double.parseDouble(txtbox2.getText().toString());
						
						if(x<1 || x>1000000000)
						{
							txtbox1.setError("Please Enter Amount Between 1 And 1000000000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>100000)
						{
							txtbox2.setError("Deduction Amount Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income And Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y==x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
							
						}
						
						else if(x>=1 && x<=190000)
						{
							
							a=x-y;
							f=a-190000;
							b=f*0.10;
							c=x/12;
							double c1=x/12;
							d=b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat("#.##");
							tv1.setText(df.format(a));
							tv2.setText("-");
							tv3.setText("-");
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("-");
							tv9.setText("-");
							t5.setText(df.format(a));
							//t7.setText(df.format(b));
							t9.setText(df.format(c));
							t11.setText("-");
							t13.setText("0%");
							
							
						}
						else if((x-y)>190000 && (x-y)<=500000)
						{
							a=x-y;
							f=a-190000;
							b=f*0.10;
							c=(int)x/12;
							double c1=x/12;
							d=(int)b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("190,000");
							tv2.setText(df.format(f));
							tv3.setText(df.format(b));
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(b));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(b));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
						}
						else if((x-y)>500000 && (x-y)<=800000)
						{							
							a=x-y;
							g=a-500000;
							i=g*0.20;
							h=g*0.20+31000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)h/12;
							double d1=h/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("190,000");
							tv2.setText("310,000");
							tv3.setText("31,000");
							tv4.setText(df.format(g));
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(h));
							tv9.setText("-");
							tv5.setText("` "+df.format(i));
							t5.setText("` "+df.format(a));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
						}
						else
						{
							a=x-y;
							i=a-800000;
							h=i*0.30;
							j=i*0.30+91000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)j/12;
							double d1=j/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("190,000");
							tv2.setText("310,000");
							tv3.setText("31,000");
							tv4.setText("300,000");
							tv5.setText("60,000");
							tv6.setText(df.format(i));
							tv7.setText(df.format(h));
							tv8.setText("` "+df.format(j));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(j));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
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
			public void verySrCitizen()
			{
					
				try
				{
					
					
					if(txtbox1.getText().toString().trim().length()==0)
					{
						txtbox1.setError("Please Enter Gross Total Income");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					else if(txtbox2.getText().toString().trim().length()==0)
					{
						txtbox2.setError("Please Enter Deduction Amount");
						tv1.setText("");
						tv2.setText("");
						tv3.setText("");
						tv4.setText("");
						tv5.setText("");
						tv6.setText("");
						tv7.setText("");
						tv8.setText("");
						tv9.setText("");
						t5.setText("");
						//t7.setText("");
						t9.setText("");
						t11.setText("");
						t13.setText("");
					}
					
					else
					{
					
						x=Double.parseDouble(txtbox1.getText().toString());//amount
						y=Double.parseDouble(txtbox2.getText().toString());
						
						if(x<1 || x>1000000000)
						{
							txtbox1.setError("Please Enter Amount Between 1 And 1000000000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>100000)
						{
							txtbox2.setError("Deduction Amount Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y>x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income And Should Not Exceed 100000");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
						}
						else if(y==x)
						{
							txtbox2.setError("Deduction Amount Should Be Less Than Gross Income");
							tv1.setText("");
							tv2.setText("");
							tv3.setText("");
							tv4.setText("");
							tv5.setText("");
							tv6.setText("");
							tv7.setText("");
							tv8.setText("");
							tv9.setText("");
							t5.setText("");
							//t7.setText("");
							t9.setText("");
							t11.setText("");
							t13.setText("");
							
							
						}
						
						else if(x>=1 && x<=240000)
						{
							
							a=x-y;
							f=a-240000;
							b=f*0.10;
							c=x/12;
							double c1=x/12;
							d=b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat("#.##");
							tv1.setText(df.format(a));
							tv2.setText("-");
							tv3.setText("-");
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("-");
							tv9.setText("-");
							t5.setText(df.format(a));
							//t7.setText(df.format(b));
							t9.setText(df.format(c));
							t11.setText("-");
							t13.setText("0%");
							
						}
						else if((x-y)>240000 && (x-y)<=500000)
						{
							a=x-y;
							f=a-240000;
							b=f*0.10;
							c=(int)x/12;
							double c1=x/12;
							d=(int)b/12;
							double d1=b/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("240,000");
							tv2.setText(df.format(f));
							tv3.setText(df.format(b));
							tv4.setText("-");
							tv5.setText("-");
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(b));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(b));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
						}
						else if((x-y)>500000 && (x-y)<=800000)
						{							
							a=x-y;
							g=a-500000;
							i=g*0.20;
							h=g*0.20+26000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)h/12;
							double d1=h/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("240,000");
							tv2.setText("260,000");
							tv3.setText("26,000");
							tv4.setText(df.format(g));
							tv6.setText("-");
							tv7.setText("-");
							tv8.setText("` "+df.format(h));
							tv9.setText("-");
							tv5.setText("` "+df.format(i));
							t5.setText("` "+df.format(a));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
						}
						else
						{
							a=x-y;
							i=a-800000;
							h=i*0.30;
							j=i*0.30+86000;
							c=(int)x/12;
							double c1=x/12;
							d=(int)j/12;
							double d1=j/12;
							e=(d1/c1)*100;
							DecimalFormat df=new DecimalFormat();
							tv1.setText("240,000");
							tv2.setText("260,000");
							tv3.setText("26,000");
							tv4.setText("300,000");
							tv5.setText("60,000");
							tv6.setText(df.format(i));
							tv7.setText(df.format(h));
							tv8.setText("` "+df.format(j));
							tv9.setText("-");
							t5.setText("` "+df.format(a));
							//t7.setText(df.format(j));
							t9.setText("` "+df.format(c));
							t11.setText("` "+df.format(d));
							DecimalFormat df1 = new DecimalFormat("#.##");
							t13.setText(df1.format(e)+"%");
							
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

