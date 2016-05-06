package com.example.staykozani;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class DetailsActivity extends Activity {
	private POI mySelectedPOI;
	private int currImage = 0;
	private static List<Integer> images = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//set content view AFTER ABOVE sequence (to avoid crash)
		setContentView(R.layout.activity_details);
		
		Intent intent = getIntent();
        final int choiceIntent2 = intent.getIntExtra(InformationActivity.EXTRA_POIchoice2,0); 
        mySelectedPOI=ChoiceActivity.myPOI.get(choiceIntent2);
        
        TextView name = (TextView) findViewById(R.id.detailedPOINameTextView);
        TextView description = (TextView) findViewById(R.id.detailedPOIDescriptionTextView);
        
        name.setText(mySelectedPOI.getName());
        String newDescription  = mySelectedPOI.getDescription() + "\n\n" + mySelectedPOI.getDetailedDescription();
        description.setText(newDescription);
        
        images.clear();
		TypedArray imagesArray = mySelectedPOI.getIcon();
		
		for (int i=0; i<imagesArray.length(); i++)
			images.add(imagesArray.getResourceId(i, 0));
		
		initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();
			
	}
	
	 private void initializeImageSwitcher() {
	        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.detailedPOIImageSwitcher);
	        imageSwitcher.setFactory(new ViewFactory() {
	            @Override
	            public View makeView() {
	                ImageView imageView = new ImageView(DetailsActivity.this);
	                return imageView;
	            }
	        });

	        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
	        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }

    private void setImageRotateListener() {
        final ImageButton rotatebutton = (ImageButton) findViewById(R.id.detailedPOINextButton);
        rotatebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == images.size()) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    private void setInitialImage() {
        setCurrentImage();
    }

    private void setCurrentImage() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.detailedPOIImageSwitcher);
        imageSwitcher.setImageResource(images.get(currImage));
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
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
