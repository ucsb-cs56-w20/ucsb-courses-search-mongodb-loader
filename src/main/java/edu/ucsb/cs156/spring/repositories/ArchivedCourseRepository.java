package edu.ucsb.cs156.spring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ucsb.cs156.ucsbapi.academics.curriculums.v1.classes.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchivedCourseRepository extends MongoRepository<Course, ObjectId> {
    /**
     * Returns a {@link Course} identified by that quarter it is offered and its 13 character course id.
     * <br>
     * Note: the courseId must be a properly padded 13 character course id. If you have the course's subject code
     * (e.g. CMPSC) and course number (e.g. 190J), use the overloaded version of this method instead.
     * {@link #findOneByQuarterAndCourseId(String, String, String)}
     *
     * @param quarter  the quarter that the course is offered
     * @param courseId the course's 13 character course id
     * @return an optional {@link Course}, if a matching course was found
     * @see #findOneByQuarterAndCourseId(String, String, String)
     */
    Optional<Course> findOneByQuarterAndCourseId(String quarter, String courseId);

    /**
     * Returns a {@link Course} identified by the quarter that it is offered, its subject code, and its course
     * number.
     * <br>
     * This is a convenience method that calls {@link #findOneByQuarterAndCourseId(String, String)} with a properly
     * padded course id.
     *
     * @param quarter      the quarter that the course is offered
     * @param subjectCode  the course's subject code
     * @param courseNumber the course's course number
     * @return an optional {@link Course}, if a matching course was found
     * @see #findOneByQuarterAndCourseId(String, String)
     */
    default Optional<Course> findOneByQuarterAndCourseId(String quarter,
                                                                 String subjectCode,
                                                                 String courseNumber) {
        return findOneByQuarterAndCourseId(quarter, String.format("%-8s%-5s", subjectCode, courseNumber));
    }

    /**
     * Returns a list of {@link Course} where at least one of the course's lectures/sections takes place in the
     * specified building and room.
     *
     * @param quarter  the quarter that the course is offered
     * @param building the building that the lecture/section is held in
     * @param room     the room of the building that the lecture/section is held in
     * @return a list of matching {@link Course}
     */
    @Query("{'quarter': ?0, 'classSections': {'$elemMatch': {'timeLocations': {'$elemMatch': {'building': ?1, 'room': ?2}}}}}")
    List<Course> findByQuarterAndRoom(String quarter, String building, String room);

    /**
     * Returns a list of {@link Course} occurring between specified quarters
     * where at least one of the course's instructors matches the text.
     *
     * @param startQuarter   the first quarter to consider in the search formatted in YYYYQ
     * @param endQuarter     the final quarter to consider in the search formatted in YYYYQ
     * @param instructorText instructor name search query
     * @return a list of matching {@link Course}
     */
    @Query("{'quarter': {$gte : ?0, $lte : ?1}, $text: { $search: ?2 }}")
    List<Course> findByQuarterIntervalAndInstructor(String startQuarter,
                                                            String endQuarter,
                                                            String instructorText);

    /**
     * Returns a list of {@link Course} from the requested
     * quarter and for the requested dept
     * 
     * @param quarter   quarter formatted as YYYYQ string
     * @param dept      the department code 
     * @return a list of matching {@link Course}
     */                                                       
     @Query("{'quarter': ?0, 'deptCode': ?1}")
     List<Course> findByQuarterAndDepartment(String quarter, String dept);
}
