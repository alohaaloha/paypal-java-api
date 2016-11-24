package travelsafe.paypal;

import com.paypal.base.rest.APIContext;

import static travelsafe.paypal.PayPalConfig.clientId;
import static travelsafe.paypal.PayPalConfig.clientSecret;

/**
 * Created by aloha on 23-Nov-16.
 */
public class PayPalContext {

    //TODO - what to use? new context evey time or always the same one

    public static APIContext context = new APIContext(clientId, clientSecret, "sandbox");

    public static APIContext getAPIContext(){
        return new APIContext(clientId, clientSecret, "sandbox");
    }

}
