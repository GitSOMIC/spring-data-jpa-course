package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
//         return args -> {
//             Student maria = new Student(
//                     "Maria",
//                     "Jones",
//                     "maria.jones@amigoscode.edu",
//                     21
//             );
//             Student maria2 = new Student(
//                     "Maria",
//                     "Jones",
//                     "maria2.jones@amigoscode.edu",
//                     25
//             );
//
//
//             Student ahemd = new Student(
//                     "Ahemd",
//                     "Ali",
//                     "Ahemd.Ali@amigoscode.edu",
//                     18
//             );
//             studentRepository.saveAll(List.of(maria, ahemd, maria2));
//             /*System.out.println(studentRepository.count());
//             studentRepository
//                     .findById(2L)
//                     .ifPresentOrElse(
//                             System.out::println,
//                             () -> System.out.println("Student with ID 2 not found")
//                     );
//             studentRepository
//                     .findById(3L)
//                     .ifPresentOrElse(
//                             System.out::println,
//                             () -> System.out.println("Student with ID 3 not found")
//                     );
//             List<Student> all = studentRepository.findAll();
//             all.forEach(System.out::println);
//
//             studentRepository.deleteById(1L);
//             System.out.println(studentRepository.count());
// */
//
//             studentRepository
//                     .findStudentByEmail("Ahemd.Ali@amigoscode.edu")
//                     .ifPresentOrElse(
//                             System.out::println,
//                             () -> System.out.println("Student with email Ahemd.Ali@amigoscode.edu  not found"));
//
//             studentRepository.findStudentByFirstNameEqualsAndAgeEquals(
//                     "Maria",
//                     21
//             ).forEach(System.out::println);
//             studentRepository.findStudentByFirstNameEqualsAndAgeGreaterThanEqual(
//                     "Maria",
//                     21
//             ).forEach(System.out::println);
//
//             studentRepository.findStudentByNative(
//                     "Maria",
//                     21
//             ).forEach(System.out::println);
//             studentRepository.findStudentByNative2(
//                     "Maria",
//                     21
//             ).forEach(System.out::println);
//
//             System.out.println("studentRepository.deleteStudentById(3L) = " + studentRepository.deleteStudentById(3L));
//         };
        return args -> {
            generateRandomStudents(studentRepository);
            PageRequest pageRequest = PageRequest.of(
                    0,
                    5,
                    Sort.by("firstName").ascending());
            Page<Student> page = studentRepository.findAll(pageRequest);
            System.out.println("page = " + page);
        };
    }

    private static void sorting(StudentRepository studentRepository) {
        Sort sort = Sort.by("firstName").ascending()
                .and(Sort.by("age").descending());
        studentRepository.findAll(sort)
                .forEach(
                        student -> System.out.println(student.getFirstName() + " " + student.getAge()));
    }

    private void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(11, 55));
            studentRepository.save(student);
        }
    }

}
