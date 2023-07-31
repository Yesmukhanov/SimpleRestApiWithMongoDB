package springwithmongo.restapiwithmongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RestapiwithmongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiwithmongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository,
                             MongoTemplate mongoTemplate) {
        Address address = new Address(
                "Qazaqstan",
                "Almaty",
                "1234"
        );
        String email = "yesmukhanov04@gmail.com";
        return args -> {
            Student student = new Student(
                    "Dauren",
                    "Yesmukhanov",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science"),
                    BigDecimal.TEN,
                    LocalDateTime.now()

            );

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));

            List<Student> students = mongoTemplate.find(query, Student.class);
            if (students.size() > 1) {
                throw new IllegalAccessException("Found Many Students With Email " + email);
            }

            if (students.isEmpty()) {
                System.out.println("Inserting student " + student);
                studentRepository.insert(student);
            } else {
                System.out.println(students + " already exists");
            }
        };
    }

}
