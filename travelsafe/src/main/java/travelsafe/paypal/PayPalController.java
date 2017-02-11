package travelsafe.paypal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paypal.api.payments.Links;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelsafe.model.CarInsurance;
import travelsafe.model.HomeInsurance;
import travelsafe.model.ParticipantInInsurance;
import travelsafe.model.TravelInsurance;
import travelsafe.repository.CarInsuranceRepository;
import travelsafe.repository.HomeInsuranceRepository;
import travelsafe.repository.ParticipantInInsuranceRepository;
import travelsafe.repository.TravelInsuranceRepository;
import travelsafe.service.impl.PriceCalculatorService;
import travelsafe.service.impl.TravelInsuranceService;

import java.util.HashMap;

/**
 * Created by aloha on 27-Nov-16.
 */
@RestController
@RequestMapping("/api")
public class PayPalController {

    private static final Logger LOG = LoggerFactory.getLogger(PayPalController.class);

    @Autowired
    ParticipantInInsuranceRepository participantInInsuranceRepository;

    @Autowired
    HomeInsuranceRepository homeInsuranceRepository;

    @Autowired
    CarInsuranceRepository carInsuranceRepository;


    @Autowired
    TravelInsuranceService travelInsuranceService;

    @Autowired
    TravelInsuranceRepository travelInsuranceRepository;

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
    public ResponseEntity createPayment(@RequestBody String stringTravelInsurance) {

        LOG.debug("Request for creating payment for travel insurance {}", stringTravelInsurance);

        TravelInsurance  travelInsurance = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String cleanedStringTravelInsurance = Jsoup.clean(stringTravelInsurance, Whitelist.basic());

        if (!stringTravelInsurance.equals(cleanedStringTravelInsurance)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Parse json object to appropriate java object
        try {
            travelInsurance = gson.fromJson(cleanedStringTravelInsurance, TravelInsurance.class);
        }
        catch (Exception e) {
            LOG.debug("Error during parsing json for travel insurance: {}", e);
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LOG.debug("Request for creating payment for travel insurance with {} ID",travelInsurance.getId());

        if (travelInsurance.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            /* [1] calculate final price and save entity do DB*/

            Double calculatedTotalPrice = priceCalculatorService.calculate(travelInsurance);

            travelInsurance.setTotalPrice(calculatedTotalPrice);

            TravelInsurance savedTravelInsurance = travelInsuranceService.save(travelInsurance);


            //----------------------------------------------------------------------------------
            for(ParticipantInInsurance pi: travelInsurance.getParticipantInInsurances()){
                pi.setTravelInsurance(travelInsurance);
            }
            participantInInsuranceRepository.save(travelInsurance.getParticipantInInsurances());
            for(CarInsurance ci: travelInsurance.getCarInsurances()){
                ci.setTravelInsurance(travelInsurance);
            }
            carInsuranceRepository.save(travelInsurance.getCarInsurances());
            for(HomeInsurance hi: travelInsurance.getHomeInsurances()){
                hi.setTravelInsurance(travelInsurance);
            }
            homeInsuranceRepository.save(travelInsurance.getHomeInsurances());
            //-----------------------------------------------------------------------------------


            LOG.debug("Saved with {} ID", savedTravelInsurance.getId());

            /* [2] get paypal link - create payment*/
            Links links = payPalService.createPayment(savedTravelInsurance.getId(), 100, 0, 100, "Travel Insurance Package by Travel Safe, Inc.");

            /* [3] pack data to response*/
            HashMap<String, Object> response = new HashMap<>();
            response.put("link", links);
            response.put("item", savedTravelInsurance);

            /* [4]return packed data*/
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

        LOG.debug("Execute payment with {} order ID, {} payment ID and {} payer ID.");

        if (!paymentId.equals(Jsoup.clean(paymentId, Whitelist.basic())) || !payerId.equals(Jsoup.clean(payerId, Whitelist.basic()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TravelInsurance order = travelInsuranceRepository.getOne(orderId);

        //order exists
        //has not payed yet
        //order and payment are matching combo
        if(order!=null && order.getPaypalPaymentId()==null && payPalService.checkOrderAndPaymentCombo(orderId, paymentId)){

            boolean status = payPalService.executePayment(payerId, paymentId);
            LOG.debug("Status of payment {}",status);
            if(status){
                //update order
                order.setPaypalPaymentId(paymentId);
                travelInsuranceRepository.save(order);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




    @RequestMapping(value = "/paypal/cancel/{orderId}/{paymentId}/{payerId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity cancelPaymet(@PathVariable Long orderId, @PathVariable String paymentId, @PathVariable String payerId) {

        LOG.debug("Cancel payment with {} order ID, {} payment ID and {} payer ID.");

        TravelInsurance order = travelInsuranceRepository.getOne(orderId);

        //order exists
        //has not payed yet
        //order and payment are matching combo
        if(order!=null && order.getPaypalPaymentId()==null && payPalService.checkOrderAndPaymentCombo(orderId, paymentId)){
                travelInsuranceRepository.delete(orderId);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
