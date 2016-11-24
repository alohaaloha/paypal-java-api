package travelsafe.paypal;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Created by aloha on 22-Nov-16.
 */
public class PayPalConfig {

    // Replace with your application client ID and secret
    public static final String clientId = "AcVvSVjCGp_I0xy-fV13D8NoYWkDW6bWxrvPOxPy69URyNxDD85r6mrI1wPV89XFSkUndn_58ssivW7V";
    public static final String clientSecret = "EOVApgSdq5HDal7f8YGADaYYAYSTy7MQ1_OwRzsaTK1_SPuCUL_mIstqsU0l9pk2qkumj4zuU20Xnb-i";

    //my app cancel and return(success) urls
    //TODO - replace it :D
    public static final String CANCEL_URL = "https://www.google.rs";
    public static final String RETURN_URL = "https://www.9gag.com";

}
