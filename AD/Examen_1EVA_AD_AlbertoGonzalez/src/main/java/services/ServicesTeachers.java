package services;

import dao.DaoTeachers;

public class ServicesTeachers {

    public boolean updateGradesFromGringoStudents(){
        DaoTeachers daoTeachers = new DaoTeachers();
        return daoTeachers.updateGradesFromGringoStudents();
    }
}
