package utils;

public class Constantes {
    public final static String SELECT_ALL_DATA_FROM_STUDENT = "select * from subjects " +
            "inner join students_grades sg on subjects.id = sg.id_subject " +
            "inner join teachers t on subjects.id_teacher = t.id " +
            "inner join students s on sg.id_student = s.id " +
            "where id_student = ?";

    public final static String SELECT_ALL_SUBJECTS_FROM_STUDENT = "select * " +
            "from subjects where id in (" +
            "select id_subject from students_grades " +
            "where id_student = ?" +
            ")";


    public final static String INSERT_STUDENT_GRADE = "insert into students_grades " +
            "(id_student, id_subject, grade, attempt) " +
            "values (?, ?, ?, ?)";

    public final static String SELECT_ALL_FAILED_STUDENTS = "select name, id_subject, attempt, grade " +
            "from students_grades as sg " +
            "inner join students s on sg.id_student = s.id " +
            "where attempt in (select max(attempt) as max " +
            "                  from students_grades sg2 " +
            "                           inner join students on sg.id_student = sg2.id_student " +
            "                  group by id_student, id_subject) " +
            "  and grade < 5;";

   /* "select id_student, id_subject, max(attempt) as mAttempt, max(grade) < 5 as pass
    from students_grades
    group BY id_student, id_subject;"*/

    public final static String UPDATE_GRADE_FROM_GRINGO_STUDENTS = "update students_grades " +
            "inner join subjects s on students_grades.id_subject = s.id " +
            "inner join teachers t on s.id_teacher = t.id " +
            "set grade = grade + 0.5 " +
            "where t.name = 'gringo'";

    public final static String GET_STUDENT = "select * from students where id = ?";

    public final static String DELETE_STUDENT_GRADES = "delete from students_grades where id_student = ?";

    public final static String DELETE_STUDENT = "delete from students where id = ?";

}
