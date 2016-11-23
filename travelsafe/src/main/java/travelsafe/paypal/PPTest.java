package travelsafe.paypal;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Created by aloha on 22-Nov-16.
 */
public class PPTest {

   public static void paypalDetails() {

       // Set payment details
       Details details = new Details();
       details.setShipping("1");
       details.setSubtotal("5");
       details.setTax("1");

       System.out.println(">>> DETAILS");

       // Payment amount
       Amount amount = new Amount();
       amount.setCurrency("USD");
       // Total must be equal to sum of shipping, tax and subtotal.
       amount.setTotal("7");
       amount.setDetails(details);

       System.out.println(">>> AMMOUNT");

       // Transaction information
       Transaction transaction = new Transaction();
       transaction.setAmount(amount);
       transaction.setDescription("This is the payment transaction description.");

       System.out.println(">>> TRANSASCION");

       // Add transaction to a list
       List<Transaction> transactions = new ArrayList<>();
       transactions.add(transaction);

       // Add payer details
       Payer payer = new Payer();
       payer.setPaymentMethod("paypal");

       System.out.println(">>> PAYER");

       // Add payment details
       Payment payment = new Payment();
       payment.setIntent("sale");
       payment.setPayer(payer);
       payment.setTransactions(transactions);

       System.out.println(">>> PAYMENT");

       // Add redirect URLs
       RedirectUrls redirectUrls = new RedirectUrls();
       redirectUrls.setCancelUrl("http://localhost:3000/cancel");
       redirectUrls.setReturnUrl("http://localhost:3000/process");
       payment.setRedirectUrls(redirectUrls);

       System.out.println(">>> URLs");

       System.out.println(">>> CREATING PAYMENT...");
       // Create payment
       try {
           Payment createdPayment = payment.create(new PayPalConfig().context);
           System.out.println(createdPayment.toJSON());
           Iterator links = createdPayment.getLinks().iterator();
           while (links.hasNext()) {
               Links link = (Links) links.next();
               if (link.getRel().equalsIgnoreCase("approval_url")) {
                   // REDIRECT USER TO link.getHref()
                   System.out.println(">>> PAYMENT INIT SUCCESS!");
                   System.out.println("GO TO: "+ link.toJSON());

               }
           }
       } catch (PayPalRESTException e) {
           System.err.println(e.getDetails());
           System.out.println(">>> PAYMENT ERROR");
       }









   }

}
