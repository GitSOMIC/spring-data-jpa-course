package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author XinYu(Jerry)
 * @create 2025-01-27-14:36
 */
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age > :age")
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = ?1 AND age >= ?2",
            nativeQuery = true)
    List<Student> findStudentByNative(String firstName, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true)
    List<Student> findStudentByNative2(@Param("firstName") String firstName, @Param("age") Integer age);

    List<Student> findStudentByFirstNameEqualsAndAgeGreaterThanEqual(String firstName, Integer age);
    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
