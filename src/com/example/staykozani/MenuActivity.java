package com.example.staykozani;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MenuActivity extends Activity {
	
	public final static String EXTRA_choice = "com.example.shapeorcolor.CHOICE";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//set content view AFTER ABOVE sequence (to avoid crash)
		this.setContentView(R.layout.activity_menu);
		
		final Button monumentsButton = (Button) findViewById(R.id.monumentsButton);
		final Button entertainmentButton = (Button) findViewById(R.id.entertainmentButton);
		final Button restaurantButton = (Button) findViewById(R.id.restaurantButton);
		final Button accommodationButton = (Button) findViewById(R.id.accommodationButton);
		
		final Intent intent = new Intent(MenuActivity.this, ChoiceActivity.class);
		
		monumentsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monumentsButton.setPressed(true);
				intent.putExtra(EXTRA_choice, 1 );
				startActivity(intent);
			}
		});
		
		entertainmentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				entertainmentButton.setPressed(true);
				intent.putExtra(EXTRA_choice, 2 );	
				startActivity(intent);
			}
		});
		
		restaurantButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				restaurantButton.setPressed(true);
				intent.putExtra(EXTRA_choice, 3 );
				startActivity(intent);
			}
		});
		
		accommodationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				accommodationButton.setPressed(true);
				intent.putExtra(EXTRA_choice, 4 );
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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
