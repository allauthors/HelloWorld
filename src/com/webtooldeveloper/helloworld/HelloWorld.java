package com.webtooldeveloper.helloworld;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloWorld extends Activity implements OnClickListener, OnSharedPreferenceChangeListener {
	Button updateButton;
	EditText myName;
	TextView myHello;
	Twitter twitter;
	SharedPreferences prefs;
	
	private static final String TAG = "StatusActivity";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        setContentView(R.layout.hello);
        updateButton = (Button) findViewById(R.id.bButton);
        myName = (EditText) findViewById(R.id.editText1);
        myHello = (TextView) findViewById(R.id.hellotext);
        updateButton.setOnClickListener(this);
    }
    public void onClick (View v){
    	String status = myName.getText().toString();
    	new PosttoTwitter().execute(status);
    	Log.d(TAG,"onClick updated status");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitemPrefs:
			startActivity(new Intent(this, PrefsActivity.class));
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private Twitter getTwitterSingleton() {
		if (twitter == null) {
	        twitter = new Twitter(prefs.getString("username", "student"),prefs.getString("password", "password"));
	        twitter.setAPIRootUrl(prefs.getString("apiroot", "http://yamba.marakana.com/api"));
		}
		return twitter;
	}
	
    public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		twitter = null;
	}



	class PosttoTwitter extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				Twitter.Status status = getTwitterSingleton().updateStatus(params[0]);
				return status.text;
			} catch (TwitterException e)
			{
				Log.e(TAG,e.toString());
				e.printStackTrace();
				return "Failed to Post";
			}
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), result, 1).show();
	    	myName.setText("");
	    	myHello.setTextColor(getResources().getColor(R.color.Green));
	    	myHello.setText("Status Updated");
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
    	
    }
}