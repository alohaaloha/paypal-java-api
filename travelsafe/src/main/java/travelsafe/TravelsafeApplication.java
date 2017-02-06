package travelsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class TravelsafeApplication extends SpringBootServletInitializer {

	private static Class<TravelsafeApplication> applicationClass = TravelsafeApplication.class;

	/**
	 * Used when run as JAR
	 * */
	public static void main(String[] args) {
		SpringApplication.run(TravelsafeApplication.class, args);

		//https how to: https://www.drissamri.be/blog/java/enable-https-in-spring-boot/
		System.out.println("APP IS RUNNING:[ https://localhost:8090/#/ ]");
	}

	/**
	 * Used when run as WAR
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
