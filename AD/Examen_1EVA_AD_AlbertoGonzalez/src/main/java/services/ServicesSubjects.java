package services;

import dao.DaoStudents;
import dao.DaoSubjects;
import model.Student;
import model.StudentGrade;
import model.Subject;

import java.util.List;

public class ServicesSubjects {
    public String addGradeToStudent(int id_student, int id_subject, double grade) {
        DaoStudents daoStudents = new DaoStudents();
        DaoSubjects daoSubjects = new DaoSubjects();
        String result;

        List<StudentGrade> grades = daoStudents.getAllSubjectsStudent(id_student);
        StudentGrade studentGrade = StudentGrade.builder()
                .student(Student.builder().id(id_student).build())
                .subject(Subject.builder().id(id_subject).build())
                .build();

        if (grades.contains(studentGrade)) {
            if (grades.get(grades.lastIndexOf(studentGrade)).getAttempt() == 4) {
                result = "This student has reached max tries";
            } else {
                if (grades.get(grades.size() - 1).getGrade() >= 5) {
                    result = "This student got a mark grater than 5 in his last try";
                } else {
                    if (daoSubjects.addGradeToStudent(id_student, id_subject, grade, grades.get(grades.lastIndexOf(studentGrade)).getAttempt() + 1)) {
                        result = "Grade added to the student";
                    } else result = "There's been an error while processing your solicitude";
                }
            }
        } else {
            if (daoSubjects.addGradeToStudent(id_student, id_subject, grade, 1)) {
                result = "Grade added to the student";
            } else result = "There's been an error while processing your solicitude";
        }
        return result;
    }
}
