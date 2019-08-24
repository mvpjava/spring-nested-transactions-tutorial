package com.mvpjava.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main implements CommandLineRunner {	
	@Autowired UploaderService uploaderService;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		uploaderService.upload(buildTimedCommands());
		pauseApplication();
	}
	
	private List<String> buildTimedCommands(){
		List<String> sql = new ArrayList<>();
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('STAR_TRACKER MODE REBOOT', '2019-07-10T00:00:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('SAR_ANTENNA IMAGE_ON', '2019-07-10T11:22:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('SAR_ANTENNA STANDBY', '2019-07-10T11:29:00Z')");
		
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('STAR_TRACKER MODE OFF', '2019-07-10T12:00:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('SAR_ANTENNA IMAGE_OFF', '2019-07-10T12:22:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('SAR_ANTENNA STANDBY', '2019-07-10T13:29:00Z')");
		
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('XPNDR TX ON', '2019-07-10T14:00:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('XPNDR TX OFF', '2019-07-10T14:20:00Z')" );
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('SUN_SENSOR ON', '2019-07-10T14:29:00Z')");
		
		sql.add("INSERT INTO TIMED_COMMANDS (command, time_of_execution) VALUES ('MAG ON', '2019-07-10T14:30:00Z')");
		
		return sql;
	}
	
	/* Just keep application running in order to view H2 console */
	private void pauseApplication() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Press any key to stop application. Goto http://localhost:8080/h2-console URL=jdbc:h2:mem:satellitetest");
	 	scanner.nextLine();
	 	System.out.println("Application will close");
	 	scanner.close();
	 	System.exit(0);
	}
	

}
