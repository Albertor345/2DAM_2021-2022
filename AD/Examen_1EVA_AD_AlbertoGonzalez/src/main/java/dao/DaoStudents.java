package dao;

import config.Config;
import lombok.extern.log4j.Log4j2;
import model.Student;
import model.StudentGrade;
import model.Subject;
import model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Constantes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log4j2
public class DaoStudents {

    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    //Lucia, estos try con constructor -> try(algo) <- como el que hay aqui debajo, CIERRAN la conexion y
    //el statement al salir del try, es una implementacion de java 9 o algo asi. No me bajes nota por no poner finallys,
    //estos try los tienen por defecto sin tener que ponerlos.

    public List<StudentGrade> getAllSubjectsStudent(int id) {
        List<StudentGrade> result = new ArrayList<>();
        try (Connection connection = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_DATA_FROM_STUDENT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(StudentGrade.builder()
                        .id(resultSet.getInt("sg.id"))
                        .student(Student.builder()
                                .id(resultSet.getInt("s.id"))
                                .name(resultSet.getString("s.name"))
                                .build())
                        .subject(Subject.builder()
                                .id(resultSet.getInt("subjects.id"))
                                .name(resultSet.getString("subjects.name"))
                                .teacher(Teacher.builder()
                                        .id(resultSet.getInt("t.id"))
                                        .name(resultSet.getString("t.name"))
                                        .build())
                                .build())
                        .grade(resultSet.getDouble("grade"))
                        .attempt(resultSet.getInt("attempt"))
                        .build());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;

    }


    public List<StudentGrade> getAllSuspendStudents() {
        try {
            JdbcTemplate template = new JdbcTemplate(dbConnectionPool.getDataSource());
            return new ArrayList<>(template.query(Constantes.SELECT_ALL_FAILED_STUDENTS, new Student_Grade_RowMapper()));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    public boolean deleteStudent(int id) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = dbConnectionPool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(Constantes.GET_STUDENT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Student student = null;

            if (resultSet.next()) {
                student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .subjects(new ArrayList<>())
                        .build();
            }

            preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_SUBJECTS_FROM_STUDENT);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student.getSubjects().add(Subject.builder().id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .teacher(Teacher.builder().id(resultSet.getInt("id_teacher"))
                                .build())
                        .build());
            }

            storeSubjectsIntoXML(student);

            preparedStatement = connection.prepareStatement(Constantes.DELETE_STUDENT_GRADES);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(Constantes.DELETE_STUDENT);
            preparedStatement.setInt(1, id);

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            result = true;
        } catch (Exception ex) {
            dbConnectionPool.rollbackConnection(connection);
            log.error(ex.getMessage(), ex);
        } finally {
            dbConnectionPool.closeConnection(connection);
        }
        return result;
    }

    private void storeSubjectsIntoXML(Student student) {
        try {
            Path file = Paths.get(Config.getInstance().getProperty("student"));
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(student, Files.newOutputStream(file));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public static final class Student_Grade_RowMapper implements RowMapper<StudentGrade> {
        @Override
        public StudentGrade mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return StudentGrade.builder()
                    .id(0)
                    .student(Student.builder()
                            .id(resultSet.getInt("id_student"))
                            .name("")
                            .build())
                    .subject(Subject.builder()
                            .id(resultSet.getInt("id_subject"))
                            .name("")
                            .teacher(Teacher.builder().build())
                            .build())
                    .grade(0)
                    .attempt(resultSet.getInt("attempt"))
                    .build();
        }
    }
}
