package av.saprykin.testforcma.service;

import av.saprykin.testforcma.entity.Student;
import av.saprykin.testforcma.repository.StudentRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@ShellComponent
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @ShellMethod("add student -Name -Patronymic -Surname -Date of birth(in format dd.MM.yyyy) -GroupId")
    @Transactional
    public Long addStudent(@ShellOption String name,@ShellOption String patronymic, @ShellOption String surname, @ShellOption String dateOfBirth, @ShellOption Long groupId ) {
        Student student = new Student();
        student.setName(name);
        student.setPatronymic(patronymic);
        student.setSurname(surname);
        student.setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        student.setGroupId(groupId);
        student = studentRepository.save(student);
        return student.getId();
    }

    @ShellMethod("delete student -StudentId")
    @Transactional
    public boolean deleteStudent(@ShellOption Long studentId){
        if(studentRepository.findById(studentId).isPresent()) {
            studentRepository.deleteById(studentId);
            return true;
        }
        else return false;
    }

    @ShellMethod("show all students in db")
    @Transactional
    public Iterable<Student> showAllStudents() {
        return studentRepository.findAll();
    }
}
