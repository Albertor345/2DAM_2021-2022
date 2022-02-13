package ui;

import ui.exercises.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {

            System.out.println("1: Type and title of all events located at “Auditorio y sala de exposiciones Paco de Lucía (Latina)“");
            System.out.println("2: All events located at La Latina (Use last part of event-location)");
            System.out.println("3: Title of events starting in January");
            System.out.println("4: Number of events at Latina playing in January");
            System.out.println("5: Number of events per location");
            System.out.println("6: Number of events per location at Latina");
            System.out.println("7: Event location with the largest number of events");
            System.out.println("8: Average number of events of all locations");
            System.out.println("9: Number of purchases per customer");
            System.out.println("10: Number of reviews per customer");
            System.out.println("11: Id of the customer with more purchases");
            System.out.println("12: Number of items with average rating greater than 4");
            System.out.println("13: Customers with no review lower than 3");
            System.out.println("14: Customers that are not registered as users");
            System.out.println("15: Budget spent by each customer");
            System.out.println("16: TODO()");
            System.out.println("0: End");
            System.out.println("\nChoose one");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    ex1();
                    break;
                case 2:
                    ex2();
                    break;
                case 3:
                    ex3();
                    break;
                case 4:
                    ex4();
                    break;
                case 5:
                    ex5();
                    break;
                case 6:
                    ex6();
                    break;
                case 7:
                    ex7();
                    break;
                case 8:
                    ex8();
                    break;
                case 9:
                    ex9();
                    break;
                case 10:
                    ex10();
                    break;
                case 11:
                    ex11();
                    break;
                case 12:
                    ex12();
                    break;
                case 13:
                    ex13();
                    break;
                case 14:
                    ex14();
                    break;
                case 15:
                    ex15();
                    break;
                case 16:
                    ex16();
                    break;
                default:
                    System.out.println("\nInvalid character\n");
                    break;
            }

        }
    }


    public static void ex1() {
        EX1 ex1 = new EX1();
        ex1.exercise();
        System.out.println("\n");
    }

    public static void ex2() {
        EX2 ex2 = new EX2();
        ex2.exercise();
        System.out.println("\n");
    }

    public static void ex3() {
        EX3 ex3 = new EX3();
        ex3.exercise();
        System.out.println("\n");
    }

    public static void ex4() {
        EX4 ex4 = new EX4();
        ex4.exercise();
        System.out.println("\n");
    }

    public static void ex5() {
        EX5 ex5 = new EX5();
        ex5.exercise();
        System.out.println("\n");
    }

    public static void ex6() {
        EX6 ex6 = new EX6();
        ex6.exercise();
        System.out.println("\n");
    }

    public static void ex7() {
        EX7 ex7 = new EX7();
        ex7.exercise();
        System.out.println("\n");
    }

    public static void ex8() {
        EX8 ex8 = new EX8();
        ex8.exercise();
        System.out.println("\n");
    }

    public static void ex9() {
        EX9 ex9 = new EX9();
        ex9.exercise();
        System.out.println("\n");
    }

    public static void ex10() {
        EX10 ex10 = new EX10();
        ex10.exercise();
        System.out.println("\n");
    }

    public static void ex11() {
        EX11 ex11 = new EX11();
        ex11.exercise();
        System.out.println("\n");
    }

    public static void ex12() {
        EX12 ex12 = new EX12();
        ex12.exercise();
        System.out.println("\n");
    }

    public static void ex13() {
        EX13 ex13 = new EX13();
        ex13.exercise();
        System.out.println("\n");
    }

    public static void ex14() {
        EX14 ex14 = new EX14();
        ex14.exercise();
        System.out.println("\n");
    }

    public static void ex15() {
        EX15 ex15 = new EX15();
        ex15.exercise();
        System.out.println("\n");
    }

    public static void ex16() {
        EX16 ex16 = new EX16();
        ex16.exercise();
        System.out.println("\n");
    }


}
