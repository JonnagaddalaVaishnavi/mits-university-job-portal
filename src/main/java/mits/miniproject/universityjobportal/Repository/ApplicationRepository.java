package mits.miniproject.universityjobportal.Repository;

import mits.miniproject.universityjobportal.Entity.ApplicationEntity;
import mits.miniproject.universityjobportal.Entity.JobEntity;
import mits.miniproject.universityjobportal.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
    List<ApplicationEntity> findByStudent(StudentEntity student);
    List<ApplicationEntity> findByJob(JobEntity job);
    boolean existsByStudentAndJob(StudentEntity student, JobEntity job);
}