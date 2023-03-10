package com.datapirates.ins;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rssireader.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity<x> extends AppCompatActivity {
	public static final String EXTRA_NUMBER_X = "com.datapirates.ins.xaxi";
	public static final String EXTRA_NUMBER_Y = "com.datapirates.ins.yaxi";
	double d1, d2, d3;
	private static ImageView imageView;
	private static Button button;
	private int current_image;
	private ImageView image;
	public ArrayList<Integer> products;
	public ArrayList<Integer> productsNew;
	public ArrayList<Integer> routes;
	public ArrayList<String> productsList;



	private static String TAG = "_RSSI";
	private static int INTERVAL_FOR_RSSI = 1000; //in mSec
	protected WifiManager mWifiManager;
	private TextView ssidTextView;
	private TextView xTextView;
	private TextView yTextView;
	private Button startButton;
	private Handler wifiScanHandler;
	private MyBroadcastReceiver myBroadcastReceiver;
	private IntentFilter intentFilter;
	private File outputFile;
	private FileOutputStream foo;
	private Runnable wifiScan = new Runnable() {
		@Override
		public void run() {
			if (startButton.getText().equals("Stop")) {
				scanForWifi();
				wifiScanHandler.postDelayed(wifiScan, INTERVAL_FOR_RSSI);
			}
		}
	};
//------------------------------------------------------------------------------------------
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		String requiredpermissions[] = {
				Manifest.permission.ACCESS_COARSE_LOCATION,
				Manifest.permission.WRITE_EXTERNAL_STORAGE};
		permissions(this, requiredpermissions);

		ssidTextView = (TextView) findViewById(R.id.setSSID);
		xTextView = (TextView) findViewById(R.id.textViewX);
		yTextView = (TextView) findViewById(R.id.textViewY);
		startButton = (Button) findViewById(R.id.start_stop);
		wifiScanHandler = new Handler();
		intentFilter = new IntentFilter();
		intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

		myBroadcastReceiver = new MyBroadcastReceiver(this);
		registerReceiver(myBroadcastReceiver, intentFilter);

		outputFile = new File(
				"/storage/self/primary",
				"Rssi" + new Random().nextInt(100));
		if (!outputFile.exists()) {
			try {
				outputFile.createNewFile();
				Log.i(TAG, "File Created");
			} catch (IOException e) {
				e.printStackTrace();
				Log.i(TAG, "File not Created");
			}
		}
//------------------------------------------------------------------------------------------

		ArrayList<String> numbersList = (ArrayList<String>) getIntent().getSerializableExtra("key");
		ArrayList<String> nameList = (ArrayList<String>) getIntent().getSerializableExtra("keyName");

		products = new ArrayList<>();
		productsList = new ArrayList<>();
		for (int j=0; j<numbersList.size();j++){
			products.add(Integer.valueOf(numbersList.get(j)));
			productsList.add(nameList.get(j));
		}

		productsNew = new ArrayList<>();
		for (int pro: products){
			if (!productsNew.contains(pro)){
				productsNew.add(pro);
			}
		}


		buttonclick();

		routes = new ArrayList<Integer>();
		if(productsNew.size()==5){
			routes.add(R.drawable.a_a);
			routes.add(R.drawable.a_a);
			routes.add(R.drawable.a_b);
			routes.add(R.drawable.b_c);
			routes.add(R.drawable.c_e);
			routes.add(R.drawable.d_e);

		}
		if(productsNew.size()==4){
			if (productsNew.get(0)==1) {
				routes.add(R.drawable.a_a);
				routes.add(R.drawable.a_a);
				if (productsNew.get(1)==2) {
					routes.add(R.drawable.a_b);
					if (productsNew.get(2)==3) {
						routes.add(R.drawable.b_c);
						if (productsNew.get(3)==4) {
							routes.add(R.drawable.c_d);
						}
						if (productsNew.get(3)==5) {
							routes.add(R.drawable.c_e);
						}

					}
					if (productsNew.get(2)==4) {
						routes.add(R.drawable.b_d);
						routes.add(R.drawable.d_e);

					}
				}
				if (productsNew.get(1)==3) {
					routes.add(R.drawable.a_c);
					routes.add(R.drawable.c_e);
					routes.add(R.drawable.d_e);
				}
			}
			if (productsNew.get(0)==2) {
				routes.add(R.drawable.b_b);
				routes.add(R.drawable.b_b);
				routes.add(R.drawable.b_c);
				routes.add(R.drawable.c_e);
				routes.add(R.drawable.d_e);
			}
		}
		if(productsNew.size()==3){
			if (productsNew.get(0)==1){
				routes.add(R.drawable.a_a);
				routes.add(R.drawable.a_a);
				if (productsNew.get(1)==2){
					routes.add(R.drawable.a_b);
					if (productsNew.get(2)==3) {
						routes.add(R.drawable.b_c);
					}
					if (productsNew.get(2)==4){
						routes.add(R.drawable.b_d);
					}
					if (productsNew.get(2)==5){
						routes.add(R.drawable.b_d);
					}
				}
				if (productsNew.get(1)==3){
					routes.add(R.drawable.a_c);
					if (productsNew.get(2)==4){
						routes.add(R.drawable.c_d);
					}
					if (productsNew.get(2)==5){
						routes.add(R.drawable.c_e);
					}
				}
				if (productsNew.get(1)==4){
					routes.add(R.drawable.a_d);
					routes.add(R.drawable.d_e);
				}
			}
			if (productsNew.get(0)==2){
				routes.add(R.drawable.b_b);
				routes.add(R.drawable.b_b);
				if (products.get(1)==3) {
					routes.add(R.drawable.b_c);
					if (products.get(2)==4) {
						routes.add(R.drawable.c_d);
					}
					if (products.get(2)==5) {
						routes.add(R.drawable.c_e);
					}
				}
				if (products.get(1)==4) {
					routes.add(R.drawable.b_d);
					routes.add(R.drawable.d_e);
				}
			}
			if (productsNew.get(0)==3){
				routes.add(R.drawable.c_c);
				routes.add(R.drawable.c_c);
				routes.add(R.drawable.c_e);
				routes.add(R.drawable.d_e);

			}
		}
		if(productsNew.size()==2) {
			if (productsNew.get(0) == 1) {
				routes.add(R.drawable.a_a);
				routes.add(R.drawable.a_a);
				if (productsNew.get(1) == 2) {
					routes.add(R.drawable.a_b);
				}
				if (productsNew.get(1) == 3) {
					routes.add( R.drawable.a_c);
				}
				if (productsNew.get(1) == 4) {
					routes.add(R.drawable.a_d);
				}
				if (productsNew.get(1) == 5) {
					routes.add(R.drawable.a_e);
				}
			}
			if (productsNew.get(0) == 2) {
				routes.add(R.drawable.b_b);
				routes.add(R.drawable.b_b);
				if (productsNew.get(1) == 3) {
					routes.add(R.drawable.b_c);
				}
				if (productsNew.get(1) == 4) {
					routes.add( R.drawable.b_d);
				}
				if (productsNew.get(1) == 5) {
					routes.add(R.drawable.b_e);
				}
			}
			if (productsNew.get(0) == 3) {
				routes.add(R.drawable.c_c);
				routes.add(R.drawable.c_c);
				if (productsNew.get(1) == 4) {
					routes.add(R.drawable.c_d);
				}
				if (productsNew.get(1) == 5) {
					routes.add(R.drawable.c_e);
				}
			}
			if (productsNew.get(0) == 4) {
				routes.add(R.drawable.d_d);
				routes.add(R.drawable.d_d);
				if (productsNew.get(1) == 5) {
					routes.add(R.drawable.d_e);
				}
			}
		}
		if(productsNew.size()==1){
			if (productsNew.get(0)== 1){
				routes.add(R.drawable.a_a);
				routes.add(R.drawable.a_a);
			}
			if (productsNew.get(0)== 2){
				routes.add(R.drawable.b_b);
				routes.add(R.drawable.b_b);
			}
			if (productsNew.get(0)== 3){
				routes.add(R.drawable.c_c);
				routes.add(R.drawable.c_c);
			}
			if (productsNew.get(0)== 4){
				routes.add(R.drawable.d_d);
				routes.add(R.drawable.d_d);
			}
			if (productsNew.get(0)== 5){
				routes.add(R.drawable.e_e);
				routes.add(R.drawable.e_e);
			}
		}
//------------------------------------------------------------------------------------

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//------------------------------------------------------------------------------------


//------------------------------------------------------------------------------------
	}

	public void buttonclick() {

		imageView = (ImageView) findViewById(R.id.route);
		button = (Button) findViewById(R.id.button4);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				current_image++;
				current_image = current_image % routes.size();
				imageView.setImageResource(routes.get(current_image));
			}
		});
	}
