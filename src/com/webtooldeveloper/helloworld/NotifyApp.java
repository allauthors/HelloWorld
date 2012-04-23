/**
 * 
 */
package com.webtooldeveloper.helloworld;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * @author rflach
 *
 */
public class NotifyApp extends Application {
	private static final String TAG = "StatusApplication";

	/* (non-Javadoc)
	 * @see android.app.Application#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "Notify App Created");
	}

	/* (non-Javadoc)
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		Log.i(TAG, "Notify App Terminated");
	}

}
