package edu.ucsb.cs156.spring.repositories;


import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.ucsb.cs156.spring.entities.StudentEntity;


@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
  List<StudentEntity> findByPerm(int perm);
}
