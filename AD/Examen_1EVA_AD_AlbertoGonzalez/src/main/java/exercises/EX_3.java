package exercises;

import services.ServicesStudents;

import java.util.Scanner;

public class EX_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce a student ID");
        int id = sc.nextInt();
        sc.nextLine();

        ServicesStudents servicesStudents = new ServicesStudents();
        if (servicesStudents.deleteStudent(id)){
            System.out.println("Student deleted, subjects stored");
        }else{
            System.out.println("Something went wrong");
        }
    }
}
