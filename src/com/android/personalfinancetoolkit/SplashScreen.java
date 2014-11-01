package com.android.personalfinancetoolkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.splash);
      Thread splashThread = new Thread() 
      {
         @Override
         public void run() 
         {
            try
            {
               int waited = 0;
               while (waited < 1500) 
               {
                  sleep(100);
                  waited += 100;
               }
            }
            catch (InterruptedException e)
            {
               // do nothing
            }
            finally 
            {
               finish();
               Intent i = new Intent();
               i.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP); 
               i.setClassName("com.android.personalfinancetoolkit","com.android.personalfinancetoolkit.PersonalFinanceToolkit");
               startActivity(i);
            }
         }
      };
      splashThread.start();
   }
}