//------------------------------------------------------------------------------------------

	public void onClickFinish (View view){

		finishBtn();
	}

	public void finishBtn(){

		Intent intent = new Intent(this, ThankYou.class);
		intent.putExtra("keyName", productsList);
		startActivity(intent);

	}

//------------------------------------------------------------------------------------------

	public void start_stop(View v) throws IOException {
		Log.i(TAG, String.valueOf(ssidTextView.getText()));
		if (startButton.getText().equals("Start")) {
			scanForWifi();
			wifiScanHandler.postDelayed(wifiScan, INTERVAL_FOR_RSSI);
			startButton.setText("Stop");

			foo = new FileOutputStream(outputFile);

		} else if (startButton.getText().equals("Stop")) {
			startButton.setText("Start");
			xTextView.setText("0");
			yTextView.setText("0");
			foo.close();
		}

	}

	public void scanForWifi() {
		mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		mWifiManager.startScan();

	}

	public void wifiScanSuccess() throws IOException {
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		List<ScanResult> mWifiList = mWifiManager.getScanResults();
		boolean ssidfound = false;
		String ssid = getString(R.string.TheLastKnght97);
		String ssid2 = getString(R.string.Nokia3);
		String ssid3 = getString(R.string.SLT);
		for (ScanResult scanResult : mWifiList) {
			if (scanResult.SSID.equals(ssid)) {
				ssidfound = true;
			if (scanResult.level >= -42){
					d1 = 1;
				}
				if (scanResult.level < -43 && scanResult.level >= -48) {
					d1 = 2;
				}
				if (scanResult.level < -49 && scanResult.level >= -53) {
					d1 = 3;
				}
				if (scanResult.level < -54 && scanResult.level >= -56) {
						d1 = 4;
				}
				if (scanResult.level < -57) {
					d1 = 5;
				}
				/*if (scanResult.level < -60 && scanResult.level >= -65){
					d1 = 6;
				}
				if (scanResult.level < -65 && scanResult.level >= -70) {
					d1 = 7;
				}
				if (scanResult.level < -75 && scanResult.level >= -80) {
					d1 = 8;
				}
				if (scanResult.level < -80) {
					d1 = 9;
				}*/


				DateFormat df = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss.SSSZ");
				String date = df.format(Calendar.getInstance().getTime());

				String data = date + " " + scanResult.SSID + " " + scanResult.level + "\n";

				foo.write(data.getBytes());

				Log.i(TAG, data);
			}

			if (scanResult.SSID.equals(ssid2)) {
				ssidfound = true;
				if (scanResult.level >= -42){
					d2 = 1;
				}
				if (scanResult.level < -43 && scanResult.level >= -52) {
					d2 = 2;
				}
				if (scanResult.level < -53 && scanResult.level >= -55) {
					d2 = 3;
				}
				if (scanResult.level < -56 && scanResult.level >= -60) {
					d2 = 4;
				}
				if (scanResult.level < -61 && scanResult.level >= -65) {
					d2 = 5;
				}
				if (scanResult.level < -66){
					d2 = 6;
				}/*
				if (scanResult.level < -68 && scanResult.level >= -69.3333) {
					d2 = 7;
				}
				if (scanResult.level < -69.3333 && scanResult.level >= -75) {
					d2 = 8;
				}
				if (scanResult.level < -75) {
					d2 = 9;
				}*/

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss.SSSZ");
				String date = df.format(Calendar.getInstance().getTime());

				String data = date + " " + scanResult.SSID + " " + scanResult.level + "\n";

				foo.write(data.getBytes());

				Log.i(TAG, data);
			}

			if (scanResult.SSID.equals(ssid3)) {
				ssidfound = true;
				if (scanResult.level >= -35){
					d3 = 1;
				}
				if (scanResult.level < -36 && scanResult.level >= -46) {
					d3 = 2;
				}
				if (scanResult.level < -47 && scanResult.level >= -49) {
					d3 = 3;
				}
				if (scanResult.level < -50 && scanResult.level >= -53) {
					d3 = 4;
				}
				if (scanResult.level < -54 && scanResult.level >= -56) {
					d3 = 5;
				}
				if (scanResult.level < -57){
					d3 = 6;
				}/*
				if (scanResult.level < -68 && scanResult.level >= -69.3333) {
					d3 = 7;
				}
				if (scanResult.level < -69.3333 && scanResult.level >= -75) {
					d3 = 8;
				}
				if (scanResult.level < -75) {
					d3 = 9;
				}*/

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss.SSSZ");
				String date = df.format(Calendar.getInstance().getTime());
				String data = date + " " + scanResult.SSID + " " + scanResult.level + "\n";
				foo.write(data.getBytes());
				Log.i(TAG, data);
			}

			double x1=5,x2=10,x3= 0,y1=0,y2=5,y3=10;
		//	double A = 2*((x2-x1)*(y3-y2)-(y2-y1)*(x3-x2));

	//		int x = (int) (((y3-y2) * ((d1*d1) - (d2*d2) - (x1*x1) + (x2*x2) - (y1*y1) + (y2*y2)) - (y2-y1)*((d2*d2) - (d3*d3) - (x2*x2) + (x3*x3) - (y2*y2) + (y3*y3))) / A);
	//		int y = (int) (((x3-x2) * ((d1*d1) - (d2*d2) - (x1*x1) + (x2*x2) - (y1*y1) + (y2*y2)) - (x3-x2)*((d2*d2) - (d3*d3) - (x2*x2) + (x3*x3) - (y2*y2) + (y3*y3)))/ ((-1)*A));

			//double x =  ((y3-y2)*((d1*d1 - d3*d3 - x1*x1 +x3*x3 - y1*y1 + y3*y3) - (y3-y1)*(d2*d2 - d3*d3 - x2*x2 +x3*x3 - y2*y2 + y3*y3)) / A);
			//double y =  ((x3-x1)*((d2*d2 - d3*d3 - x2*x2 +x3*x3 - y2*y2 + y3*y3) - (x3-x2)*(d1*d1 - d3*d3 - x1*x1 +x3*x3 - y1*y1 + y3*y3)) / A);

		//	double x =  (y3 - y2) * ((d1*d1) - (d2*d2));
		//	double y =  (x3 - x2) * ((d1*d1) - (d2*d2));

			//double A = ((d2)*(d2)) - ((d1)*(d1)) + ((x1)*(x1)) - ((x2)*(x2)) + ((y1)*(y1)) - ((y2)*(y2));
			//double D = ((d3)*(d3)) - ((d2)*(d2)) + ((x2)*(x2)) - ((x3)*(x3)) + ((y2)*(y2)) - ((y3)*(y3));

			double A = (d2 * d2) - (d1 * d1) - 100;


			double D = (d3 * d3) - (d2 * d2) + 25;

		/*	double B = 2 * (x1 - x2);
			double E = 2 * (x2 - x3);

			double C = 2 * (y1 - y2);
			double F = 2 * (y2 - y3);*/

			double B = -10;
			double E = 20;

			double C = -10;
			double F = -10;

			double x = (D - A)/40;
			double y = (-1)*(D + A)/40;


			xTextView.setText(String.valueOf(x));
			yTextView.setText(String.valueOf(y));

		//	start();
			ImageView image1 = (ImageView) findViewById(R.id.pointer);

			// Update the image's X and Y coordinates
			image1.setX((float) (850 - x*85));

			image1.setY((float) (1080 - y*54));
		}
		if (ssidfound == false) {
			startButton.setText("Start");
			Log.i(TAG, ssid + " Not Found");
			foo.close();
			Toast.makeText(this, "SSID not Found", Toast.LENGTH_SHORT).show();
		}



	}


	public void permissions(MainActivity thisActivity, String[] requiredPermission) {
		LinkedList<String> premissionList = new LinkedList<>();
		for (int i = 0; i < requiredPermission.length; i++) {
			if (ContextCompat.checkSelfPermission(thisActivity,
					requiredPermission[i])
					!= PackageManager.PERMISSION_GRANTED) {
				premissionList.add(requiredPermission[i]);
			}
		}
		if (premissionList.size() != 0) {

			String[] temp = new String[premissionList.size()];
			premissionList.toArray(temp);
			ActivityCompat.requestPermissions(thisActivity,
					temp,
					111);
		}
	}


	@Override
	public void onRequestPermissionsResult(int requestCode,
	   String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case 111: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
				}
				return;
			}


			// other 'case' lines to check for other
			// permissions this app might request.
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(myBroadcastReceiver);
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(myBroadcastReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (outputFile.exists()) {
			if (outputFile.length() == 0) {
				outputFile.delete();

			}
		}

	}

	public void start(){

		Intent intent = new Intent(this, Map_Navigation.class);

		startActivity(intent);
	}

	public void finish_acti(){

		Intent intent = new Intent(this, ThankYou.class);

		startActivity(intent);
	}


}

class MyBroadcastReceiver extends BroadcastReceiver {

	private MainActivity mActivity;

	public MyBroadcastReceiver(MainActivity mActivity) {
		super();
		this.mActivity = mActivity;
	}

	@Override
	public void onReceive (Context context, Intent intent) {
		String key = intent.getAction();
		Log.i("_RSSI", key);
		if (mActivity.mWifiManager != null) {
			if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(key)) {
				boolean success = intent.getBooleanExtra(
						WifiManager.EXTRA_RESULTS_UPDATED, false);
				if (success) {
					Log.i("_RSSI", "Sucess");
					try {
						mActivity.wifiScanSuccess();
					} catch (IOException e) {
						e.printStackTrace();
					}

					}
				}
			}
		}
	}
