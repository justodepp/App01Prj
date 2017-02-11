package android.app01prj;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener, OnLongClickListener {
	
	String us;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Intent i = getIntent();
		us = i.getStringExtra("user");
		Toast.makeText(getApplicationContext(), "Username passato: " + us, 
				Toast.LENGTH_LONG).show();
		
		//listener..
		((ImageView) findViewById(R.id.mondo)).setOnLongClickListener(this);
		((ImageView) findViewById(R.id.mondo)).setOnClickListener(this);
		((ImageButton) findViewById(R.id.ib1)).setOnClickListener(this); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_capitali:
				Intent intent = new Intent(this, CapitaliActivity.class);
				Bundle b = new Bundle();
				b.putString("user", us);
				intent.putExtras(b);
				startActivity(intent);			
				break;
			case R.id.action_fiumi:
				
				break;
			case R.id.action_laghi:
				
				break;
			case R.id.action_monumenti:
				
				break;

			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, ListaContattiActivity.class);
		startActivity(intent);
//		Cursor cursoreContatti = getContentResolver().
//				query(ContactsContract.Contacts.CONTENT_URI, 
//						null, null, null, null);
//		
//		while(cursoreContatti.moveToNext()){
//			int posizioneColonnaNome = cursoreContatti.
//					getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
//			String prossimoContatto = cursoreContatti.getString(posizioneColonnaNome);
//			
//			Toast.makeText(getApplicationContext(), prossimoContatto, 
//					Toast.LENGTH_LONG).show();
//		}
//		cursoreContatti.close();
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.ib1:
				Toast.makeText(getApplicationContext(), "Pressione BREVE sul bottone!", 
						Toast.LENGTH_LONG).show();
				break;
			case R.id.mondo:
				Toast.makeText(getApplicationContext(), "Pressione BREVE sul mondo", 
						Toast.LENGTH_LONG).show();
				break;
		}
		
	}
}
