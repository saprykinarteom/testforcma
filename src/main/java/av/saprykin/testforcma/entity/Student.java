package av.saprykin.testforcma.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String patronymic;
    private String surname;
    private LocalDate dateOfBirth;
    private Long groupId;


}
