package com.equipnet.imei;

//importazioni da Cordova 
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

//importazioni da Android
import android.content.Context;
import android.telephony.TelephonyManager;

//importazioni JSON 
import org.json.JSONArray;
import org.json.JSONException;


public class ImeiPlugin extends CordovaPlugin {

	//Definizione di una stringa costante per l'azione supportata

	public static final String ACTION_GET_PHONE_IMEI = "getImeiNumber";

	public TelephonyManager tm;

	public void initialize(CordovaInterface cordova, CordovaWebView) {
		super.initialize(cordova, webView);
		//Il plugin non ha accesso diretto al contesto dell'applicazione
		//e quindi dobbiamo arrivarci
		Context context = this.cordova.getActivity().getApplicationContext();
		//Inizializzazione dell'oggetto tm
		tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext)
			throws JSONException{
		try {
			(ACTION_GET_PHONE_IMEI.equals(action)) {
				callbackContext.success(tm.getDeviceId());
				return true;
			}

			//se non abbiamo un match con l'azione
			callbackContext.error("Invalid Action");
			return false;
		} catch (Exception e) {
			//oops abbiamo un problema
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}


	}

}
