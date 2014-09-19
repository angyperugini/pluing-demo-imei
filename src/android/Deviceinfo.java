package com.equipnet.plugin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;


public class DeviceInfo extends CordovaPlugin{

	public DeviceInfo(){} //Costruttore
	
	
	public String DeviceImeiNumber(){
		TelephonyManager tManager= (TelephonyManager)cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();

	}
	
	
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("imeiNumber")) {
            callbackContext.success(this.DeviceImeiNumber());
            return true;
        } 
        else {
            return false;
        }
    } 
}
	
