package mits.miniproject.universityjobportal.TestPack;



import mits.miniproject.universityjobportal.Utility.Role;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import mits.miniproject.universityjobportal.Entity.UserEntity;
import mits.miniproject.universityjobportal.Repository.StudentRepository;
import mits.miniproject.universityjobportal.Repository.UserRepository;
import mits.miniproject.universityjobportal.Entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional // rolls back everything this test writes once it finishes - keeps your DB clean
class StudentUserMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void studentSharesPrimaryKeyWithUser() {
        // 1. Create and save the User FIRST - Student's id depends on this row already existing
        UserEntity user = new UserEntity();
        user.setName("Test Student");
        user.setEmail("test.student@example.com");
        user.setPassword("dummyPasswordForNow");
        user.setPhone("9999999999");
        user.setRole(Role.STUDENT);
        user.setActive(true);
        user = userRepository.save(user);

        // 2. Create the Student, linking it to the saved User
        //    NOTE: adjust "setUser(...)" below if you named this field differently in Student.java
        StudentEntity student = new StudentEntity();
        student.setUser(user);
        student.setUsn("1XX20CS999");
        student.setDepartment("Computer Science");
        student.setCgpa(new BigDecimal("8.50"));
        student.setBacklogs(0);
        student.setGraduationYear(2027);
        student = studentRepository.save(student);

        // 3. Fetch the Student back independently, using the USER's id
        StudentEntity fetched = studentRepository.findById(user.getId()).orElseThrow();

        // 4. The actual point of this test: Student's id IS User's id.
        //    There is no separate "userId" column - this line proves @MapsId worked.
        assertEquals(user.getId(), fetched.getId());
        assertEquals("1XX20CS999", fetched.getUsn());
    }
}
