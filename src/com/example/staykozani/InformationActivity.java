package com.example.staykozani;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends Activity {
	
	protected static final String EXTRA_POIchoice2 = "com.example.staykozani.POICHOICE2";
	private POI mySelectedPOI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.activity_information);
		
		Intent intent = getIntent();
        final int choiceIntent = intent.getIntExtra(ChoiceActivity.EXTRA_POIchoice1,0); 
        mySelectedPOI=ChoiceActivity.myPOI.get(choiceIntent);
        
        TextView name = (TextView) findViewById(R.id.POINameTextView);
		ImageView im = (ImageView) findViewById(R.id.POIImageView);
		TextView description = (TextView) findViewById(R.id.POIDescriptionTextView);
		Button bMap = (Button) findViewById(R.id.POIMapButton);
		Button bViewMore = (Button) findViewById(R.id.viewMoreButton);
		

		bMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<"
						+ mySelectedPOI.getLatitude() + ">,<"
						+ mySelectedPOI.getLongitude() + ">?q=<"
						+ mySelectedPOI.getLatitude() + ">,<"
						+ mySelectedPOI.getLongitude() + ">("
						+ ">" + "+"
						+ mySelectedPOI.getName() + ")"));
				startActivity(geoIntent);
				}
		});
		
		bViewMore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(InformationActivity.this,DetailsActivity.class);
				intent.putExtra(EXTRA_POIchoice2,choiceIntent);
				startActivity(intent);
			}
		});

		name.setText(mySelectedPOI.getName());
		TypedArray imagesArray = mySelectedPOI.getIcon();
		int resourceId = imagesArray.getResourceId(0, 0);
		im.setImageResource(resourceId);
		description.setText(mySelectedPOI.getDescription());
     


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.information, menu);
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
}
