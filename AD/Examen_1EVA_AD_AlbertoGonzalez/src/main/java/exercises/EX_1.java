package exercises;

import config.Config;
import services.ServicesStudents;

import java.util.Scanner;

public class EX_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce a student ID");
        int id = sc.nextInt();
        sc.nextLine();

        ServicesStudents servicesStudents = new ServicesStudents();
        servicesStudents.getAllSubjectsStudent(id).forEach(System.out::println);
    }
}
