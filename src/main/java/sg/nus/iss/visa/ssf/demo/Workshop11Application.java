package sg.nus.iss.visa.ssf.demo;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop11Application {

	//added code for logging
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);

	//default port number
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		logger.info("Main method started...");
		SpringApplication.run(Workshop11Application.class, args);

		//initialise the spring app for running, not actually running it yet.
		SpringApplication app = new SpringApplication(Workshop11Application.class);

		//read args array and check if port is free
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		//return a list of all keyvalue pairs in the args aray upon exec 
		//from cmd prompt and check for any holding a 'port' parameter 
		List opsValues = appArgs.getOptionValues("port");

		String portNumber = null;

			//this is run if no param not passed at command line, or if on checking get[0] it returns a zero value
			//instead of our decided port number which would then have been part of get[0]???
			if(opsValues == null || opsValues.get(0) == null){

				//Read port number from environment variables if port number
				//returns zero
				portNumber = System.getenv("PORT");

				//Read port number from default port if it returned null
				if(portNumber == null){
					portNumber = DEFAULT_PORT;
				}


			} else {

				portNumber = (String) opsValues.get(0);

			}

			if (portNumber != null){

				//setting port number in the spring-boot config
				app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));

			}

			logger.info("Port number is: " + portNumber);

		//args since we are passing the port number as part of execution arguments when we run the program
		app.run(args);

	}

}
