package travelsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import travelsafe.paypal.PPTest;

@SpringBootApplication
public class TravelsafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelsafeApplication.class, args);

		PPTest.paypalDetails();
	}
}
