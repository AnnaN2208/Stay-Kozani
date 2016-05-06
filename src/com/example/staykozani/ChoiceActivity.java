package com.example.staykozani;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceActivity extends Activity{
	
	public static List<POI> myPOI = new ArrayList<POI>();
	private int choice = 0;
	public static double myLatitude = 0;
	public static double myLongitude = 0;
	public final static String EXTRA_POIchoice1 = "com.example.staykozani.POICHOICE1";

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.activity_choice);
		
		Intent intent = getIntent();
        int choiceIntent = intent.getIntExtra(MenuActivity.EXTRA_choice,0);
        setChoice(choiceIntent);
        
        myPOI.clear();
        
        fillArraylist();
        
        LocationListener();   
        
        updateDistances(); 
	}



	private void fillArraylist() {
		TextView categoryTextView = (TextView)findViewById(R.id.categoryTextView);
        int type = getChoice();
		Resources res = getResources();
		
        switch (type) {
		case 1:
			categoryTextView.setText(R.string.monuments);
			Drawable img1 = getBaseContext().getResources().getDrawable( R.drawable.ic_columns );
			img1.setBounds( 0, 0, 60, 60 );
			categoryTextView.setCompoundDrawables( img1, null, null, null );
			
			String[] monuments = res.getStringArray(R.array.monuments_array);
			String[][] newMonuments = new String[monuments.length][];
			
			TypedArray drawablesArray11 = res.obtainTypedArray(R.array.agiosnikolaos_ic);
			TypedArray drawablesArray12 = res.obtainTypedArray(R.array.lassanis_ic);
			TypedArray drawablesArray13 = res.obtainTypedArray(R.array.laografiko_ic); 
			TypedArray[] drawablesArray1 = {drawablesArray11, drawablesArray12, drawablesArray13};
					
			
			//TypedArray[] drawablesArray1 = getResources().obtainTypedArray(R.array.laografiko_ic);
			//int resourceId11 = drawablesArray11.getResourceId(id, -1);
			for (int i=0; i<monuments.length; i++) 
		        newMonuments[i] = monuments[i].split("@");
			for (int i=0; i<monuments.length; i++)
				myPOI.add(new POI(newMonuments[i][0], 1,  Double.valueOf(newMonuments[i][1]), Double.valueOf(newMonuments[i][2]), newMonuments[i][3], newMonuments[i][4], newMonuments[i][5], drawablesArray1[i]));
			break;
		case 2:
			categoryTextView.setText(R.string.entertainment);
			Drawable img2 = getBaseContext().getResources().getDrawable( R.drawable.ic_quaver );
			img2.setBounds( 0, 0, 60, 60 );
			categoryTextView.setCompoundDrawables( img2, null, null, null );
			
			String[] entertainment = res.getStringArray(R.array.entertainment_array);
			String[][] newEntertainment = new String[entertainment.length][];
			TypedArray drawablesArray21 = res.obtainTypedArray(R.array.t26_ic);
			TypedArray drawablesArray22 = res.obtainTypedArray(R.array.mode_ic);
			TypedArray drawablesArray23 = res.obtainTypedArray(R.array.ts_ic); 
			TypedArray[] drawablesArray2 = {drawablesArray21, drawablesArray22, drawablesArray23};
					
			for (int i=0; i<entertainment.length; i++) 
		        newEntertainment[i] = entertainment[i].split("@");
			for (int i=0; i<entertainment.length; i++)
				myPOI.add(new POI(newEntertainment[i][0], 1, Double.valueOf(newEntertainment[i][1]), Double.valueOf(newEntertainment[i][2]), newEntertainment[i][3], newEntertainment[i][4], newEntertainment[i][5], drawablesArray2[i]));
			break;
		case 3:
			categoryTextView.setText(R.string.restaurants);
			Drawable img3 = getBaseContext().getResources().getDrawable( R.drawable.ic_restaurant );
			img3.setBounds( 0, 0, 60, 60 );
			categoryTextView.setCompoundDrawables( img3, null, null, null );
			
			String[] restaurants = res.getStringArray(R.array.restaurants_array);
			String[][] newRestaurants = new String[restaurants.length][];
			TypedArray drawablesArray31 = res.obtainTypedArray(R.array.torevithi_ic);
			TypedArray drawablesArray32 = res.obtainTypedArray(R.array.topelagos_ic);
			TypedArray drawablesArray33 = res.obtainTypedArray(R.array.trypokarydos_ic); 
			TypedArray[] drawablesArray3 = {drawablesArray31, drawablesArray32, drawablesArray33};
					
			for (int i=0; i<restaurants.length; i++) 
		        newRestaurants[i] = restaurants[i].split("@");
			for (int i=0; i<restaurants.length; i++)
				myPOI.add(new POI(newRestaurants[i][0], 1, Double.valueOf(newRestaurants[i][1]), Double.valueOf(newRestaurants[i][2]), newRestaurants[i][3], newRestaurants[i][4], newRestaurants[i][5], drawablesArray3[i]));
			break;
		case 4:
			categoryTextView.setText(R.string.accommodation);
			Drawable img4 = getBaseContext().getResources().getDrawable( R.drawable.ic_hotel );
			img4.setBounds( 0, 0, 60, 60 );
			categoryTextView.setCompoundDrawables( img4, null, null, null );
			
			String[] accommodation = res.getStringArray(R.array.accommodation_array);
			String[][] newAccommodation = new String[accommodation.length][];
			TypedArray drawablesArray41 = res.obtainTypedArray(R.array.elena_ic);
			TypedArray drawablesArray42 = res.obtainTypedArray(R.array.ermionio_ic);
			TypedArray drawablesArray43 = res.obtainTypedArray(R.array.aliakmon_ic); 
			TypedArray[] drawablesArray4 = {drawablesArray41, drawablesArray42, drawablesArray43};
					
			for (int i=0; i<accommodation.length; i++) 
		        newAccommodation[i] = accommodation[i].split("@");
			for (int i=0; i<accommodation.length; i++)
				myPOI.add(new POI(newAccommodation[i][0], 1, Double.valueOf(newAccommodation[i][1]), Double.valueOf(newAccommodation[i][2]), newAccommodation[i][3], newAccommodation[i][4], newAccommodation[i][5], drawablesArray4[i]));
			break;
		default: 
			TypedArray array = res.obtainTypedArray(R.array.agiosnikolaos_ic); 
			myPOI.add(new POI("Error!",0,0.0,0.0,"Error!","Error!","Error!",array));
        	break;
        }
	}
	
	
	
	public void updateDistances(){
		if (myLatitude == 0 || myLongitude == 0) {
			Toast.makeText(getBaseContext(), "Searching for GPS..", Toast.LENGTH_LONG).show();
		} else {
			for (POI p : myPOI) 
				p.setDistance(calcDistance(p.getLatitude(),p.getLongitude(),myLatitude,myLongitude));
			Collections.sort(myPOI, comparePOIs);
		}
		deleteRows();
		addRows(); 
		
	}
	
	
	
	private void LocationListener() {
		LocationManager locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );        
        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);
        
    }
	
	private class MyLocationListener implements LocationListener {
	
		@Override
		public void onLocationChanged(Location location) {
			
			myLatitude = location.getLatitude();
			myLongitude = location.getLongitude();
		  		
			for (POI p : myPOI) 
				p.setDistance(calcDistance(p.getLatitude(),p.getLongitude(),location.getLatitude(),location.getLongitude()));
	
			Collections.sort(myPOI, comparePOIs);
			
			deleteRows();
			addRows(); 
	
		}
		
		@Override
		public void onProviderDisabled(String provider) {
		    Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
		}
		
		@Override
		public void onProviderEnabled(String provider) {
		    Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
		}
		
		@Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	        
	    }
	}
	
	private void deleteRows() {
		
		final TableLayout tl = (TableLayout)findViewById(R.id.choicesTableLayout);
		tl.removeAllViews();
	}

	private void addRows() {
		
		final TableLayout tl = (TableLayout)findViewById(R.id.choicesTableLayout);
		
		for(int i = 0; i < myPOI.size(); i++)
		{
		    TableRow row = (TableRow)LayoutInflater.from(ChoiceActivity.this).inflate(R.layout.row, null);
		    ((TextView)row.findViewById(R.id.choice1Button)).setText(myPOI.get(i).getName());
		    ((Button)row.findViewById(R.id.choice1Button)).setTag(i);
		    tl.addView(row);
		    ((Button)row.findViewById(R.id.choice1Button)).setOnClickListener(onclicklistener);
		}
		tl.requestLayout(); 
			
	}
	
	OnClickListener onclicklistener = new OnClickListener() {

	    @Override
	    public void onClick(View v) {	    
	    	Button btn = (Button) v;
	    	if (myLatitude != 0 && myLongitude != 0) {
		    	String message = "Βρίσκεστε " + myPOI.get((Integer) btn.getTag()).getDistance()+ "m μακριά από " + myPOI.get((Integer) btn.getTag()).getName();
				Toast.makeText(ChoiceActivity.this, message,Toast.LENGTH_SHORT).show();
	    	}else{
	    		String message = "Δεν έγινε εντοπισμός θέσης. Άγνωστη απόσταση από " + myPOI.get((Integer) btn.getTag()).getName();
				Toast.makeText(ChoiceActivity.this, message,Toast.LENGTH_SHORT).show();
	    	}
			Intent intent = new Intent(ChoiceActivity.this,InformationActivity.class);
			intent.putExtra(EXTRA_POIchoice1,(Integer)btn.getTag());
			startActivity(intent);
	    }
	};

	
	public static Comparator<POI> comparePOIs = new Comparator<POI>() {
        @Override
        public int compare(POI p1, POI p2) {
            return (int) (p1.getDistance() - p2.getDistance());
        }
	};


	private int calcDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		
		Location location1 = new Location("One POI");
		location1.setLatitude(latitude1);
		location1.setLongitude(longitude1);

		Location location2 = new Location("Μy location");
		location2.setLatitude(latitude2);
		location2.setLongitude(latitude2);
		
		float[] results = new float[1];
		Location.distanceBetween (latitude1, longitude1, latitude2, longitude2, results);
		float distanceInMeters = results[0];
		int distance = (int) distanceInMeters;
		return distance;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}
}
