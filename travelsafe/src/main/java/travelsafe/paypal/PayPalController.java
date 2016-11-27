package travelsafe.paypal;

import com.paypal.api.payments.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import travelsafe.model.TravelInsurance;
import travelsafe.paypal.PayPalService;
import travelsafe.service.impl.TravelInsuranceService;

import java.util.HashMap;

/**
 * Created by aloha on 27-Nov-16.
 */
@RestController
@RequestMapping("/api")
public class PayPalController {

    @Autowired
    TravelInsuranceService travelInsuranceService;

    @Autowired
    PayPalService payPalService;


    /**
     * When user clicks on BUY ITEM button
     * @see PayPalService
     * */
    @RequestMapping(value = "/paypal/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPayment(@RequestBody TravelInsurance travelInsurance) {

        if (travelInsurance.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        System.out.println("IN:");
        System.out.println(travelInsurance.getDuration());

        //TODO - calculate final price and set it to 'travelInsurance' before saving it to DB
        //travelInsurance.setAmount({CALCULATED_PRICE});
        TravelInsurance savedTravelInsurance = travelInsuranceService.save(travelInsurance);
        System.out.println("SAVED:");
        System.out.println(savedTravelInsurance.getId());

        Links links = payPalService.createPayment(savedTravelInsurance.getId(), 100, 0, 100, "Travel Insurance Package by Travel Safe, Inc.");

        HashMap<String, Object> response = new HashMap<>();
        response.put("links", links);
        response.put("item", savedTravelInsurance);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //http://stackoverflow.com/questions/18543958/paypal-rest-api-how-to-retrieve-payment-id-after-user-has-approved-the-payment

    /**
     * When users is redirected to our website - confirm payment (execute it)
     * @see PayPalService
     * */
    @RequestMapping(value = "/paypal/execute",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity executePayment(@RequestBody TravelInsurance travelInsurance) {

        //TODO

        return null;
    }




}
