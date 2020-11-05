package edu.ucsb.cs156.spring.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.ucsb.cs156.student.Student;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;


// Bulk of code is from: https://www.baeldung.com/spring-boot-console-app

@SpringBootApplication
public class Main implements CommandLineRunner  {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String [] args) {
        LOG.info("STARTING THE APPLICATION");

        // For not starting webserver, see: https://www.baeldung.com/spring-boot-no-web-server

        SpringApplication app = new SpringApplication(Main.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        LOG.info("APPLICATION FINISHED");
    }

    /**
	 * This method will be executed after the application context is loaded and
	 * right before the Spring Application main method is completed.
	 */
	@Override
	public void run(String... args) throws Exception {
		if (args.length != 1) {
			usage();
			System.exit(1);
		}

		String filename = args[0];

        List<String> allLines = null;

        try {
            allLines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.err.println("ERROR: could not open file " + filename);
            System.err.println(e);
            System.exit(2);
        }

        ArrayList<Student> students = Student.linesToStudents(allLines);

		System.out.println("Students from file: ");
		Student.listStudents(students);


	}

	public void usage() {
		System.err.println("Usage: java -jar jarfile inputfile ");
		System.err.println("  where inputfile contains student data in .csv format");
	}

	



}