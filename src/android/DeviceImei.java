package com.plugin.DeviceImei;

import java.util.TimeZone;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public class DeviceImei extends CordovaPlugin {
	
	public static final String TAG = "DeviceImei"; //Definizione della stringa che sarà disponibile a tutte le istanze della classe 
	
    public static String imei;
    
    /**
     * Costruttore.
     */
    public DeviceImei() {
    }
    
    /**
     * Settaggio del contesto del comando Può essere usato per prendere i file associati all'activity
     * 
     *
     * @param cordova contesto della main activity.
     * @param webView la CordovaWebView nella queale cordova e in esecuzione.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        
    }
    
    /*funzione execute che si connette al javascript */
    
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getImeiInfo")) {
            JSONObject r = new JSONObject();
            r.put("imei", this.getImei());
            callbackContext.success(r);
        }
        else {
            return false;
        }
        return true;
    }
    
    /* funzione per predere l'imei del dispositivo*/
    
    public String getImei() {
    	Telephonymanager telefonymanager = (telephonymManager)this.cordova.getActivity().getSystemService(context.TELEPHONY_SERVICE);
    	String imei = telephonyManager.getDeviceId();
    	return imei;
    
    }
    
}
