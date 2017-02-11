package android.app01prj;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ListaContattiActivity extends Activity implements OnItemClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_contatti);
		
		String[] nomi = new String[]{"Fabio", "Gianni", "Luca"};
		ArrayList<String> sorgenteDatiPerListView = new ArrayList<String>();
		for(int i=0; i < nomi.length; i++){
			sorgenteDatiPerListView.add(nomi[i]);
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1, sorgenteDatiPerListView);
		
		ListView contattiLV = (ListView) findViewById(R.id.listaContatti);
		contattiLV.setAdapter(adapter);
		contattiLV.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		String contatto = (String) parent.getItemAtPosition(position);
		
		//show alert
		Toast.makeText(getApplicationContext(), "Posizione: "+position+" Contatto: " + id, 
				Toast.LENGTH_LONG).show();
	}
}
