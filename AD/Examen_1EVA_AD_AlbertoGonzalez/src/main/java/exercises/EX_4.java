package exercises;

import services.ServicesStudents;

public class EX_4 {
    public static void main(String[] args) {
        ServicesStudents servicesStudents = new ServicesStudents();
        servicesStudents.getAllSuspendStudents().forEach(st ->
                System.out.println("Student " + st.getStudent().getId() + " with subject " + st.getSubject().getId() + " failed the attempt " + st.getAttempt()));
    }
}
