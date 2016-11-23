package travelsafe.paypal;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Created by aloha on 22-Nov-16.
 */
public class PayPalConfig {

    // Replace with your application client ID and secret
    String clientId = "AcVvSVjCGp_I0xy-fV13D8NoYWkDW6bWxrvPOxPy69URyNxDD85r6mrI1wPV89XFSkUndn_58ssivW7V";
    String clientSecret = "EOVApgSdq5HDal7f8YGADaYYAYSTy7MQ1_OwRzsaTK1_SPuCUL_mIstqsU0l9pk2qkumj4zuU20Xnb-i";

    public APIContext context = new APIContext(clientId, clientSecret, "sandbox");

}
