package services;

import dao.DaoStudents;
import model.StudentGrade;

import java.util.List;

public class ServicesStudents {
    public List<StudentGrade> getAllSubjectsStudent(int id) {
        DaoStudents daoStudents = new DaoStudents();
        return daoStudents.getAllSubjectsStudent(id);
    }

    public boolean deleteStudent(int id) {
        DaoStudents daoStudents = new DaoStudents();
        return daoStudents.deleteStudent(id);
    }

    public List<StudentGrade> getAllSuspendStudents() {
        DaoStudents daoStudents = new DaoStudents();
        return daoStudents.getAllSuspendStudents();
    }
}
