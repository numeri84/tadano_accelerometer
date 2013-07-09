package com.example.tadano_accelerometer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;

import android.widget.FrameLayout;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MainActivity extends Activity implements SensorEventListener {
	
	private SensorManager _manager;
	private Sensor _accelerometerSensor;
	private TextView _accelTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		FrameLayout layout = new FrameLayout(this);
		setContentView(layout);
		
		_accelTextView = new TextView(this);
		_accelTextView.setText("加速度センサー値");
		layout.addView(_accelTextView);
		
		_manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		_accelerometerSensor = _manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Log.d("tag", "onSensorChanged");
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			String string = "加速度センサー値" 
							+ "\nX : " + event.values[0]
							+ "\nY : " + event.values[1]
							+ "\nZ : " + event.values[2];
			
			_accelTextView.setText(string);
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// Listenerの登録解除
		_manager.unregisterListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		_manager.registerListener(this, _accelerometerSensor, SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
