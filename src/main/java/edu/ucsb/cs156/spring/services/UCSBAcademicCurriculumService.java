package edu.ucsb.cs156.spring.services;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import edu.ucsb.cs156.ucsbapi.academics.curriculums.v1.classes.CoursePage;

/**
 * Service object that wraps the UCSB Academic Curriculum API
 */
@Service
public class UCSBAcademicCurriculumService implements CurriculumService {

    private Logger logger = LoggerFactory.getLogger(UCSBAcademicCurriculumService.class);

    private String apiKey;

    public UCSBAcademicCurriculumService(@Value("${ucsb.api.consumer_key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCSV(String subjectArea, String quarter, String courseLevel, String dept, String instructor, String course, String college, String areas){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format("?quarter=%s&", quarter);

        if(subjectArea != null ){
            params += String.format("subjectCode=%s&", subjectArea);
        }

        if(courseLevel != null){
            params += String.format("objLevelCode=%s&", courseLevel);
        }

        if(dept != null){
            params += String.format("subjectCode=%s&", dept);
        }

        if(instructor != null){
            params += String.format("instructor=%s&", instructor);
        }

        if(course != null){
            params += String.format("courseId=%s&", course);
        }

        if(college != null){
            params += String.format("college=%s&", college);
        }

        if(areas != null){
            params += String.format("areas=%s&", areas);
        }

        params += String.format("pageNumber=%d&pageSize=%d&includeClassSections=%s", 1, 100, "true");
        String url = uri + params;

        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }

    public String getJSON(String subjectArea, String quarter, String courseLevel) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?quarter=%s&subjectCode=%s&objLevelCode=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s", quarter,
                subjectArea, courseLevel, 1, 100, "true");
        String url = uri + params;

        if (courseLevel.equals("A")) {
            params = String.format(
                    "?quarter=%s&subjectCode=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                    quarter, subjectArea, 1, 100, "true");
            url = uri + params;
        }

        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }

    public String getJSON(String instructor, String quarter) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format("?quarter=%s&instructor=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                quarter, instructor, 1, 100, "true");
        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }

    public String getJSON(String quarter) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?quarter=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s", quarter, 1, 100, "true");
        String url = uri + params;

        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }

    public String getJSON(int pagenum, int pagesize, String yyyyq) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?quarter=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s", yyyyq, pagenum, pagesize, "true");
        String url = uri + params;

        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }



    /**
     * Gets the json response for a query for a given course in a given quarter.
     * 
     * @param course  name of course, e.g. "CMPSC 130A"
     * @param quarter quarter as an integer, e.g. 20201 for W20
     * @return json results from API
     */

    public String getCourse(String course, int quarter) {
        logger.info("getCourse: course: " + course + " quarter: " + quarter);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format("?quarter=%d&courseId=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                quarter, course, 1, 100, "true");

        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
	    logger.info("re=" + re);
            MediaType contentType = re.getHeaders().getContentType();
	    logger.info("contenttype=" + contentType);
            HttpStatus statusCode = re.getStatusCode();
	    logger.info("statuscode=" + statusCode);
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }


    public String getGE(String college, String areas, String quarter) {
        logger.info("getGE: college: " + college + " areas: " + areas +" quarter: " + quarter);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?college=%s&areas=%s&quarter=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                college, areas, quarter, 1, 100, "true");
        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }

    public String getGE(String college, String areas, String quarter, int startT) {
        logger.info("getGE: college: " + college + " areas: " + areas +" quarter: " + quarter + " Start time: "+ startT);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?college=%s&areas=%s&quarter=%s&minStartTime=%d&maxStartTime=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                college, areas, quarter, startT, startT, 1, 100, "true");
        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }


    public String getGE(String college, String areas, String quarter, String days) {
        logger.info("api: " + apiKey);
        logger.info("getGE: college: " + college + " areas: " + areas +" quarter: " + quarter + " Days: "+ days);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
  
        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?college=%s&areas=%s&quarter=%s&days=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s",
                college, areas, quarter, days, 1, 100, "true");
  
        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
  
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        return retVal;
    }
  
    public String getFinalExam(String quarter, String enrollCode) {
        logger.info("getFinalExam: quarter: " + quarter + " enrollCode: " + enrollCode);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
      
        String uri = "https://api.ucsb.edu/academics/curriculums/v1/finals";
        String params = String.format(
                "?quarter=%s&enrollCode=%s",
                quarter, enrollCode);
        String url = uri + params;
        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getFinalExam: " + retVal);
        return retVal;
    }

    // def getNumberPages(quarter="20202"):
    // url = f"https://api.ucsb.edu/academics/curriculums/v1/classes/search?quarter={quarter}&pageNumber=1&pageSize={pageSize}&includeClassSections=true" 

    // # > json_data/results_${i}.json

    // r = requests.get(url, headers=headers)
    // if r.status_code != 200:
    //     raise Exception("Bad status "+r.status_code)
    // return math.ceil(r.json()['total']/pageSize)

    public int getNumberPages(String yyyyq, int pagesize){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("ucsb-api-version", "1.0");
        headers.set("ucsb-api-key", this.apiKey);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        String uri = "https://api.ucsb.edu/academics/curriculums/v1/classes/search";
        String params = String.format(
                "?quarter=%s&pageNumber=%d&pageSize=%d&includeClassSections=%s", yyyyq, 1, pagesize, "true");
        String url = uri + params;

        logger.info("url=" + url);

        String retVal = "";
        try {
            ResponseEntity<String> re = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            MediaType contentType = re.getHeaders().getContentType();
            HttpStatus statusCode = re.getStatusCode();
            retVal = re.getBody();
        } catch (HttpClientErrorException e) {
            retVal = "{\"error\": \"401: Unauthorized\"}";
        }
        logger.info("from UCSBAcademicCurriculumService.getJSON: " + retVal);
        //parse JSON to get out the value of Total
        CoursePage cp = CoursePage.fromJSON(retVal);
        int total = cp.getTotal();
        return (int)Math.ceil(total/pagesize);
    }
}