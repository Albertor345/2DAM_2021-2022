package exercises;

import services.ServicesTeachers;

public class EX_5 {
    public static void main(String[] args) {
        ServicesTeachers servicesTeachers = new ServicesTeachers();
        if (servicesTeachers.updateGradesFromGringoStudents()){
            System.out.println("All marks increased");
        }else{
            System.out.println("There's been an error while processing your solicitude");
        }
    }

}
