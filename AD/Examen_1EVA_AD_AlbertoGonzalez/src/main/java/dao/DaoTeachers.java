package dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Constantes;

@Log4j2
public class DaoTeachers {

    private DBConnectionPool dbConnectionPool = new DBConnectionPool();

    public boolean updateGradesFromGringoStudents() {
        boolean result = false;
        try {
            JdbcTemplate template = new JdbcTemplate(dbConnectionPool.getDataSource());
            template.update(Constantes.UPDATE_GRADE_FROM_GRINGO_STUDENTS);
            result = true;

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }
}
