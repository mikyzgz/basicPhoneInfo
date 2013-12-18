package com.miky.phonegap;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;

public class PhoneInfoPlugin extends CordovaPlugin{
	
	public static final String OBTENER_INFO_ACCION = "OBTENER_INFO_ACCION";
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		boolean resultado = false;
		try{
			if(OBTENER_INFO_ACCION.equals(action)){
				JSONObject jsonSuccess = this.obtenerInfoTelefonoImpl();
				// Para devolver directamente el valor cuando es correcto descomentar abajo
				// callbackContext.success(jsonSuccess);				
				// Para realizarlo de forma generica tradicional, continuar con lo de abajo
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, jsonSuccess));	
			}else{
				throw new IllegalArgumentException(action + "no soportada.");
			}			
			resultado = true;
		}catch (Throwable exc){
			JSONObject jsonERROR = new JSONObject("{\"mensaje\":\""+ exc.getMessage() + "\"}");
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, jsonERROR));
		}		
		return resultado;		
	}

	private JSONObject obtenerInfoTelefonoImpl() throws JSONException {
		TelephonyManager manager = (TelephonyManager) super.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		String numero = manager.getLine1Number(); // numero de telefono
		String imei = manager.getDeviceId();	 // imei
		String imsi = manager.getSubscriberId(); // numero único asociado al telefono a nivel mundial

		String jsonString = "{'numero' : '{0}', 'imei' : '{1}', 'imsi' : '{2}'}"; // previo y luego remplazamos
		jsonString = jsonString.replaceAll("'", "\"")
				.replace("{0}", numero!=null?numero:"")
				.replace("{1}", imei!=null?imei:"")
				.replace("{2}", imsi!=null?imsi:"");
		
		return new JSONObject(jsonString);
	}
	
}
