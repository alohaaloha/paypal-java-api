package travelsafe.paypal;

/**
 * Created by aloha on 22-Nov-16.
 */
public class PayPalConfig {

    // Replace with your application client ID and secret
    public static final String clientId = "AUGkOs8SimhCzUsv9ROkTCYtYupLCBfgqZYXjB_Y5zLSHNAysprJ4DinWoIK-oQCBxfMMiMvrQRHcJeR";
    public static final String clientSecret = "EBQlESla-JB-4SRq-jshJs9r80bmr00XTWU2--G3fXadGMIWh1qF6dmvU3Ge8i10LtHIVKVZ-5ruE_F0";

    //my app cancel and return(success) urls
    static String url = "https://localhost:8090/#/status";
    public static final String CANCEL_URL = url;
    public static final String RETURN_URL = url;

}
