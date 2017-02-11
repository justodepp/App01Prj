package android.app01prj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	boolean cercaUtente(String nomeUtente, String pwd, boolean soloUtente) throws FileNotFoundException{
		String nomeFileAccounts = getApplicationContext().getFilesDir().getPath() + 
				"/" + getString(R.string.nomeFileAccounts); 
		boolean utenteEsistente = false;
		File f = new File(nomeFileAccounts);
		
		if(f.exists()){ 
			//il file esiste, cerchiamo l'utente...
			FileReader fileLeggi = new FileReader(nomeFileAccounts);
			BufferedReader lettore = new BufferedReader(fileLeggi);
			String rigaletta;
			String[] datiAccount = null;
			
			//ricerca di una riga con quello user name
			try {
				while ((rigaletta = lettore.readLine()) != null && !utenteEsistente){
					datiAccount = rigaletta.split(" ");
					utenteEsistente = datiAccount[0].compareTo(nomeUtente)==0;
					
					if(!soloUtente)
							utenteEsistente = utenteEsistente && datiAccount[1].compareTo(pwd)==0;
				}
				lettore.close();
				fileLeggi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), "Creazione Fallita",
						Toast.LENGTH_LONG).show();
			}
		return utenteEsistente;		
	}
	
	public void onClickAccedi(View v) throws FileNotFoundException{
		// TODO Auto-generated method stub
		String nomeUtente ="", pwd ="";
		
		EditText nomeUtenteView = (EditText) findViewById(R.id.etUser);
		nomeUtente = nomeUtenteView.getText().toString();
		EditText pwdView = (EditText) findViewById(R.id.etPwd);
		pwd = pwdView.getText().toString();
		
		/*
		TextView nomeUtenteView = (TextView) findViewById(R.id.tvUser);
		nomeUtente = nomeUtenteView.getText().toString();
		TextView pwdView = (TextView) findViewById(R.id.tvPwd);
		pwd = pwdView.getText().toString();
		*/
		
		if(!cercaUtente(nomeUtente,pwd, false)){
			Toast.makeText(getApplicationContext(), "Account inesistente", 
					Toast.LENGTH_LONG).show();
		} else{
			Intent intent = new Intent(this, HomeActivity.class);
			Bundle b = new Bundle();
			b.putString("user", nomeUtente);
			intent.putExtras(b);
			startActivity(intent);
			/*
			Toast.makeText(getApplicationContext(), "Ben Tornato " + 
					nomeUtente + " ;)", Toast.LENGTH_LONG).show();
			*/
		}
	}
	
	public void onClickRegistrami(View v) {
		
		// TODO Auto-generated method stub
		String nomeUtente ="", pwd ="";
		
		EditText nomeUtenteView = (EditText) findViewById(R.id.etUser);
		nomeUtente = nomeUtenteView.getText().toString();
		EditText pwdView = (EditText) findViewById(R.id.etPwd);
		pwd = pwdView.getText().toString();
		
		/*
		TextView nomeUtenteView = (TextView) findViewById(R.id.tvUser);
		nomeUtente = nomeUtenteView.getText().toString();
		TextView pwdView = (TextView) findViewById(R.id.tvPwd);
		pwd = pwdView.getText().toString();
		*/
		
		try {
			if (cercaUtente(nomeUtente, pwd, true))
				Toast.makeText(getApplicationContext(), 
						"Nome utente già in uso , " + nomeUtente + " ...", 
						Toast.LENGTH_LONG).show();
			else{
			//aggiungiamo l'account
				String nomeFileAccounts = getApplicationContext().getFilesDir().getPath().toString() + 
								"/" +getString(R.string.nomeFileAccounts); 		  	
				FileWriter fileScrivi = new FileWriter(nomeFileAccounts,true);
				fileScrivi.append(nomeUtente+" "+pwd+"\n");
				fileScrivi.close();
					 
				Toast.makeText(getApplicationContext(), "Nuovo account registrato. Benvenuto , " + nomeUtente + "...", 
						Toast.LENGTH_LONG).show();
	 
			}
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
