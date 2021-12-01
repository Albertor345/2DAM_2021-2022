package exercises;

import services.ServicesSubjects;

import java.util.Scanner;

public class EX_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce a student ID");
        int student_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce a subject ID");
        int subject_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce a grade");
        double grade = sc.nextDouble();
        sc.nextLine();

        ServicesSubjects servicesSubjects = new ServicesSubjects();
        System.out.println(servicesSubjects.addGradeToStudent(student_id, subject_id, grade));
    }
}
