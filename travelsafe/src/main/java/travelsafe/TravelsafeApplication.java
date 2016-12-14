package travelsafe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TravelsafeApplication {



	public static void main(String[] args) {
		SpringApplication.run(TravelsafeApplication.class, args);
		System.out.println("APP IS RUNNING: "+ "http://localhost:8090/#/");
	}

}
