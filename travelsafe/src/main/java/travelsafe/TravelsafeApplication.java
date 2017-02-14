package travelsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TravelsafeApplication extends SpringBootServletInitializer {

	private static Class<TravelsafeApplication> applicationClass = TravelsafeApplication.class;

	/**
	 * Standard
	 * */
	public static void main(String[] args) {
		System.setProperty("drools.dialect.mvel.strict", "false");
		SpringApplication.run(TravelsafeApplication.class, args);
		System.out.println("APP IS RUNNING:[ https://localhost:8090/#/ ]");
	}

	/**
	 * Used when running as WAR
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}


}
