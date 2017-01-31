package travelsafe.paypal;

import com.paypal.api.payments.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.TravelInsurance;
import travelsafe.service.impl.PriceCalculatorService;
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

    @Autowired
    PriceCalculatorService priceCalculatorService;
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

        /* [1] calculate final price and save entity do DB*/
        Double price = priceCalculatorService.calculatePrice(travelInsurance);

        travelInsurance.setMaxAmount(price);

        TravelInsurance savedTravelInsurance = travelInsuranceService.save(travelInsurance);
        System.out.println("SAVED with ID:");
        System.out.println(savedTravelInsurance.getId());

        /* [2] get paypal link - create payment*/
        Links links = payPalService.createPayment(savedTravelInsurance.getId(), travelInsurance.getMaxAmount(), 0, travelInsurance.getMaxAmount(), "Travel Insurance Package by Travel Safe, Inc.");

        /* [3] pack data to response*/
        HashMap<String, Object> response = new HashMap<>();
        response.put("link", links);
        response.put("item", savedTravelInsurance);

        /* [4]return packed data*/
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * When users is redirected to our website to confirm payment (execute it) or cancel it (delte order from DB)
     * Same url in both cases - angular opusteno :D
     * @see PayPalService
     * */
    @RequestMapping(value = "/paypal/execute/{orderId}/{paymentId}/{payerId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity executePayment(@PathVariable Long orderId, @PathVariable String paymentId, @PathVariable String payerId) {

        // TODO - save paymentId inside TravelInsurance object
        //travelInsuranceService.getById(orderId).setPaymentId(paymentId);

        boolean status = payPalService.executePayment(payerId, paymentId);
        if(status){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
