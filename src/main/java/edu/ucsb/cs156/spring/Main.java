package edu.ucsb.cs156.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.ucsb.cs156.spring.repositories.StudentRepository;
import edu.ucsb.cs156.spring.entities.StudentEntity;

import edu.ucsb.cs156.student.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

// Bulk of code is from: https://www.baeldung.com/spring-boot-console-app

//   If the main has trouble finding the repos and/or entities,
//   you either need to ensure that the repos and entities are in a package
//   lower than the package for the main, OR you need to explicitly tell
//   it where to look with annotations such as these:

// @EnableJpaRepositories("edu.ucsb.cs156.spring")
// @EntityScan("edu.ucsb.cs156.spring")


@SpringBootApplication
public class Main implements CommandLineRunner  {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

    @Autowired
    StudentRepository studentRepository;

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

        storeStudents(students,studentRepository);

        Iterable<StudentEntity> seList = studentRepository.findAll();

        System.out.println("Students from database: ");
        for (StudentEntity se: seList) {
            System.out.println(se);
        }

    }
    
    public void storeStudents(List<Student> students, StudentRepository sr) {
        for (Student s: students) {
            StudentEntity se = new StudentEntity(s);
            sr.save(se);
        }
    }

	public void usage() {
		System.err.println("Usage: java -jar jarfile inputfile ");
		System.err.println("  where inputfile contains student data in .csv format");
	}


}