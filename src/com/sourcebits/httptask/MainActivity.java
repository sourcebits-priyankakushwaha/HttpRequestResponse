package com.sourcebits.httptask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

public class MainActivity extends Activity {

	private EditText edittext;
	private Button button1, button2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edittext = (EditText) findViewById(R.id.urltextbox);
		button1 = (Button) findViewById(R.id.statusbutton);

		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				checkInternetConenction();
			}
		});

		button2 = (Button) findViewById(R.id.connect);
		button2.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("unchecked")
			public void onClick(View v) {
				new MyAsyncTask().execute(" http://www.example.com ");
			}
		});

	}

	// to check the network connection
	public boolean checkInternetConenction() {

		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			Toast.makeText(this, " Network connection is available ", Toast.LENGTH_LONG).show();
			return true;
		} else {
			Toast.makeText(this, "Network connection is not available  ", Toast.LENGTH_LONG).show();
			return false;
		}

	}

	class MyAsyncTask extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... params) {   
	
	try {

            // create HttpClient
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://www.example.com"); 
            HttpResponse response;
            try {
                response = client.execute(request);
                 
                Log.d("Response of GET request", response.toString());
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     
        }
            
	
	}
		// display the response on textview section
		protected void onPostExecute(String result) {
			TextView tv = (TextView) findViewById(R.id.resultview);
			tv.setText("response");

		}

	}

}
