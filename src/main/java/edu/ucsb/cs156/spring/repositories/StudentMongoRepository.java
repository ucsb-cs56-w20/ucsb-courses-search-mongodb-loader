package edu.ucsb.cs156.spring.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.ucsb.cs156.spring.documents.StudentDocument;

import java.util.Optional;

@Repository
public interface StudentMongoRepository extends MongoRepository<StudentDocument, ObjectId> {
    Optional<StudentDocument> findOneByPerm(int perm);
}