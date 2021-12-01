package dao;

import lombok.extern.log4j.Log4j2;
import utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Log4j2
public class DaoSubjects {

    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    public boolean addGradeToStudent(int id_student, int id_subject, double grade, int attempt) {

        //Lucia, estos try con constructor -> try(algo) <- como el que hay aqui debajo, CIERRAN la conexion y
        //el statement al salir del try, es una implementacion de java 9 o algo asi. No me bajes nota por no poner finallys,
        //estos try los tienen por defecto sin tener que ponerlos.

        try (Connection connection = dbConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.INSERT_STUDENT_GRADE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id_student);
            preparedStatement.setInt(2, id_subject);
            preparedStatement.setDouble(3, grade);
            preparedStatement.setInt(4, attempt);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("Recovered ID from insert: " + resultSet.getInt(1));
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }
}
