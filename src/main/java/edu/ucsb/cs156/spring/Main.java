package edu.ucsb.cs156.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import edu.ucsb.cs156.spring.repositories.ArchivedCourseRepository;
import edu.ucsb.cs156.spring.services.UCSBAcademicCurriculumService;
import edu.ucsb.cs156.ucsbapi.academics.curriculums.v1.classes.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;

import org.springframework.context.annotation.PropertySource;
import com.mongodb.util.JSON;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import javax.annotation.PostConstruct;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

@PropertySource("file:secrets-localhost.properties")
@EnableMongoRepositories
@SpringBootApplication
public class Main implements CommandLineRunner  {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

    @Autowired
    ArchivedCourseRepository acr;

    // @Autowired MongoTemplate mongoTemplate;
    // MongoCollection<Document> collection;
    // @PostConstruct
    // public void init(){
    //     collection = mongoTemplate.getCollection("courses");
    // }

    @Autowired
    UCSBAcademicCurriculumService ucs;

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

        Mongo mongo = new Mongo("localhost", 27017);
        DB db = mongo.getDB("database");
        DBCollection collection = db.getCollection("courses");


        String yyyyq = args[0];
        int pagesize = 100;
        int numpages = ucs.getNumberPages(yyyyq, pagesize);
        System.out.println("numpages = " + numpages);
        for(int i = 0; i < numpages; i++){
            String json = ucs.getJSON(i, pagesize, yyyyq);
            CoursePage cp = CoursePage.fromJSON(json);
            for(Course c : cp.getClasses()){
                acr.save(c);
            }
        }
    }

	public void usage() {
		System.err.println("Usage: java -jar jarfile yyyyq ");
		System.err.println("  where yyyyq is the quarter to be updated");
    }

    public void updateCourse(Course c){
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(c.getCourseId()));
        query.addCriteria(Criteria.where("quarter").is(c.getQuarter()));

        Update update = new Update();
        //

        mongoOperation.upsert(query, update, Course.class);

        Course courseTest = mongoOperation.findOne(query, Course.class);
        System.out.println("courseTest - " + courseTest);
    }


}