package paypal.utils;

import java.util.HashMap;
import java.util.Map;

import com.paypal.base.ConfigManager;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

/**
 * 
 */
public class GenerateAccessToken { 

	
	/**
	 * Method: getAccessToken
	 * Author: madal
	 * 
	 * Description: 
	 *
	 * @return
	 * 
	 * String
	 */
	public static String getAccessToken() {

		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap.put("mode", "live");
		configurationMap=ConfigManager.getInstance().getConfigurationMap();
		String clientId = configurationMap.get("clientId");
		String clientSecret = configurationMap.get("clientSecret");
		OAuthTokenCredential merchantTokenCredential = new OAuthTokenCredential(clientId,clientSecret, configurationMap);
		
		try {
			return merchantTokenCredential.getAccessToken();
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
